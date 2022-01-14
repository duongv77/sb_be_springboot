package duong.dev.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import duong.dev.dto.OrderStatusHistoryDTO;
import duong.dev.entity.OrderStatusHistory;



@Component
public class OrderStatusHistoryMapper {
	
	@Autowired
	private ModelMapper mapper;
	
	//convert từ DTO về entity
	public OrderStatusHistory convertToEntity(OrderStatusHistoryDTO orderStatusHistoryDTO) {
		OrderStatusHistory entity = new OrderStatusHistory();
		mapper.map(orderStatusHistoryDTO, entity);
		return entity;
	}
	
	//convert từ entity về DTO
	public OrderStatusHistoryDTO convertToDTO(OrderStatusHistory entity) {
		OrderStatusHistoryDTO orderStatusHistoryDTO = new OrderStatusHistoryDTO();
		mapper.map(entity, orderStatusHistoryDTO);
		return orderStatusHistoryDTO;
	}
}
