package duong.dev.serviceImpl;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import duong.dev.entity.Cartdetail;
import duong.dev.repository.CartdetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import duong.dev.common.Status;
import duong.dev.dto.CartDTO;
import duong.dev.entity.Account;
import duong.dev.entity.Cart;
import duong.dev.mapper.CartMapper;
import duong.dev.repository.CartRepository;
import duong.dev.service.CartInterface;

@Service
public class CartServiceImpl implements CartInterface{
	
	@Autowired private CartRepository cartRepo;
	@Autowired private CartMapper cartMapper;
	@Autowired private CartdetailRepository cartdetailRepository;
	
	@Override
	public Cart create(Account account) {
		Cart cartE = new Cart();
		cartE.setStatus(Status.STATUS_HOAT_DONG);
		cartE.setAccount(account);
		cartRepo.save(cartE);
		return cartE;
	}

	@Override
	public CartDTO delete(Cart cartE) throws IOException {
		cartRepo.delete(cartE);
		return cartMapper.convertToDTO(cartE);
	}

	@Override
	public Integer getQuantityProductInCart(Integer id){
		Optional<List<Cartdetail>> listCartdertailOptionals = cartdetailRepository.findByIdAccounts(id);
		if(!listCartdertailOptionals.isPresent()){
			return 0;
		}
		List<Cartdetail> listCartdertail = listCartdertailOptionals.get();
		Integer quantity = 0;
		for (Cartdetail cartdetail:listCartdertail) {
			quantity +=cartdetail.getQuantity();
		}
		return quantity;
	}

}
