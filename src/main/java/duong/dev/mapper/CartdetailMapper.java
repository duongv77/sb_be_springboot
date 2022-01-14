package duong.dev.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import duong.dev.dto.CartdetailDTO;
import duong.dev.entity.Cartdetail;

@Component
public class CartdetailMapper {
	@Autowired
	private ModelMapper mapper;
	
	public Cartdetail convertToEntity(CartdetailDTO cartdetailDTO) {
		Cartdetail entity = new Cartdetail();
		mapper.map(cartdetailDTO, entity);
		return entity;
	}
	
	public CartdetailDTO convertToDTO(Cartdetail entity) {
		CartdetailDTO cartdetailDTO = new CartdetailDTO();
		mapper.map(entity, cartdetailDTO);
		return cartdetailDTO;
	}
}
