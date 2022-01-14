package duong.dev.serviceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import duong.dev.common.CommonObject;
import duong.dev.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import duong.dev.dto.OrderStatusHistoryDTO;
import duong.dev.entity.OrderStatusHistory;
import duong.dev.mapper.OrderStatusHistoryMapper;
import duong.dev.repository.OrderStatusHistoryRepository;
import duong.dev.service.OrderStatusHistoryInterface;





@Service
public class OrderStatusHistoryServiceImpl implements  OrderStatusHistoryInterface {

	@Autowired
	private OrderStatusHistoryRepository orderStatusHistoryRepo;
	@Autowired
	OrderStatusHistoryMapper OrderStatusHistoryMapper;

	public List<OrderStatusHistoryDTO> convertListDTO(List<OrderStatusHistory> listOrderStatusHistoryE) {
		List<OrderStatusHistoryDTO> listOrderStatusHistoryD = new ArrayList<OrderStatusHistoryDTO>();
		for (OrderStatusHistory orderStatusHistoryE : listOrderStatusHistoryE) {
			listOrderStatusHistoryD.add(OrderStatusHistoryMapper.convertToDTO(orderStatusHistoryE));
		}
		return listOrderStatusHistoryD;
	}

	@Override
	public List<OrderStatusHistoryDTO> readAll(){
		List<OrderStatusHistory> listOrderStatusHistoryE = orderStatusHistoryRepo.findAll();
		return convertListDTO(listOrderStatusHistoryE);

	}

	@Override
	public OrderStatusHistory create1(Order order){
		OrderStatusHistory orderStatusHistory = new OrderStatusHistory();
		orderStatusHistory.setOrder(order);
		orderStatusHistory.setStatusorder(CommonObject.STATUS_ORDER_CHO_XAC_NHAN);
		orderStatusHistoryRepo.save(orderStatusHistory);
		return orderStatusHistory;
	}

	@Override
	public OrderStatusHistory create2(Order order){
		OrderStatusHistory orderStatusHistory = new OrderStatusHistory();
		orderStatusHistory.setOrder(order);
		orderStatusHistory.setStatusorder(CommonObject.STATUS_ORDER_DA_XAC_NHAN);
		orderStatusHistoryRepo.save(orderStatusHistory);
		return orderStatusHistory;
	}

	@Override
	public OrderStatusHistory create3(Order order){
		OrderStatusHistory orderStatusHistory = new OrderStatusHistory();
		orderStatusHistory.setOrder(order);
		orderStatusHistory.setStatusorder(CommonObject.STATUS_ORDER_DANG_GIAO_HANG);
		orderStatusHistoryRepo.save(orderStatusHistory);
		return orderStatusHistory;
	}

	@Override
	public OrderStatusHistory create4(Order order){
		OrderStatusHistory orderStatusHistory = new OrderStatusHistory();
		orderStatusHistory.setOrder(order);
		orderStatusHistory.setStatusorder(CommonObject.STATUS_ORDER_DA_GIAO_HANG);
		orderStatusHistoryRepo.save(orderStatusHistory);
		return orderStatusHistory;
	}

	@Override
	public OrderStatusHistory create5(Order order){
		OrderStatusHistory orderStatusHistory = new OrderStatusHistory();
		orderStatusHistory.setOrder(order);
		orderStatusHistory.setStatusorder(CommonObject.STATUS_ORDER_DA_HUY);
		orderStatusHistoryRepo.save(orderStatusHistory);
		return orderStatusHistory;
	}

	@Override
	public OrderStatusHistory create6(Order order) {
		OrderStatusHistory orderStatusHistory = new OrderStatusHistory();
		orderStatusHistory.setOrder(order);
		orderStatusHistory.setStatusorder(CommonObject.STATUS_ORDER_YEU_CAU_TRA_HANG);
		orderStatusHistoryRepo.save(orderStatusHistory);
		return orderStatusHistory;
	}


	@Override
	public OrderStatusHistoryDTO delete(OrderStatusHistory orderStatusHistoryE){
		orderStatusHistoryRepo.delete(orderStatusHistoryE);
		return OrderStatusHistoryMapper.convertToDTO(orderStatusHistoryE);
	}
	
	@Override
	public OrderStatusHistoryDTO update(OrderStatusHistoryDTO orderStatusHistoryD){
		return null;
	}

}
