package duong.dev.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import duong.dev.dto.RegionDTO;
import duong.dev.entity.Region;

@Component
public class RegionMapper {
	@Autowired
	private ModelMapper mapper;
	
	public Region convertToEntity(RegionDTO dto) {
		Region entity = new Region();
		mapper.map(dto, entity);
		return entity;
	}
	
	public RegionDTO convertToDTO(Region entity) {
		RegionDTO dto = new RegionDTO();
		mapper.map(entity, dto);
		return dto;
	}
}
