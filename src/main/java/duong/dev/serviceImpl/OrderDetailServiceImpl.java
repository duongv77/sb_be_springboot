package duong.dev.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import duong.dev.common.Common;
import duong.dev.common.ResponeCustom;
import duong.dev.common.Status;
import duong.dev.dto.OrderDTO;
import duong.dev.dto.request.CreateOrderdetailDTO;
import duong.dev.dto.request.UpdateQuantityOrderDetailRQDTO;
import duong.dev.entity.Order;
import duong.dev.entity.Product;
import duong.dev.entity.Promotion;
import duong.dev.exception.AppException;
import duong.dev.mapper.OrderMapper;
import duong.dev.repository.OrderRepository;
import duong.dev.repository.ProductRepository;
import duong.dev.repository.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import duong.dev.dto.OrderDetailDTO;
import duong.dev.entity.OrderDetail;
import duong.dev.mapper.OrderDetailMapper;
import duong.dev.repository.OrderDetailRepository;
import duong.dev.service.OrderDetailInterface;

@Service
public class OrderDetailServiceImpl implements  OrderDetailInterface {

	@Autowired
	private OrderDetailRepository orderDetailRepo;
	@Autowired
	private OrderDetailMapper orderDetailMapper;
	@Autowired
	private OrderRepository orderRepo;
	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private ProductRepository productRepo;
	@Autowired
	private PromotionRepository promotionRepo;
	@Autowired
	private OrderServiceImpl orderSev;

	public List<OrderDetailDTO> convertListDTO(List<OrderDetail> listOrderDetailE) {
		List<OrderDetailDTO> listOrderDetailD = new ArrayList<OrderDetailDTO>();
		for (OrderDetail orderDetailE : listOrderDetailE) {
			listOrderDetailD.add(orderDetailMapper.convertToDTO(orderDetailE));
		}
		return listOrderDetailD;
	}

	@Override
	public List<OrderDetailDTO> readAll(){
		List<OrderDetail> listOrderDetailE = orderDetailRepo.findAll();
		return convertListDTO(listOrderDetailE);

	}

	@Override
	public List<OrderDetailDTO> findAllByOrderId(Integer orderId) {
		List<OrderDetail> orderDetailE = orderDetailRepo.findByOrderId(orderId);
		return convertListDTO(orderDetailE);
	}

	@Override
	public OrderDTO create(CreateOrderdetailDTO createorderdetailDTO) {
		Optional<Order> orderOptional = orderRepo.findById(createorderdetailDTO.getOrderId());
		if(!orderOptional.isPresent()){
			throw new AppException(ResponeCustom.MESSAGE_CODE_KHONG_TON_TAI, "Đơn hàng không tồn tại!");
		}
		Optional<Product> productOptional = productRepo.findById(createorderdetailDTO.getProductId());
		if(!productOptional.isPresent()){
			throw new AppException(ResponeCustom.MESSAGE_CODE_KHONG_TON_TAI, "sản phẩm không tồn tại!");
		}
        Optional<OrderDetail> orderDetailOptional = orderDetailRepo.findOrderDetailByProductIdAndOrderId(createorderdetailDTO.getProductId(), createorderdetailDTO.getOrderId());
		if(orderDetailOptional.isPresent()){
			throw new AppException(ResponeCustom.MESSAGE_CODE_KHONG_TON_TAI, "Sản phẩm đã tồn tại trong đơn hàng!");
		}
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setOrder(orderOptional.get());
		orderDetail.setProduct(productOptional.get());
		orderDetail.setQuantity(1);
		orderDetail.setPrice(productOptional.get().getPrice());
		orderDetail.setStatus(Status.STATUS_HOAT_DONG);
		orderDetailRepo.save(orderDetail);
		Optional<Promotion> promotion = promotionRepo.getPromotionActiveByIdProduct(orderDetail.getProduct().getId());
		if(promotion.isPresent()) {
			orderDetail.setSale(promotion.get().getSale());
		}
		Order order = orderRepo.findById(orderDetail.getOrder().getId()).get();
		return orderMapper.convertToDTO(refetchOrderTotals(order));
	}

	@Override
	public List<OrderDetailDTO> create2(CreateOrderdetailDTO createorderdetailDTO) {
		Optional<Order> orderOptional = orderRepo.findById(createorderdetailDTO.getOrderId());
		if(!orderOptional.isPresent()){
			throw new AppException(ResponeCustom.MESSAGE_CODE_KHONG_TON_TAI, "Đơn hàng không tồn tại!");
		}
		Optional<Product> productOptional = productRepo.findById(createorderdetailDTO.getProductId());
		if(!productOptional.isPresent()){
			throw new AppException(ResponeCustom.MESSAGE_CODE_KHONG_TON_TAI, "sản phẩm không tồn tại!");
		}
		Optional<OrderDetail> orderDetailOptional = orderDetailRepo.findOrderDetailByProductIdAndOrderId(createorderdetailDTO.getProductId(), createorderdetailDTO.getOrderId());
		if(orderDetailOptional.isPresent()){
			if(orderDetailOptional.get().getQuantity()>productOptional.get().getQuantity())
				throw new AppException(ResponeCustom.MESSAGE_CODE_KHONG_THANH_CONG, "Không còn đủ sản phẩm này!");
			OrderDetail orderDetail = orderDetailOptional.get();
			orderDetail.setQuantity(orderDetail.getQuantity()+1);
			orderDetailRepo.save(orderDetail);
		}else{
			OrderDetail orderDetail = new OrderDetail();
			orderDetail.setOrder(orderOptional.get());
			orderDetail.setProduct(productOptional.get());
			orderDetail.setQuantity(1);
			orderDetail.setPrice(productOptional.get().getPrice());
			orderDetail.setStatus(Status.STATUS_HOAT_DONG);
			Optional<Promotion> promotion = promotionRepo.getPromotionActiveByIdProduct(orderDetail.getProduct().getId());
			if(promotion.isPresent()) {
				orderDetail.setSale(promotion.get().getSale());
			}
			orderDetailRepo.save(orderDetail);
		}
		Order order = orderRepo.findById(createorderdetailDTO.getOrderId()).get();
		return order.getOrderdetail().stream().map(elm->orderDetailMapper.convertToDTO(elm)).collect(Collectors.toList());
	}

	@Override
	public OrderDTO update(OrderDetailDTO orderDetailD) {
		Optional<OrderDetail> orderDetailOp = orderDetailRepo.findById(orderDetailD.getId());
		if(!orderDetailOp.isPresent()){
			throw new AppException(ResponeCustom.MESSAGE_CODE_KHONG_TON_TAI, "Sản phẩm không tồn tại trong giỏ hàng!");
		}
		OrderDetail orderDetail = orderDetailOp.get();

		Integer quantity = orderDetail.getQuantity()-orderDetailD.getQuantity();
		Product product = productRepo.findById(orderDetail.getProduct().getId()).get();
		product.setQuantity(product.getQuantity()+quantity);
		if(product.getQuantity()<0)
			throw new AppException(ResponeCustom.MESSAGE_CODE_KHONG_TON_TAI, "Không đủ số lượng sản phẩm!");

		productRepo.save(product);

		orderDetail.setQuantity(orderDetailD.getQuantity());
		orderDetailRepo.save(orderDetail);
		Order order = orderRepo.findById(orderDetail.getOrder().getId()).get();
		return orderMapper.convertToDTO(refetchOrderTotals(order));
	}

	@Override
	public OrderDTO delete(Integer id) {
		Optional<OrderDetail> orderDetail = orderDetailRepo.findById(id);
		if(!orderDetail.isPresent()){
			throw new AppException(ResponeCustom.MESSAGE_CODE_KHONG_TON_TAI, "Sản phẩm không tồn tại trong order");
		}
		Product product = productRepo.findById(orderDetail.get().getProduct().getId()).get();
		product.setQuantity(product.getQuantity()+orderDetail.get().getQuantity());
		productRepo.save(product);

		orderDetailRepo.delete(orderDetail.get());
		Order order = orderRepo.findById(orderDetail.get().getOrder().getId()).get();
		return orderMapper.convertToDTO(refetchOrderTotals(order));
	}

	@Override
	public OrderDetailDTO updateQuantity(UpdateQuantityOrderDetailRQDTO obj) {
		Optional<OrderDetail> orderDetailOptional = orderDetailRepo.findById(obj.getOrderDetailId());
		if(!orderDetailOptional.isPresent())
			throw new AppException(ResponeCustom.MESSAGE_CODE_KHONG_TON_TAI, "Không tồn tại order");
		OrderDetail orderDetail = orderDetailOptional.get();
		if(obj.getQuantity()>orderDetail.getProduct().getQuantity())
			throw new AppException(ResponeCustom.MESSAGE_CODE_KHONG_TON_TAI, "Không đủ số lượng");
		orderDetail.setQuantity(obj.getQuantity());
		orderDetailRepo.save(orderDetail);
		return orderDetailMapper.convertToDTO(orderDetail);
	}

	public Order refetchOrderTotals(Order order){
		Integer total = 0;
		for ( OrderDetail orderDetail: order.getOrderdetail()) {
			total += orderDetail.getSale() == null? orderDetail.getPrice()*orderDetail.getQuantity() : (orderDetail.getPrice()*orderDetail.getQuantity())/100*(100-orderDetail.getSale());
		}
		System.out.println(total);
		if(order.getTypeorder()!=3){
			total += orderSev.checkShippingPrice(order.getAddress());
		}
		total += total/100*10;
		order.setTotal(total);
		System.out.println(total);
		orderRepo.save(order);
		return order;
	}

}
