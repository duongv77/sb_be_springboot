package duong.dev.controller.website;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.validation.Valid;

import duong.dev.dto.request.UpdateOrderdetailQuantityRequestDTO;
import duong.dev.service.CartdetailInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import duong.dev.JwtTokenUtil;
import duong.dev.common.Common;
import duong.dev.common.ResponeCustom;
import duong.dev.dto.ResponseDTO;
import duong.dev.dto.request.UpdateQuantityCartdetailDTO;

import duong.dev.entity.Cartdetail;
import duong.dev.entity.Product;
import duong.dev.mapper.CartdetailMapper;
import duong.dev.repository.ProductRepository;
import duong.dev.serviceImpl.CartdetailServiceImpl;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = Common.URL)
public class CartDetailController {
	@Autowired
	CartdetailInterface cartdetailImpl;

	@GetMapping("/v2/user/cartdetail")
	private ResponseEntity<ResponseDTO<?>> readAll() throws IOException, ServletException {
		return ResponseEntity.ok(ResponseDTO.builder().messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
				.messageName(ResponeCustom.MESSAGE_NAME_SUCCESS).data(cartdetailImpl.readAll()).build());
	}

	@GetMapping("/v2/user/addtocart/product/{id}")
	private ResponseEntity<ResponseDTO<?>> addToCart(@PathVariable("id") Product product)
			throws IOException, ServletException {
		return ResponseEntity.ok(ResponseDTO.builder().messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
				.messageName(ResponeCustom.MESSAGE_NAME_SUCCESS).data(cartdetailImpl.create(product)).build());
	}

	@GetMapping("/v2/user/cart-detail/product-quantity")
	private ResponseEntity<ResponseDTO<?>> addToCart() throws IOException, ServletException {
		return ResponseEntity.ok(ResponseDTO.builder()
				.messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
				.messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
				.data(cartdetailImpl.quantityProductInCart())
				.build());
	}
	

	@DeleteMapping("/v2/user/cartdetail/{id}")
	private ResponseEntity<ResponseDTO<?>> deleteProductInCart(@PathVariable("id") Cartdetail cartdetail) throws IOException{
		return ResponseEntity.ok(ResponseDTO.builder()
                .messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
                .messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
                .data(cartdetailImpl.delete(cartdetail))
                .build());
	}

	@PutMapping("/v2/user/cartdetail/quantity")
	private ResponseEntity<ResponseDTO<?>> updateQuantityCartdetail(
			@RequestBody @Valid UpdateQuantityCartdetailDTO updateQuantityCartdetailDTO) throws IOException, ServletException {
		return ResponseEntity.ok(ResponseDTO.builder().messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
				.messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
				.data(cartdetailImpl.updateQuantity(updateQuantityCartdetailDTO)).build());
	}

	@PostMapping("/v2/user/cart-detail")
	private ResponseEntity<ResponseDTO<?>> updateOrderdetailQuantity( @RequestBody @Valid UpdateOrderdetailQuantityRequestDTO createOrderDTO )  throws IOException, ServletException {
		return ResponseEntity.ok(ResponseDTO.builder()
				.messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
				.messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
				.data(cartdetailImpl.updateOrderDetailQuantity(createOrderDTO))
				.build());
	}

}
