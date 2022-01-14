package duong.dev.serviceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.ServletException;

import duong.dev.common.Common;
import duong.dev.dto.AccountDTO;
import duong.dev.dto.OrderDetailDTO;
import duong.dev.dto.request.UpdateOrderdetailQuantityRequestDTO;
import duong.dev.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import duong.dev.JwtTokenUtil;
import duong.dev.dto.CartdetailDTO;
import duong.dev.common.ResponeCustom;
import duong.dev.dto.request.UpdateQuantityCartdetailDTO;
import duong.dev.dto.response.CartdetailSaleResponseDTO;
import duong.dev.entity.Account;
import duong.dev.entity.Cart;
import duong.dev.entity.Cartdetail;
import duong.dev.entity.Product;
import duong.dev.entity.Promotion;
import duong.dev.exception.AppException;
import duong.dev.mapper.AccountMapper;
import duong.dev.mapper.CartdetailMapper;
import duong.dev.repository.CartRepository;
import duong.dev.repository.CartdetailRepository;
import duong.dev.repository.PromotionRepository;
import duong.dev.service.CartdetailInterface;

@Service
public class CartdetailServiceImpl implements CartdetailInterface{
	@Autowired JwtTokenUtil jwtToken;
	@Autowired AccountMapper accountMapper;
	@Autowired CartRepository cartRepo;
	@Autowired private CartdetailRepository cartdetailRepo;
	@Autowired private CartdetailMapper cartdetailMapper;
	@Autowired private PromotionRepository promotionRepo;
	@Autowired private ProductRepository productRepo;
	
	@Override
	public CartdetailDTO create(Product product) throws IOException, ServletException {
		Account account = accountMapper.convertToEntity(jwtToken.getUserToToken());
		Cart cart = this.cartRepo.findByIdAccount(account.getId());
		Optional<Cartdetail> cartdetailOptional = cartdetailRepo.findCartDetailByProductAndCart(product.getId(), cart.getId());
		if( !cartdetailOptional.isPresent()) {
			Cartdetail cartdetail = new Cartdetail();
			cartdetail.setCart(cart);
			cartdetail.setProduct(product);
			cartdetail.setQuantity(1);
			this.cartdetailRepo.save(cartdetail);
			return cartdetailMapper.convertToDTO(cartdetail);
		}else {
			Cartdetail cartdetail = cartdetailOptional.get();
			cartdetail.setQuantity(cartdetail.getQuantity() +1);
			if(cartdetail.getQuantity()>product.getQuantity())
				throw new AppException(ResponeCustom.MESSAGE_CODE_KHONG_TON_TAI, "Sản phẩm trong giỏ hàng vượt quá số lượng cho phép!");
			this.cartdetailRepo.save(cartdetail);
			return cartdetailMapper.convertToDTO(cartdetail);
		}
	}

	@Override
	public CartdetailDTO update(CartdetailDTO cartDetaiDTO){
		cartdetailRepo.save(cartdetailMapper.convertToEntity(cartDetaiDTO));
		return cartDetaiDTO;
	}

	@Override
	public CartdetailDTO delete(Cartdetail cartdetailE){
		cartdetailRepo.delete(cartdetailE);
		return cartdetailMapper.convertToDTO(cartdetailE);
	}
	
	public List<CartdetailDTO> convertListDTO(List<Cartdetail> listcartdetailE) {
		List<CartdetailDTO> listcartdetailD = new ArrayList<CartdetailDTO>();
		for (Cartdetail cartdetailE : listcartdetailE) {
			listcartdetailD.add(cartdetailMapper.convertToDTO(cartdetailE));
		}
		return listcartdetailD;
	}
	
	public List<Cartdetail> convertListEntity(List<CartdetailDTO> listcartdetailDTO) {
//		List<Cartdetail> listcartdetailE = new ArrayList<Cartdetail>();
//		for (CartdetailDTO cartdetailD : listcartdetailDTO) {
//			listcartdetailE.add(cartdetailMapper.convertToEntity(cartdetailD));
//		}
		return listcartdetailDTO.stream().map(elm->cartdetailMapper.convertToEntity(elm)).collect(Collectors.toList());
	}


	@Override
	public List<CartdetailSaleResponseDTO> readAll() throws IOException, ServletException {
		Cart cart = getCartByAccount();
		List<Cartdetail> list = cartdetailRepo.findCartDetailByIdCart(cart.getId());
		List<CartdetailSaleResponseDTO> listCartdetailCustom = new ArrayList<CartdetailSaleResponseDTO>();
		for (Cartdetail cartdetail : list) {
			CartdetailSaleResponseDTO newE = new CartdetailSaleResponseDTO();
			Optional<Promotion> promotion = promotionRepo.getPromotionActiveByIdProduct(cartdetail.getProduct().getId());
			if(promotion.isPresent()) newE.setSale(promotion.get().getSale());
			newE.setCartdetail(cartdetailMapper.convertToDTO(cartdetail));
			listCartdetailCustom.add(newE);
		}
		return listCartdetailCustom;
	}


	@Override
	public List<CartdetailDTO> deleteAll(List<Cartdetail> cartdetail) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CartdetailDTO updateOrderDetailQuantity(UpdateOrderdetailQuantityRequestDTO orderDetail)throws IOException, ServletException  {
		Optional<Product> productOptional = productRepo.findById(orderDetail.getProductId());
		if(!productOptional.isPresent()){
			throw new AppException(ResponeCustom.MESSAGE_CODE_KHONG_TON_TAI, "Sản phẩm không tồn tại!");
		}
		Cart cart = getCartByAccount();

		Optional<Cartdetail> cartdetailOptional = cartdetailRepo.findCartDetailByProductAndCart(productOptional.get().getId(), cart.getId());
		if(cartdetailOptional.isPresent()){
			Cartdetail cartdetail = cartdetailOptional.get();
			cartdetail.setQuantity(cartdetail.getQuantity()+orderDetail.getQuantity());
			if(cartdetail.getQuantity()>productOptional.get().getQuantity())
				throw new AppException(ResponeCustom.MESSAGE_CODE_KHONG_TON_TAI, "Sản phẩm trong giỏ hàng vượt quá số lượng cho phép!");
			cartdetailRepo.save(cartdetail);
			return cartdetailMapper.convertToDTO(cartdetail);
		}else{
			Cartdetail cartdetail = new Cartdetail();
			cartdetail .setQuantity(orderDetail.getQuantity());
			cartdetail.setCart(cart);
			cartdetail.setProduct(productOptional.get());
			cartdetailRepo.save(cartdetail);
			return cartdetailMapper.convertToDTO(cartdetail);
		}
	}

	@Override
	public Long quantityProductInCart() throws IOException, ServletException {
		AccountDTO accountDTO = jwtToken.getUserToToken();
		return cartdetailRepo.quantityProductCart(accountDTO.getId());
	}


	@Override
	public CartdetailSaleResponseDTO updateQuantity(UpdateQuantityCartdetailDTO updateQuantityCartdetailDTO) {
		Optional<Cartdetail> cartdetail = cartdetailRepo.findById(updateQuantityCartdetailDTO.getId());
		if(!cartdetail.isPresent())
			throw new AppException(ResponeCustom.MESSAGE_CODE_KHONG_TON_TAI, "Cartdetail không tồn tại!");
		Optional<Product> productOptional = productRepo.findById(cartdetail.get().getProduct().getId());
		if(!productOptional.isPresent())
			throw new AppException(ResponeCustom.MESSAGE_CODE_KHONG_TON_TAI, "Sản phẩm không tồn tại!");
		if(productOptional.get().getQuantity()<updateQuantityCartdetailDTO.getQuantity())
			throw new AppException(ResponeCustom.MESSAGE_CODE_KHONG_THANH_CONG, "Không còn đủ số lượng!");
		CartdetailSaleResponseDTO cartdetailSaleResponseDTO = new CartdetailSaleResponseDTO();
		Cartdetail cartdetail2 = cartdetail.get();
		cartdetail2.setQuantity(updateQuantityCartdetailDTO.getQuantity());
		cartdetailRepo.save(cartdetail2);
		Optional<Promotion> promotion = promotionRepo.getPromotionActiveByIdProduct(cartdetail2.getProduct().getId());
		if(promotion.isPresent()) cartdetailSaleResponseDTO.setSale(promotion.get().getSale());
		cartdetailSaleResponseDTO.setCartdetail(cartdetailMapper.convertToDTO(cartdetail2));
		return cartdetailSaleResponseDTO;
	}
	
	public List<CartdetailDTO> deleteAllCartdetailByCart() throws IOException, ServletException {
		Cart cart = getCartByAccount();
		List<Cartdetail> listCartdetail = cartdetailRepo.findByCart(cart);
		cartdetailRepo.deleteAll(listCartdetail);
		return null;
	}
	
	public Cart getCartByAccount() throws IOException, ServletException{
		Account account = accountMapper.convertToEntity(jwtToken.getUserToToken());
		Cart cart = this.cartRepo.findByIdAccount(account.getId());
		return cart;
	}

}
