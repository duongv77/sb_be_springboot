package duong.dev.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import duong.dev.dto.PromotionBlackListDTO;
import duong.dev.entity.PromotionBlackList;

@Component
public class PromotionBlackListMapper {
	@Autowired
	private ModelMapper mapper;
	
	public PromotionBlackList convertToEntity(PromotionBlackListDTO dto) {
		PromotionBlackList entity = new PromotionBlackList();
		mapper.map(dto, entity);
		return entity;
	}
	
	public PromotionBlackListDTO convertToDTO(PromotionBlackList entity) {
		PromotionBlackListDTO dto = new PromotionBlackListDTO();
		mapper.map(entity, dto);
		return dto;
	}
}
