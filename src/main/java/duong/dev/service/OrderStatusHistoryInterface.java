package duong.dev.service;

import java.io.IOException;
import java.util.List;

import duong.dev.dto.OrderStatusHistoryDTO;
import duong.dev.entity.Order;
import duong.dev.entity.OrderStatusHistory;


public interface OrderStatusHistoryInterface {

	public List<OrderStatusHistoryDTO> readAll();
	
	public OrderStatusHistory create1(Order order);

	public OrderStatusHistory create2(Order order);

	public OrderStatusHistory create3(Order order);

	public OrderStatusHistory create4(Order order);

	public OrderStatusHistory create5(Order order);

	public OrderStatusHistory create6(Order order);

	public OrderStatusHistoryDTO update(OrderStatusHistoryDTO orderStatusHistoryD);
	
	public OrderStatusHistoryDTO delete(OrderStatusHistory orderStatusHistoryE);
}
