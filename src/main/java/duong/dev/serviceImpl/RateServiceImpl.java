package duong.dev.serviceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import duong.dev.common.ResponeCustom;
import duong.dev.common.Status;
import duong.dev.dto.request.CreateRateRequestDTO;
import duong.dev.dto.response.CountCountVoteResponseDTO;
import duong.dev.entity.Account;
import duong.dev.entity.Product;
import duong.dev.exception.AppException;
import duong.dev.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import duong.dev.dto.RateDTO;
import duong.dev.entity.Rate;
import duong.dev.mapper.RateMapper;
import duong.dev.repository.RateRepository;
import duong.dev.service.RateInterface;

import javax.servlet.ServletException;

@Service
public class RateServiceImpl implements RateInterface {
	@Autowired
	private RateRepository rateRepo;
	@Autowired
	private RateMapper rateMapper;
	@Autowired
	private OrderServiceImpl orderService;
	@Autowired
	private ProductRepository productRepo;
	@Autowired
	private TurnVoteServiceImpl turnvoteSev;

	public Account getAccount() throws IOException, ServletException {
		return orderService.getAccount();
	}
	
	public List<RateDTO> convertListDTO(List<Rate> listRateE ) {
		List<RateDTO> listRateD = new ArrayList<RateDTO>();
		for(Rate rateE : listRateE ) {
			listRateD.add(rateMapper.convertToDTO(rateE));
		}
		return listRateD;
	}

	@Override
	public List<RateDTO> findByProduct(Integer id, Integer page) {
		Sort sort = Sort.by("createDate").descending();
		Pageable pageable = PageRequest.of(page, 4, sort);
		List<Rate> listRateOptional = rateRepo.findListRateByProductId(id, pageable);
		return convertListDTO(listRateOptional);
	}

	@Override
	public CountCountVoteResponseDTO findCountVoteGoodAndAll() {
		Long countVoteGood = rateRepo.rateCountGood();
		Long countVoteAll= rateRepo.rateCountAll();
		return new CountCountVoteResponseDTO(countVoteGood, countVoteAll);
	}

	@Override
	public RateDTO create(CreateRateRequestDTO rateDTO)  throws IOException, ServletException {
		Optional<Product> productOptional = productRepo.findById(rateDTO.getProductId());
		if(!productOptional.isPresent())
			throw new AppException(ResponeCustom.MESSAGE_CODE_KHONG_TON_TAI, "Sản phẩm không tồn tại");
		turnvoteSev.updateQuantityMinus(rateDTO.getProductId());
		Rate rate = new Rate();
		rate.setVote(rateDTO.getRate());
		rate.setAccount(getAccount());
		rate.setComment(rateDTO.getComment());
		rate.setStatus(Status.STATUS_HOAT_DONG);
		rate.setProduct(productOptional.get());
		rateRepo.save(rate);
		return rateMapper.convertToDTO(rate);
	}


}
