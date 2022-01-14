package duong.dev.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import duong.dev.dto.PromotionCategorieDTO;
import duong.dev.entity.PromotionCategorie;
@Component
public class PromotionCategorieMapper {
	@Autowired
	private ModelMapper mapper;
	
	public PromotionCategorie convertToEntity(PromotionCategorieDTO dto) {
		PromotionCategorie entity = new PromotionCategorie();
		mapper.map(dto, entity);
		return entity;
	}
	
	public PromotionCategorieDTO convertToDTO(PromotionCategorie entity) {
		PromotionCategorieDTO dto = new PromotionCategorieDTO();
		mapper.map(entity, dto);
		return dto;
	}
}
