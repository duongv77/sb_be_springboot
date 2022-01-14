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
import duong.dev.dto.PromotionDTO;
import duong.dev.dto.ResponseDTO;
import duong.dev.dto.request.UpdatePromotionDTO;
import duong.dev.service.PromotionInterface;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = Common.URL)
public class AdminPromotionController {
	@Autowired private PromotionInterface promoSer;
	
	@GetMapping("/v2/admin/promotion/sort={sort}")
	private ResponseEntity<ResponseDTO<?>> showAll(@PathVariable("sort") String sort) throws IOException{
		return ResponseEntity.ok(ResponseDTO.builder()
				.messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
				.messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
				.data(promoSer.readAll(sort))
				.build());
	}

	@GetMapping("/v2/admin/promotion-count")
	private ResponseEntity<ResponseDTO<?>> findCountPromotion(){
		return ResponseEntity.ok(ResponseDTO.builder()
				.messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
				.messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
				.data(promoSer.findCountPromotion())
				.build());
	}
	
	@GetMapping("/v2/admin/promotion/sort={sort}/search={keyword}")
	private ResponseEntity<ResponseDTO<?>> showAll(@PathVariable("sort") String sort, @PathVariable("keyword") String keyword){
		return ResponseEntity.ok(ResponseDTO.builder()
				.messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
				.messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
				.data(promoSer.searchPromotion(sort, keyword))
				.build());
	}
	
	@GetMapping("/v2/admin/promotion/{id}")
	private ResponseEntity<ResponseDTO<?>> showDetail(@PathVariable("id") Integer id) throws IOException{
		return ResponseEntity.ok(ResponseDTO.builder()
				.messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
				.messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
				.data(promoSer.showDetail(id))
				.build());
	}
	
	@PutMapping("/v2/admin/promotion")
	private ResponseEntity<ResponseDTO<?>> update(@Valid @RequestBody UpdatePromotionDTO promotionDTO) throws IOException{
		return ResponseEntity.ok(ResponseDTO.builder()
				.messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
				.messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
				.data(promoSer.update(promotionDTO))
				.build());
	}
	
	@PutMapping("/v2/admin/stop/{id}")
	private ResponseEntity<ResponseDTO<?>> updateStop(@PathVariable("id") Integer id) {
		return ResponseEntity.ok(ResponseDTO.builder()
				.messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
				.messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
				.data(promoSer.stop(id))
				.build());
	}
	
	@PutMapping("/v2/admin/pause/{id}")
	private ResponseEntity<ResponseDTO<?>> updatePause(@PathVariable("id") Integer id) {
		return ResponseEntity.ok(ResponseDTO.builder()
				.messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
				.messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
				.data(promoSer.pause(id))
				.build());
	}
	
	@PutMapping("/v2/admin/play/{id}")
	private ResponseEntity<ResponseDTO<?>> updatePlay(@PathVariable("id") Integer id) {
		return ResponseEntity.ok(ResponseDTO.builder()
				.messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
				.messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
				.data(promoSer.play(id))
				.build());
	}
	
	@PostMapping("/v2/admin/promotion")
	private ResponseEntity<ResponseDTO<?>> create(@Valid @RequestBody PromotionDTO promotion) {
		return ResponseEntity.ok(ResponseDTO.builder()
				.messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
				.messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
				.data(promoSer.create(promotion))
				.build());
	}
	
	@DeleteMapping("/v2/admin/promotion/{id}")
	private ResponseEntity<ResponseDTO<?>> create(@PathVariable("id") Integer id) {
		return ResponseEntity.ok(ResponseDTO.builder()
				.messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
				.messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
				.data(promoSer.delete(id))
				.build());
	}
}
