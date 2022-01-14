package duong.dev.controller.admin;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import duong.dev.common.Common;
import duong.dev.common.ResponeCustom;
import duong.dev.dto.ResponseDTO;
import duong.dev.dto.request.CreatePromotionBlackListDTO;
import duong.dev.service.PromotionBlackListInterface;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = Common.URL)
public class PromotionBlackListController {
	@Autowired private PromotionBlackListInterface promotionBlSev;
	
	@PostMapping("/v2/admin/promotion-blacklist")
	private ResponseEntity<ResponseDTO<?>> create(@Valid @RequestBody CreatePromotionBlackListDTO dto) throws IOException{
		return ResponseEntity.ok(ResponseDTO.builder()
                .messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
                .messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
                .data(promotionBlSev.create(dto))
                .build());
	}
	
	@PostMapping("/v2/admin/delete-product-blacklist")
	private ResponseEntity<ResponseDTO<?>> deleteToBlackList(@Valid @RequestBody CreatePromotionBlackListDTO dto) throws IOException{
		return ResponseEntity.ok(ResponseDTO.builder()
                .messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
                .messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
                .data(promotionBlSev.delete(dto))
                .build());
	}
}
