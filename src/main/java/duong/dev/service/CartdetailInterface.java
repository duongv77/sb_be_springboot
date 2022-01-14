package duong.dev.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import duong.dev.dto.CartdetailDTO;
import duong.dev.dto.OrderDetailDTO;
import duong.dev.dto.request.UpdateOrderdetailQuantityRequestDTO;
import duong.dev.dto.request.UpdateQuantityCartdetailDTO;
import duong.dev.dto.response.CartdetailSaleResponseDTO;
import duong.dev.entity.Cartdetail;
import duong.dev.entity.Product;

public interface CartdetailInterface {
	
	public List<CartdetailSaleResponseDTO> readAll() throws IOException, ServletException;
	
	public CartdetailDTO create(Product product) throws IOException, ServletException;
	
	public CartdetailDTO update(CartdetailDTO cartDetaiDTO);

	public CartdetailDTO delete(Cartdetail cartdetail) throws IOException;
	
	public List<CartdetailDTO> deleteAll(List<Cartdetail> cartdetail) throws IOException;

	public CartdetailSaleResponseDTO updateQuantity(UpdateQuantityCartdetailDTO updateQuantityCartdetailDTO);

	public CartdetailDTO updateOrderDetailQuantity(UpdateOrderdetailQuantityRequestDTO orderDetail) throws IOException, ServletException ;

	public Long quantityProductInCart() throws IOException, ServletException ;

}
