package duong.dev.controller.admin;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import duong.dev.common.Common;
import duong.dev.common.ResponeCustom;
import duong.dev.dto.ResponseDTO;
import duong.dev.dto.request.CreatePromotionCategoriesDTO;
import duong.dev.serviceImpl.PromotionCategorieServiceImpl;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = Common.URL)
public class AdminPromotionCategorieController {
	@Autowired private PromotionCategorieServiceImpl promoCateSev;
	
	@DeleteMapping("/v2/admin/promotion-categorie/{id}")
	private ResponseEntity<ResponseDTO<?>> showAll(@PathVariable("id") Integer id) throws IOException{
		return ResponseEntity.ok(ResponseDTO.builder()
				.messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
				.messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
				.data(promoCateSev.delete(id))
				.build());
	}
	
	@PostMapping("/v2/admin/promotion-categories")
	private ResponseEntity<ResponseDTO<?>> create(@Valid @RequestBody CreatePromotionCategoriesDTO dto) throws IOException{
		return ResponseEntity.ok(ResponseDTO.builder()
				.messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
				.messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
				.data(promoCateSev.create(dto))
				.build());
	}
}
