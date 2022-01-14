package duong.dev.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import duong.dev.dto.PromotionDTO;
import duong.dev.entity.Promotion;

@Component
public class PromotionMapper {
	@Autowired
	private ModelMapper mapper;
	
	//convert từ DTO về entity
	public Promotion convertToEntity(PromotionDTO promotionDTO) {
		Promotion entity = new Promotion();
		mapper.map(promotionDTO, entity);
		return entity;
	}
	
	//convert từ entity về DTO
	public PromotionDTO convertToDTO(Promotion entity) {
		PromotionDTO promotionDTO = new PromotionDTO();
		mapper.map(entity, promotionDTO);
		return promotionDTO;
	}
}
