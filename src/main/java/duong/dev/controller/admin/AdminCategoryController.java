package duong.dev.controller.admin;


import java.io.IOException;

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
import duong.dev.dto.AuthorDTO;
import duong.dev.dto.CategorieDTO;
import duong.dev.dto.ResponseDTO;
import duong.dev.dto.request.CategoryRequestDTO;
import duong.dev.entity.Categorie;
import duong.dev.entity.Product;
import duong.dev.service.CategorieInterface;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = Common.URL)
public class AdminCategoryController {
	@Autowired private CategorieInterface cateSev;
	
	@GetMapping("/v2/admin/category/product_{id}")
	private  ResponseEntity<ResponseDTO<?>> createProduct(@PathVariable("id") Product product) throws IOException{
		return ResponseEntity.ok(ResponseDTO.builder()
				.messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
				.messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
				.data(null) //xóa cateproductdetai rồi
				.build());
	}
	
	@GetMapping("/v2/admin/category/sort={sort}/search={keyword}")
	private ResponseEntity<ResponseDTO<?>> showAll(@PathVariable("sort") String sort, @PathVariable("keyword") String keyword){
		return ResponseEntity.ok(ResponseDTO.builder()
				.messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
				.messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
				.data(cateSev.searchCategory(sort, keyword))
				.build());
	}
	
	@GetMapping("/v2/admin/categories-not-promotion/{id}")
	private  ResponseEntity<ResponseDTO<?>> getListCategoriesnotPromotion(@PathVariable("id") Integer id) throws IOException{
		return ResponseEntity.ok(ResponseDTO.builder()
				.messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
				.messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
				.data(cateSev.finListCateNoPromotion(id))
				.build());
	}
	
	@GetMapping("/v2/admin/categories-hight-lv")
	private  ResponseEntity<ResponseDTO<?>> getListCateLV2() throws IOException{
		return ResponseEntity.ok(ResponseDTO.builder()
				.messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
				.messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
				.data(cateSev.listCateLv2())
				.build());
	}
	
	@GetMapping("/v2/admin/bigCategories")
	private  ResponseEntity<ResponseDTO<?>> getListCateLV1() throws IOException{
		return ResponseEntity.ok(ResponseDTO.builder()
				.messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
				.messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
				.data(cateSev.listCateLv1())
				.build());
	}
	
	@GetMapping("/v2/admin/nameCategory")
	private ResponseEntity<ResponseDTO<?>> readNameCategory() throws IOException{
		return ResponseEntity.ok(ResponseDTO.builder()
                .messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
                .messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
                .data(cateSev.readNameCategory())
                .build());
	}
	
	@GetMapping("/v2/admin/typeBigCategory")
	private ResponseEntity<ResponseDTO<?>> chooseTypeCategory() throws IOException{
		return ResponseEntity.ok(ResponseDTO.builder()
                .messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
                .messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
                .data(cateSev.readTypeBigCategory())
                .build()); 
	}
	
	@GetMapping("/v2/admin/category")
	private ResponseEntity<ResponseDTO<?>> readAll() throws IOException{
		return ResponseEntity.ok(ResponseDTO.builder()
                .messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
                .messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
                .data(cateSev.realAll())
                .build());
	}
	
	@PostMapping("/v2/admin/category")
	private ResponseEntity<ResponseDTO<?>> create(@Valid @RequestBody CategoryRequestDTO categoryRequestDTO) throws IOException{
		return ResponseEntity.ok(ResponseDTO.builder()
                .messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
                .messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
                .data(cateSev.createBigcategory(categoryRequestDTO))
                .build());
	}
	
	@PutMapping("/v2/admin/category")
	private ResponseEntity<ResponseDTO<?>> update(@Valid @RequestBody CategoryRequestDTO categoryRequestDTO
			) throws IOException{
		return ResponseEntity.ok(ResponseDTO.builder()
                .messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
                .messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
                .data(cateSev.update(categoryRequestDTO))
                .build());
	}
	
	@DeleteMapping("/v2/admin/category/{id}")
	private ResponseEntity<ResponseDTO<?>> delete(@PathVariable("id") Integer id) throws IOException{
		return ResponseEntity.ok(ResponseDTO.builder()
				.messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
				.messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
				.data(cateSev.delete(id))
				.build());
	}
	
	@PutMapping("/v2/admin/category/updateStatus/{id}")
	private ResponseEntity<ResponseDTO<?>> updateStatus(@PathVariable("id") Integer id) throws IOException{
		return ResponseEntity.ok(ResponseDTO.builder()
				.messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
				.messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
				.data(cateSev.updateStatus(id))
				.build());
	}
	
	@GetMapping("/v2/admin/listSubCategory/{id}")
	private  ResponseEntity<ResponseDTO<?>> getListSubCategory(@PathVariable("id") Integer id) throws IOException{
		return ResponseEntity.ok(ResponseDTO.builder()
				.messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
				.messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
				.data(cateSev.listSubCategory(id))
				.build());
	}
	
	
}
