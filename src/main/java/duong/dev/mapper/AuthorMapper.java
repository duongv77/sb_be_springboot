package duong.dev.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import duong.dev.dto.AuthorDTO;
import duong.dev.entity.Author;

@Component
public class AuthorMapper {
	@Autowired
	private ModelMapper mapper;
	
	public Author convertToEntity(AuthorDTO authorDTO) {
		Author entity = new Author();
		mapper.map(authorDTO, entity);
		return entity;
	}
	
	public AuthorDTO convertToDTO(Author entity) {
		AuthorDTO authorDTO = new AuthorDTO();
		mapper.map(entity, authorDTO);
		return authorDTO;
	}
}
