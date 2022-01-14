package duong.dev.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import duong.dev.dto.RateDTO;
import duong.dev.entity.Rate;

@Component
public class RateMapper {
	@Autowired
	private ModelMapper mapper;
	
	//convert từ DTO về entity
	public Rate convertToEntity(RateDTO rateDTO) {
		Rate entity = new Rate();
		mapper.map(rateDTO, entity);
		return entity;
	}
	
	//convert từ entity về DTO
	public RateDTO convertToDTO(Rate entity) {
		RateDTO rateDTO = new RateDTO();
		mapper.map(entity, rateDTO);
		return rateDTO;
	}
}
