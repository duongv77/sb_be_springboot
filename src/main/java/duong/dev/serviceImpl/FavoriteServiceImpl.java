package duong.dev.serviceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import duong.dev.common.ResponeCustom;
import duong.dev.common.Status;
import duong.dev.dto.request.CreateFavoriteRequestDTO;
import duong.dev.entity.Account;
import duong.dev.entity.Product;
import duong.dev.exception.AppException;
import duong.dev.repository.AccountRepository;
import duong.dev.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import duong.dev.dto.FavoriteDTO;
import duong.dev.entity.Favorite;
import duong.dev.mapper.FavoriteMapper;
import duong.dev.repository.FavoriteRepository;
import duong.dev.service.FavoriteInterface;

import javax.servlet.ServletException;


@Service
public class FavoriteServiceImpl implements  FavoriteInterface {
	@Autowired
	private FavoriteRepository favoriteRepo;
	@Autowired
	FavoriteMapper favoriteMapper;
	@Autowired private ProductRepository productRepo;
	@Autowired private AccountRepository accountRepo;
	@Autowired private OrderServiceImpl accountSev;

	public List<FavoriteDTO> convertListDTO(List<Favorite> listFavoriteE) {
		List<FavoriteDTO> listFavoriteD = new ArrayList<FavoriteDTO>();
		for (Favorite favoriteE : listFavoriteE) {
			listFavoriteD.add(favoriteMapper.convertToDTO(favoriteE));
		}
		return listFavoriteD;
	}

	@Override
	public List<FavoriteDTO> readAll() throws IOException {
		List<Favorite> listFavoriteE = favoriteRepo.findAll();
		return convertListDTO(listFavoriteE);

	}

	@Override
	public FavoriteDTO create(CreateFavoriteRequestDTO favoriteD) throws IOException, ServletException {
		Optional<Product> productOptional = productRepo.findById(favoriteD.getProductId());
		if(!productOptional.isPresent())
			throw new AppException(ResponeCustom.MESSAGE_CODE_KHONG_TON_TAI, "Sản phẩm không tồn tại");
		Account account = accountSev.getAccount();
		Optional<Favorite> favoriteOptional = favoriteRepo.findByAccountAndProduct(account.getId(), productOptional.get().getId());
		if(favoriteOptional.isPresent())
			throw new AppException(ResponeCustom.MESSAGE_CODE_KHONG_TON_TAI, "Sản phẩm đã tồn tại trong danh sách yêu thích");
		Favorite favoriteE = new Favorite();
		favoriteE.setStatus(Status.STATUS_HOAT_DONG);
		favoriteE.setProduct(productOptional.get());
		favoriteE.setAccount(account);
		favoriteRepo.save(favoriteE);
		return favoriteMapper.convertToDTO(favoriteE);
	}


	@Override
	public FavoriteDTO delete(Favorite favoriteE) throws IOException {
		favoriteRepo.delete(favoriteE);
		return favoriteMapper.convertToDTO(favoriteE);
	}

}
