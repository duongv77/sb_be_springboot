package duong.dev.controller.admin;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import duong.dev.common.Common;
import duong.dev.common.ResponeCustom;
import duong.dev.dto.ResponseDTO;
import duong.dev.dto.request.UpdateProductDTO;
import duong.dev.service.ProductInterface;

@CrossOrigin(origins = Common.URL)
@RequestMapping("api")
@RestController
public class AdminProductController {
	@Autowired private ProductInterface productSev;
	
	@GetMapping("/v2/admin/product/sort={sort}")
	private ResponseEntity<ResponseDTO<?>> showAll(@PathVariable("sort") String sort) {
		return ResponseEntity.ok(ResponseDTO.builder()
				.messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
				.messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
				.data(productSev.readAll(sort))
				.build());
	}

	@GetMapping("/v2/admin/product/sort={sort}/search={keyword}")
	private ResponseEntity<ResponseDTO<?>> showAll(@PathVariable("sort") String sort, @PathVariable("keyword") String keyword){
		return ResponseEntity.ok(ResponseDTO.builder()
				.messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
				.messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
				.data(productSev.searchProduct(sort, keyword))
				.build());
	}

	@GetMapping("/v2/admin/product/product-rate-report")
	private ResponseEntity<ResponseDTO<?>> productReportRate(){
		return ResponseEntity.ok(ResponseDTO.builder()
				.messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
				.messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
				.data(productSev.readAllCustom())
				.build());
	}

	@GetMapping("/v2/admin/product/{id}")
	private ResponseEntity<ResponseDTO<?>> showDetailProduct(@PathVariable("id") Integer id){
		return ResponseEntity.ok(ResponseDTO.builder()
				.messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
				.messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
				.data(productSev.showDetail(id))
				.build());
	}
	
	@GetMapping("/v2/admin/productOfPromotion/{id}")
	private ResponseEntity<ResponseDTO<?>> showToCategorie(@PathVariable("id") Integer id){
		return ResponseEntity.ok(ResponseDTO.builder()
				.messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
				.messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
				.data(productSev.showToPromotion(id))
				.build());
	}
	
	@GetMapping("/v2/admin/product-blacklist/promotion/{id}")
	private ResponseEntity<ResponseDTO<?>> showToBlackList(@PathVariable("id") Integer id) {
		return ResponseEntity.ok(ResponseDTO.builder()
				.messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
				.messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
				.data(productSev.getListProductToBlackList(id))
				.build());
	}
	
	@PostMapping("/v2/admin/product")
	private ResponseEntity<ResponseDTO<?>> createProduct(@Valid @RequestBody UpdateProductDTO productD){
		return ResponseEntity.ok(ResponseDTO.builder()
				.messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
				.messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
				.data(productSev.create(productD))
				.build());
	}
	
	
	@PutMapping("/v2/admin/product")
	private ResponseEntity<ResponseDTO<?>> update(@Valid @RequestBody UpdateProductDTO productD){
		return ResponseEntity.ok(ResponseDTO.builder()
				.messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
                .messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
                .data(productSev.update(productD))
                .build());			
	}	
	
	@DeleteMapping("/v2/admin/product/{id}")
	private ResponseEntity<ResponseDTO<?>> delete(@PathVariable("id") Integer id){
		return ResponseEntity.ok(ResponseDTO.builder()
				.messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
				.messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
				.data(productSev.delete(id))
				.build());
	}

	@PutMapping("/v2/admin/product-status/{id}")
	private ResponseEntity<ResponseDTO<?>> updateStatus(@PathVariable("id") Integer id) {
		return ResponseEntity.ok(ResponseDTO.builder()
				.messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
				.messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
				.data(productSev.updateStatus(id))
				.build());
	}

}
