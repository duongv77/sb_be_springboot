package duong.dev.controller.website;

import java.io.IOException;

import duong.dev.dto.request.UpdatePhotoRequestDTO;
import duong.dev.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import duong.dev.common.Common;
import duong.dev.common.ResponeCustom;
import duong.dev.dto.ResponseDTO;
import duong.dev.service.ProductInterface;

import javax.servlet.ServletException;
import javax.validation.Valid;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = Common.URL)
public class ProductController {
	@Autowired private ProductInterface productSev;
	
	@GetMapping("/v1/user/product-favorite")
	private ResponseEntity<ResponseDTO<?>> findListProductFavourites()throws ServletException, IOException{
		return ResponseEntity.ok(ResponseDTO.builder()
				.messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
				.messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
				.data(productSev.findListProductFavourites())
				.build());
	}

	@GetMapping("/v1/product-related/page={page}/{id}")
	private ResponseEntity<ResponseDTO<?>> showProductRelated(@PathVariable("id") Product product, @PathVariable("page") Integer page)throws IOException{
		return ResponseEntity.ok(ResponseDTO.builder()
				.messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
				.messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
				.data(productSev.showProductRelated(product, page))
				.build());
	}

	@GetMapping("/v1/product-related-category/page={page}/product-id={id}")
	private ResponseEntity<ResponseDTO<?>> showProductRelatedCategory(@PathVariable("id") Product product, @PathVariable("page") Integer page) throws IOException{
		return ResponseEntity.ok(ResponseDTO.builder()
				.messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
				.messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
				.data(productSev.showProductRelatedCategory(product, page))
				.build());
	}
	
	@GetMapping("/v1/user/product/{id}")
	private ResponseEntity<ResponseDTO<?>> getOnly(@PathVariable Integer id) throws IOException{
		return ResponseEntity.ok(ResponseDTO.builder()
				.messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
				.messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
				.data(productSev.getOnly(id))
				.build());
	}
	
	@GetMapping("/v1/product-and-sale")
	private ResponseEntity<ResponseDTO<?>> listProductAndSale() throws IOException{
		return ResponseEntity.ok(ResponseDTO.builder()
				.messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
				.messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
				.data(productSev.getListProductVsSale())
				.build());
	}

	@GetMapping("/v1/product-and-sale/search={keyword}")
	private ResponseEntity<ResponseDTO<?>> searchListProductAndSale(@PathVariable("keyword") String keyword) throws IOException{
		return ResponseEntity.ok(ResponseDTO.builder()
				.messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
				.messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
				.data(productSev.getSearchListProductVsSale(keyword))
				.build());
	}

	@GetMapping("/v1/product-and-sale/product-new")
	private ResponseEntity<ResponseDTO<?>> top10ProductNews(){
		return ResponseEntity.ok(ResponseDTO.builder()
				.messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
				.messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
				.data(productSev.top10ProductNews())
				.build());
	}

	@GetMapping("/v1/product-and-sale/product-sale")
	private ResponseEntity<ResponseDTO<?>> top10ProductSales(){
		return ResponseEntity.ok(ResponseDTO.builder()
				.messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
				.messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
				.data(productSev.top10ProductSale())
				.build());
	}

	@GetMapping("/v1/product-and-sale/product-trending")
	private ResponseEntity<ResponseDTO<?>> top10ProductTrending(){
		return ResponseEntity.ok(ResponseDTO.builder()
				.messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
				.messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
				.data(productSev.top10ProductTrending())
				.build());
	}

	@GetMapping("/v1/product-and-sale/product-banchay")
	private ResponseEntity<ResponseDTO<?>> top10ProductBanChay(){
		return ResponseEntity.ok(ResponseDTO.builder()
				.messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
				.messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
				.data(productSev.top10ProductBanChay())
				.build());
	}

	@GetMapping("/v1/product/find-list-Language")
	private ResponseEntity<ResponseDTO<?>> findListLanguage(){
		return ResponseEntity.ok(ResponseDTO.builder()
				.messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
				.messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
				.data(productSev.findListLanguage())
				.build());
	}

	@GetMapping("/v1/product/find-list-nxb")
	private ResponseEntity<ResponseDTO<?>> findListNxb(){
		return ResponseEntity.ok(ResponseDTO.builder()
				.messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
				.messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
				.data(productSev.findListNxb())
				.build());
	}

	@GetMapping("/v1/product/find-list-product/category-lv1")
	private ResponseEntity<ResponseDTO<?>> findListToCateLV1(){
		return ResponseEntity.ok(ResponseDTO.builder()
				.messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
				.messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
				.data(productSev.findListToCateLV1())
				.build());
	}

	@GetMapping("/v1/product/sort={sort}")
	private ResponseEntity<ResponseDTO<?>> showAll(@PathVariable("sort") String sort) {
		return ResponseEntity.ok(ResponseDTO.builder()
				.messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
				.messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
				.data(productSev.readAllSortUser(sort))
				.build());
	}

	@PostMapping("/v1/product/search-form-custom")
	private ResponseEntity<ResponseDTO<?>> findCustom(@RequestBody @Valid UpdatePhotoRequestDTO obj) {
		return ResponseEntity.ok(ResponseDTO.builder()
				.messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
				.messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
				.data(productSev.findListCustomForm(obj.getPhoto()))
				.build());
	}
}
