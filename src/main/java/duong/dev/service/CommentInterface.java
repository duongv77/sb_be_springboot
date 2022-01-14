package duong.dev.service;

import java.io.IOException;
import java.util.List;

import duong.dev.dto.CommentDTO;
import duong.dev.dto.request.CreateCommentRequestDTO;
import duong.dev.entity.Comment;


public interface CommentInterface {

	public List<CommentDTO> readAll() throws IOException;

	public List<CommentDTO> findListCommentToProductId(Integer id);
	
	public CommentDTO create(CreateCommentRequestDTO commentD);
	
	public CommentDTO update(CommentDTO commentD) throws IOException;
	
	public CommentDTO delete(Comment commentE) throws IOException ;

	public Long findCountComment();
}
