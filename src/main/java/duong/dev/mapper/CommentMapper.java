package duong.dev.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import duong.dev.dto.CommentDTO;
import duong.dev.entity.Comment;


@Component
public class CommentMapper {
	@Autowired
	private ModelMapper mapper;
	
	//convert từ DTO về entity
	public Comment convertToEntity(CommentDTO commentDTO) {
		Comment entity = new Comment();
		mapper.map(commentDTO, entity);
		return entity;
	}
	
	//convert từ entity về DTO
	public CommentDTO convertToDTO(Comment entity) {
		CommentDTO categorieDTO = new CommentDTO();
		mapper.map(entity, categorieDTO);
		return categorieDTO;
	}
}
