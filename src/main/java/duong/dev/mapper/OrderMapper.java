package duong.dev.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import duong.dev.dto.OrderDTO;
import duong.dev.entity.Order;

@Component
public class OrderMapper {
	
	@Autowired
	private ModelMapper mapper;
	
	//convert từ DTO về entity
	public Order convertToEntity(OrderDTO orderDTO) {
		Order entity = new Order();
		mapper.map(orderDTO, entity);
		return entity;
	}
	
	//convert từ entity về DTO
	public OrderDTO convertToDTO(Order entity) {
		OrderDTO orderDTO = new OrderDTO();
		mapper.map(entity, orderDTO);
		return orderDTO;
	}
}
