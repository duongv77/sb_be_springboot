package duong.dev.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import duong.dev.dto.StatusOrderDTO;
import duong.dev.entity.StatusOrder;

@Component
public class StatusOrderMapper {
	@Autowired
	private ModelMapper mapper;
	
	//convert từ DTO về entity
	public StatusOrder convertToEntity(StatusOrderDTO statusOrderDTO) {
		StatusOrder entity = new StatusOrder();
		mapper.map(statusOrderDTO, entity);
		return entity;
	}
	
	//convert từ entity về DTO
	public StatusOrderDTO convertToDTO(StatusOrder entity) {
		StatusOrderDTO statusOrderDTO = new StatusOrderDTO();
		mapper.map(entity, statusOrderDTO);
		return statusOrderDTO;
	}
}
