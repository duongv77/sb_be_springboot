package duong.dev.service;

import java.io.IOException;
import java.util.List;

import duong.dev.dto.AuthorDTO;

public interface AuthorInterface {
	public List<AuthorDTO> readAll(String sort) throws IOException;
	
	public AuthorDTO create(AuthorDTO authorD) throws IOException;
	
	public AuthorDTO update(AuthorDTO authorD) throws IOException;

	public List<AuthorDTO> searchAuthor(String sort, String keyword);

	public AuthorDTO delete(Integer id) throws IOException;
	
	public AuthorDTO updateStatus(Integer id);
}
