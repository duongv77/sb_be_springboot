package duong.dev.service;

import java.io.IOException;

import duong.dev.dto.CartDTO;
import duong.dev.entity.Account;
import duong.dev.entity.Cart;

public interface CartInterface {
	public Cart create(Account account) throws IOException;
	
	public CartDTO delete(Cart cartE) throws IOException;

	public Integer getQuantityProductInCart(Integer id);
}
