package duong.dev.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import duong.dev.dto.OrderDetailDTO;
import duong.dev.entity.OrderDetail;

@Component
public class OrderDetailMapper {

	@Autowired
	private ModelMapper mapper;
	
	//convert từ DTO về entity
	public OrderDetail convertToEntity(OrderDetailDTO orderDetailDTO) {
		OrderDetail entity = new OrderDetail();
		mapper.map(orderDetailDTO, entity);
		return entity;
	}
	
	//convert từ entity về DTO
	public OrderDetailDTO convertToDTO(OrderDetail entity) {
		OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
		mapper.map(entity, orderDetailDTO);
		return orderDetailDTO;
	}
}
