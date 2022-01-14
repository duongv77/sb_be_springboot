package duong.dev.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import duong.dev.dto.CartDTO;
import duong.dev.entity.Cart;

@Component
public class CartMapper {
	@Autowired
	private ModelMapper mapper;
	
	//convert từ DTO về entity
	public Cart convertToEntity(CartDTO cartDTO) {
		Cart entity = new Cart();
		mapper.map(cartDTO, entity);
		return entity;
	}
	
	//convert từ entity về DTO
	public CartDTO convertToDTO(Cart entity) {
		CartDTO cartDTO = new CartDTO();
		mapper.map(entity, cartDTO);
		return cartDTO;
	}
}
