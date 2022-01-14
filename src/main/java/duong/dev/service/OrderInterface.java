package duong.dev.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import duong.dev.dto.OrderDTO;
import duong.dev.dto.request.*;
import duong.dev.dto.response.CartdetailSaleResponseDTO;
import duong.dev.dto.response.CountOrderAnhOrderSuccessResponseDTO;
import duong.dev.entity.Order;


public interface OrderInterface {

	public List<OrderDTO> readAll();
	
	public Order create(String address) throws IOException, ServletException;
	
	public OrderDTO update(OrderDTO orderD);
	
	public OrderDTO delete(Order orderE);

	public OrderDTO acceptOrder(Integer orderId, Integer price) ;

	public Order createOnline(String address,String description) throws IOException, ServletException;

	public OrderDTO createDetailOff(CreateOrderTypeDTO createOrderTypeDTO);

	public OrderDTO createDetailOnline(CreateOrderTypeDTO createOrderTypeDTO);

	public List<OrderDTO> findListOrder(String sortStr);

	public List<OrderDTO> searchOrder(String sortStr, String keyword);

	public List<OrderDTO> getListOrder();

	public OrderDTO updateStatusVsAddress(UpdateStatusVsAddressOrderDTO orderDTO);

	public List<OrderDTO> getListOrderByStatus(Integer status,String sort);

	public List<OrderDTO> getListOrderSuccess(String sort);

	public OrderDTO cancelOrder(Integer orderId);

	public OrderDTO updateOrderStatusTransform(Integer orderId);

	public OrderDTO updateOrderStatusFinal(Integer orderId) ;

	public OrderDTO updateOrderStatusNew(Integer orderId);

	public List<CartdetailSaleResponseDTO> createDetail(CreateOrderDTO createOrderDTO) throws IOException, ServletException;

	public List<OrderDTO> findByListOrderNew();

	public List<OrderDTO> finByListOrderMonth();

	public List<OrderDTO> findListOrderHistory() throws IOException, ServletException;

	public OrderDTO createReturnOrder(CreateOrderReturnRequestDTO obj);

	public OrderDTO createOrderDefault() throws IOException, ServletException;

	public List<OrderDTO> findAllOrderDefault();

	public CountOrderAnhOrderSuccessResponseDTO reportCountOrder();

	public Long findQuantityOrderByAccount()throws IOException, ServletException;
}
