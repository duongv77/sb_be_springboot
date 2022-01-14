package duong.dev.serviceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.ServletException;

import duong.dev.common.Common;
import duong.dev.dto.request.*;
import duong.dev.dto.response.CountOrderAnhOrderSuccessResponseDTO;
import duong.dev.entity.*;
import duong.dev.repository.*;
import duong.dev.service.OrderStatusHistoryInterface;
import duong.dev.service.TurnVoteInteface;
import duong.dev.thread.SendMailAcceptOrderThread;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import duong.dev.JwtTokenUtil;
import duong.dev.common.ResponeCustom;
import duong.dev.common.Status;
import duong.dev.dto.AccountDTO;
import duong.dev.dto.CartdetailDTO;
import duong.dev.dto.OrderDTO;
import duong.dev.dto.ProductDTO;
import duong.dev.dto.response.CartdetailSaleResponseDTO;
import duong.dev.exception.AppException;
import duong.dev.mapper.OrderMapper;
import duong.dev.service.OrderInterface;

@Log4j2
@Service
public class OrderServiceImpl implements OrderInterface {
	@Autowired
	private OrderRepository orderRepo;
	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private OrderDetailRepository orderDetailRepository;
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	@Autowired
	private CartdetailServiceImpl cartdetailServiceImpl;
	@Autowired
	private CartdetailRepository cartdetailRepository;
	@Autowired
	private PromotionRepository promotionRepo;
	@Autowired
	private OrderStatusHistoryInterface orderStatusHistoryInterface;
	@Autowired
	private TurnVoteInteface turnVoteSev;
	@Autowired
	private VoucherServiceImpl voucherSev;
	@Autowired
	private OrderDetailServiceImpl orderDetailSev;
	
	public List<OrderDTO> convertListDTO(List<Order> listOrderE) {
		return listOrderE.stream().map(elm->orderMapper.convertToDTO(elm)).collect(Collectors.toList());
	}

	@Override
	public List<OrderDTO> readAll(){
		List<Order> listOrderE = orderRepo.findAll();
		return convertListDTO(listOrderE);
	}

	@Override
	public Order create(String address) throws IOException, ServletException {
		Account account = getAccount();
		Order order = new Order();
		order.setAddress(address);
		order.setAccount(account);
		order.setStatus(Status.STATUS_ORDER_CHO_XAC_NHAN);
		order.setTypeorder(Status.STATUS_TYPE_ORDER_CART);
		orderRepo.save(order);
		return order;
	}

	public  Account getAccount() throws IOException, ServletException {
		AccountDTO accountDTO = jwtTokenUtil.getUserToToken();
		return accountRepository.findById(accountDTO.getId()).get();
	}
	
	public Integer[] priceOrderDetail(OrderDetail orderDetail) {
		Integer price = orderDetail.getPrice()*orderDetail.getQuantity();
		Optional<Promotion> promotion = promotionRepo.getPromotionActiveByIdProduct(orderDetail.getProduct().getId());
		if(promotion.isPresent()) {
			orderDetail.setSale(promotion.get().getSale());
			price = price/100*(100-promotion.get().getSale());
		}
		Integer a[] = {price, !promotion.isPresent()?null:promotion.get().getSale()};
		return a;
	}

	public Order createOrderAmin(CreateOrderTypeDTO createOrderTypeDTO, Integer status, Integer typeOrder) {
		Optional<Order> orderOptional = orderRepo.findById(createOrderTypeDTO.getOrderId());
		if(!orderOptional.isPresent())
			throw new AppException(ResponeCustom.MESSAGE_CODE_KHONG_TON_TAI, "Đơn hàng không tồn tại!");
		Order order = orderOptional.get();
		order.setDescription(createOrderTypeDTO.getDescription());
		order.setStatus(status);
		order.setAddress(createOrderTypeDTO.getAddress());
		List<OrderDetail> orderDetails = order.getOrderdetail().stream().map(elm->{
			elm.setStatus(Status.STATUS_HOAT_DONG);
			return  elm;
		}).collect(Collectors.toList());
		order.setTypeorder(typeOrder);
		orderDetailRepository.saveAll(orderDetails);
		orderDetailSev.refetchOrderTotals(order);
		order.setCreateDate(Common.convertDateTime(java.time.LocalDateTime.now()+""));
		orderRepo.save(order);
		return order;
	}

	@Override
	public OrderDTO createDetailOff(CreateOrderTypeDTO createOrderTypeDTO){
		Order order = createOrderAmin(createOrderTypeDTO, Status.STATUS_ORDER_DA_GIAO_HANG, Status.STATUS_TYPE_ORDER_OFFLINE);
		orderStatusHistoryInterface.create4(order);
		return orderMapper.convertToDTO(order);
	}

	@Override
	public OrderDTO createDetailOnline(CreateOrderTypeDTO createOrderTypeDTO) {
		Order order = createOrderAmin(createOrderTypeDTO, Status.STATUS_ORDER_DA_XAC_NHAN, Status.STATUS_TYPE_ORDER_ONLINE);
		orderStatusHistoryInterface.create2(order);
		return orderMapper.convertToDTO(order);
	}


	@Override
	public List<OrderDTO> searchOrder(String sortStr, String keyword) {
		Sort sort = Sort.by(sortStr).descending();
		List<Order> listOrder = orderRepo.searchOrder(sort, keyword);
		return convertListDTO(listOrder);
	}

	@Override
	public List<CartdetailSaleResponseDTO> createDetail(CreateOrderDTO createOrderDTO) throws IOException, ServletException {
		Voucher voucher = voucherSev.voucherCheck(createOrderDTO.getVoucherCode());
		Order order = create(createOrderDTO.getAddress());
		orderStatusHistoryInterface.create1(order);
		Integer total = 0;
		Integer productQuantity = 0;
		for (CartdetailDTO cartdetailDTO : createOrderDTO.getCartdetail()) {
			productQuantity += cartdetailDTO.getQuantity();
			Product product = productRepository.getById(cartdetailDTO.getProduct().getId());
			OrderDetail orderDetail = new OrderDetail();
			orderDetail.setOrder(order);
			orderDetail.setProduct(product);
			orderDetail.setPrice(product.getPrice());
			orderDetail.setQuantity(cartdetailDTO.getQuantity());
			orderDetail.setStatus(Status.STATUS_HOAT_DONG);
			Integer a[] = priceOrderDetail(orderDetail);
			total += a[0];
			orderDetail.setSale(a[1]);
			orderDetailRepository.save(orderDetail);
		}
		Integer shipping = checkShippingPrice(order.getAddress());
		total = total/100*110 + shipping;

		if(voucher.getTotalPrice()!=null){
			if(total<voucher.getTotalPrice())
				throw new AppException(ResponeCustom.MESSAGE_CODE_KHONG_THANH_CONG, "Đơn hàng đủ điều kiện!");
		}else if(voucher.getProductQuantity()!=null){
			if(productQuantity<voucher.getProductQuantity())
				throw new AppException(ResponeCustom.MESSAGE_CODE_KHONG_THANH_CONG, "Đơn hàng đủ điều kiện!");
		}else if(voucher.getOrderQuantity()!=null){
			if(orderRepo.findQuantityOrder(getAccount().getId())<voucher.getOrderQuantity())
				throw new AppException(ResponeCustom.MESSAGE_CODE_KHONG_THANH_CONG, "Tài khoản không đủ điều kiện!");
		}

		if(voucher.getVoucherCode()!=null){ //nếu mã giảm giá check ổn thì chạy vào đây
			order.setVoucherCode(voucher.getVoucherCode());
			if(voucher.getSalePrice()!=null){
				total = total - voucher.getSalePrice();
			}else{
				total = total / 100 * (100-voucher.getSalePercent());
			}
		}

		order.setTotal(total);
		orderRepo.save(order);

		createOrderDTO.getCartdetail().parallelStream().forEach(elm->{
			cartdetailRepository.delete(cartdetailRepository.getById(elm.getId()));
		});

		return cartdetailServiceImpl.readAll();
	}

	@Override
	public List<OrderDTO> findByListOrderNew() {
		List<Order> listOrder = orderRepo.findByListOrderNew();
		return convertListDTO(listOrder);
	}

	@Override
	public List<OrderDTO> finByListOrderMonth() {
		List<Order> listOrder = orderRepo.finByListOrderMonth();
		return convertListDTO(listOrder);
	}

	@Override
	public List<OrderDTO> findListOrderHistory() throws IOException, ServletException {
		Account account = getAccount();
		Sort sort = Sort.by("createDate").descending();
		List<Order> order = orderRepo.finByListOrderByAccount(account.getId(), sort);
		return convertListDTO(order);
	}

	@Override
	public OrderDTO createReturnOrder(CreateOrderReturnRequestDTO obj) {
		Optional<Order> orderOptional = orderRepo.findById(obj.getId());
		if(!orderOptional.isPresent())
			throw new AppException(ResponeCustom.MESSAGE_CODE_KHONG_TON_TAI, "Order không tồn tại");
		Order order = orderOptional.get();

		//chuyển các orderdetail cũ sang status yêu cầu trả hàng
		List<OrderDetail> orderDetails = order.getOrderdetail().stream().map(elm->{
//			hoàn số lượng sản phẩm
			Product product = productRepository.findById(elm.getProduct().getId()).get();
			product.setQuantity(product.getQuantity()+elm.getQuantity());
			productRepository.save(product);

			elm.setStatus(Status.STATUS_ORDER_YEU_CAU_TRA_HANG);
			return elm;
		}).collect(Collectors.toList());

		//tạo lại các order detail
		List<OrderDetail> orderDetailsNew = obj.getOrderdetail().stream().map(elm->{
//			cập nhập lại số lượng
			Product product = productRepository.findById(elm.getProduct().getId()).get();
			product.setQuantity(product.getQuantity()-elm.getQuantity());
			productRepository.save(product);

			OrderDetail orderDetailN = new OrderDetail();
			orderDetailN.setOrder(order);
			orderDetailN.setQuantity(elm.getQuantity());
			orderDetailN.setSale(elm.getSale());
			orderDetailN.setPrice(elm.getPrice());
			orderDetailN.setProduct(elm.getProduct());
			orderDetailN.setStatus(Status.STATUS_HOAT_DONG);
			return orderDetailN;
		}).collect(Collectors.toList());

		orderDetailRepository.saveAll(orderDetailsNew);
		orderDetailRepository.saveAll(orderDetails);

		order.setCreateDate(Common.convertDateTime(java.time.LocalDateTime.now()+""));
		order.setStatus(Status.STATUS_ORDER_YEU_CAU_TRA_HANG);
		order.setReturnOrder(obj.getDescription());
		orderRepo.save(order);

		orderStatusHistoryInterface.create6( order);
		orderStatusHistoryInterface.create4(order);
		Order orderNew = orderRepo.findById(order.getId()).get();
		resetQuantity(order);

		return orderMapper.convertToDTO(orderNew);
	}

	@Override
	public OrderDTO createOrderDefault()throws IOException, ServletException {
		Order order = new Order();
		order.setAccount(getAccount());
		order.setAddress("");
		order.setStatus(Status.STATUS_ORDER_TREO);
		order.setTypeorder(Status.STATUS_TYPE_ORDER_OFFLINE);
		orderRepo.save(order);
		return orderMapper.convertToDTO(order);
	}

	@Override
	public List<OrderDTO> findAllOrderDefault() {
		Sort sort = Sort.by("createDate").descending();
		List<Order> listOrder = orderRepo.findByStatusDefault(sort);
		return convertListDTO(listOrder);
	}

	@Override
	public CountOrderAnhOrderSuccessResponseDTO reportCountOrder() {
		Long countTotalOrder = orderRepo.findCoundTotalOrder();
		Long countTotalOrderSuccess = orderRepo.findCoundTotalOrderSuccess();
		List<Long> report = new ArrayList<>();
		report.add(countTotalOrderSuccess);
		report.add(countTotalOrder);
		return new CountOrderAnhOrderSuccessResponseDTO(countTotalOrder, countTotalOrderSuccess);
	}

	@Override
	public Long findQuantityOrderByAccount() throws IOException, ServletException{
		return orderRepo.quantityOrderByAccount(getAccount().getId());
	}

	@Override
	public Order createOnline(String address,String description) throws IOException, ServletException {
		Account account =  getAccount();
		Order order = new Order();
		order.setAddress(address);
		order.setDescription(description);
		order.setAccount(account);
		order.setStatus(Status.STATUS_ORDER_DA_XAC_NHAN);
		order.setTypeorder(Status.STATUS_TYPE_ORDER_ONLINE);
		orderRepo.save(order);
		return order;
	}

	@Override
	public OrderDTO delete(Order orderE){
		orderRepo.delete(orderE);
		return orderMapper.convertToDTO(orderE);
	}

	@Override
	public OrderDTO update(OrderDTO orderD){
		return null;
	}

	@Override
	public List<OrderDTO> findListOrder(String sortStr) {
		Sort sort = Sort.by(sortStr).descending();
		List<Order> listOrder = orderRepo.findAll(sort);
		return convertListDTO(listOrder);
	}
	
	public List<OrderDTO> getListOrderByStatus(Integer status,String sortStr) {
		Sort sort = Sort.by(sortStr).descending();
		List<Order> listOrderE = orderRepo.findByStatus(status, sort);
		return convertListDTO(listOrderE);
	}
	

	@Override
	public List<OrderDTO> getListOrderSuccess(String sortStr) {
		Sort sort = Sort.by(sortStr).descending();
		List<Order> listOrderE = orderRepo.findByStatusSuccess(sort);
		return convertListDTO(listOrderE);
	}

	@Override
	public List<OrderDTO> getListOrder() {
		Sort sort = Sort.by("createDate").descending();
		List<Order> listOrderE = orderRepo.findAll(sort);
		return convertListDTO(listOrderE);
	}

	public OrderDTO updateStatusVsAddress(UpdateStatusVsAddressOrderDTO orderDTO) {
		Optional<Order> order = orderRepo.findById(orderDTO.getId());
		if (!order.isPresent())
			throw new AppException(ResponeCustom.MESSAGE_CODE_KHONG_TON_TAI, "Order không tồn tại");
		Order orderNew = order.get();
		orderNew.setStatus(orderDTO.getStatus());
		orderNew.setAddress(orderDTO.getAddress());
		orderRepo.save(orderNew);
		return orderMapper.convertToDTO(orderNew);
	}

	@Override
	public OrderDTO acceptOrder(Integer orderId, Integer price){
		Optional<Order> order = orderRepo.findById(orderId);
		if (!order.isPresent())
			throw new AppException(ResponeCustom.MESSAGE_CODE_KHONG_TON_TAI, "Order không tồn tại");
		Order orderE = order.get();
		orderE.setCreateDate(Common.convertDateTime(java.time.LocalDateTime.now()+""));
		browseOrderQuantity(orderE);
		orderE.setDeposit(price);
		orderRepo.save(orderE);
		orderE.setStatus(Status.STATUS_ORDER_DA_XAC_NHAN);
		orderStatusHistoryInterface.create2(orderE);

		if(order.get().getTypeorder() == Status.STATUS_TYPE_ORDER_CART){
			Thread thread = new SendMailAcceptOrderThread(order.get().getAccount().getEmail(), orderE.getId());
			thread.start();
		}
		return orderMapper.convertToDTO(orderE);
	}

	public void browseOrderQuantity(Order order){
		List<OrderDetail> listOrderDetail = order.getOrderdetail();
		listOrderDetail.forEach(elm->{
			Product product = productRepository.findById(elm.getProduct().getId()).get();
			if(product.getQuantity()<elm.getQuantity())
				throw new AppException(ResponeCustom.MESSAGE_CODE_KHONG_THANH_CONG, "Sản phẩm "+product.getTitle().getName()+" "+product.getName()+" không còn đủ số lượng cần!" );
		});
		listOrderDetail.forEach(elm->{
			Product product = productRepository.findById(elm.getProduct().getId()).get();
			int quantity = product.getQuantity()-elm.getQuantity();
			product.setQuantity(quantity);
			if(quantity==0) product.setAvailable(Status.STATUS_KHONG_HOAT_DONG);
			productRepository.save(product);
		});
	}

	@Override
	public OrderDTO cancelOrder(Integer orderId){
		Optional<Order> order = orderRepo.findById(orderId);
		if (!order.isPresent())
			throw new AppException(ResponeCustom.MESSAGE_CODE_KHONG_TON_TAI, "Order không tồn tại");
		Order orderE = order.get();
		resetQuantity(orderE);
		orderE.setCreateDate(Common.convertDateTime(java.time.LocalDateTime.now()+""));
		orderE.setStatus(Status.STATUS_ORDER_DA_HUY);
		orderRepo.save(orderE);
		orderStatusHistoryInterface.create5(orderE);
		return orderMapper.convertToDTO(orderE);
	}

	@Override
	public OrderDTO updateOrderStatusTransform(Integer orderId) {
		Optional<Order> order = orderRepo.findById(orderId);
		if (!order.isPresent())
			throw new AppException(ResponeCustom.MESSAGE_CODE_KHONG_TON_TAI, "Order không tồn tại");
		Order orderE = order.get();
		orderE.setCreateDate(Common.convertDateTime(java.time.LocalDateTime.now()+""));
		orderE.setStatus(Status.STATUS_ORDER_DANG_GIAO_HANG);
		orderRepo.save(orderE);
		orderStatusHistoryInterface.create3(orderE);
		return orderMapper.convertToDTO(orderE);
	}

	@Override
	public OrderDTO updateOrderStatusFinal(Integer orderId){
		Optional<Order> order = orderRepo.findById(orderId);
		if (!order.isPresent())
			throw new AppException(ResponeCustom.MESSAGE_CODE_KHONG_TON_TAI, "Order không tồn tại");
		Order orderE = order.get();
		orderE.getOrderdetail().forEach(elm->{
			turnVoteSev.updateQuantityToAdd(elm.getOrder().getAccount(), elm.getProduct().getId());
		});
		orderE.setCreateDate(Common.convertDateTime(java.time.LocalDateTime.now()+""));
		orderE.setStatus(Status.STATUS_ORDER_DA_GIAO_HANG);
		orderRepo.save(orderE);
		orderStatusHistoryInterface.create4(orderE);
		return orderMapper.convertToDTO(orderE);
	}

	@Override
	public OrderDTO updateOrderStatusNew(Integer orderId) {
		Optional<Order> order = orderRepo.findById(orderId);
		if (!order.isPresent())
			throw new AppException(ResponeCustom.MESSAGE_CODE_KHONG_TON_TAI, "Order không tồn tại");
		Order orderE = order.get();
		resetQuantity(orderE);
		orderE.setCreateDate(Common.convertDateTime(java.time.LocalDateTime.now()+""));
		orderE.setStatus(Status.STATUS_ORDER_CHO_XAC_NHAN);
		orderRepo.save(orderE);
		orderStatusHistoryInterface.create1(orderE);
		return orderMapper.convertToDTO(orderE);
	}

	public void resetQuantity(Order orderOld){
		Optional<Order> orderOptional = orderRepo.findById(orderOld.getId());
		if(!orderOptional.isPresent()) return;
		Order order = orderOptional.get();
		if(order.getStatus()>1) {
			List<OrderDetail> listOrderDetail = order.getOrderdetail();
			listOrderDetail.parallelStream().forEach(elm->{
				if(elm.getStatus()==Status.STATUS_HOAT_DONG){
					Product product = productRepository.findById(elm.getProduct().getId()).get();
					int quantity = product.getQuantity()+elm.getQuantity();
					product.setQuantity(quantity);
					if(quantity==0) product.setAvailable(Status.STATUS_KHONG_HOAT_DONG);
					productRepository.save(product);
				}
			});
		}
	}

	public Integer checkShippingPrice(String address){
		String addressUptocase = address.toUpperCase();
		if (addressUptocase.contains("HÀ NỘI")) {
			return 20000;
		}else{
			return 30000;
		}
	}
	
	

}
