package duong.dev.service;

import java.util.List;

import duong.dev.dto.OrderDTO;
import duong.dev.dto.OrderDetailDTO;
import duong.dev.dto.request.CreateOrderdetailDTO;
import duong.dev.dto.request.UpdateQuantityOrderDetailRQDTO;


public interface OrderDetailInterface {

	public List<OrderDetailDTO> readAll();

	public List<OrderDetailDTO> findAllByOrderId(Integer orderId);

	public OrderDTO create(CreateOrderdetailDTO createorderdetailDTO);

	public List<OrderDetailDTO> create2(CreateOrderdetailDTO createorderdetailDTO) ;

	public OrderDTO update(OrderDetailDTO orderDetailD);
	
	public OrderDTO delete(Integer id);

	public OrderDetailDTO updateQuantity(UpdateQuantityOrderDetailRQDTO obj);
}
