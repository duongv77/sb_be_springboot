package duong.dev.service;

import java.util.List;

import duong.dev.dto.TitleDTO;
import duong.dev.dto.request.TitleProductDTO;
import duong.dev.dto.request.TitleUpdateRequestDTO;

public interface TitleInterface {
	public List<TitleDTO> showAll();

	public List<TitleProductDTO> findAll();

	public TitleDTO createTitle(TitleDTO titleD);

	public TitleProductDTO updateTitle(TitleDTO titleD);

	public boolean deleteTitle(Integer id);

	public TitleProductDTO updateStatus(TitleUpdateRequestDTO obj);
}
