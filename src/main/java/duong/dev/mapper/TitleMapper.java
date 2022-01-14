package duong.dev.mapper;

import duong.dev.dto.request.TitleProductDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import duong.dev.dto.TitleDTO;
import duong.dev.entity.Title;

@Component
public class TitleMapper {
	@Autowired
	private ModelMapper mapper;
	
	public Title convertToEntity(TitleDTO dto) {
		Title entity = new Title();
		mapper.map(dto, entity);
		return entity;
	}
	
	public TitleDTO convertToDTO(Title entity) {
		TitleDTO dto = new TitleDTO();
		mapper.map(entity, dto);
		return dto;
	}

	public TitleProductDTO convertToDTO2(Title entity) {
		TitleProductDTO dto = new TitleProductDTO();
		mapper.map(entity, dto);
		return dto;
	}
}
