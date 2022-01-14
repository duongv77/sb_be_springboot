package duong.dev.service;

import java.io.IOException;
import java.util.List;

import duong.dev.dto.RateDTO;
import duong.dev.dto.request.CreateRateRequestDTO;
import duong.dev.dto.response.CountCountVoteResponseDTO;

import javax.servlet.ServletException;

public interface RateInterface {
	public List<RateDTO> findByProduct(Integer id, Integer page);

	public CountCountVoteResponseDTO findCountVoteGoodAndAll();

	public RateDTO create(CreateRateRequestDTO rateDTO)  throws IOException, ServletException;
}
