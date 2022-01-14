package duong.dev.controller.website;

import java.io.IOException;

import duong.dev.service.CategorieInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import duong.dev.common.Common;
import duong.dev.common.ResponeCustom;
import duong.dev.dto.ResponseDTO;
import duong.dev.serviceImpl.CategorieServiceImpl;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = Common.URL)
public class CategoryController {
	@Autowired private CategorieInterface categorySvImpl;
	
	@GetMapping("/v1/category_big")
	private ResponseEntity<ResponseDTO<?>> getCategoryBig() throws IOException {
		return ResponseEntity.ok(ResponseDTO.builder()
                .messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
                .messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
                .data(categorySvImpl.readCateoryBig())
                .build());
	}
	
	@GetMapping("/v1/category/detail/{id}")
	private ResponseEntity<ResponseDTO<?>> getCategoryDetails(@PathVariable("id") Integer id) throws IOException {
		return ResponseEntity.ok(ResponseDTO.builder()
                .messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
                .messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
                .data(categorySvImpl.readCateoryByCateGoryId(id))
                .build());
	}

	@GetMapping("/v1/category/show-nav")
	private ResponseEntity<ResponseDTO<?>> getCategoryShowNav() throws IOException {
		return ResponseEntity.ok(ResponseDTO.builder()
				.messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
				.messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
				.data(categorySvImpl.readCateoryShowNav())
				.build());
	}

	@GetMapping("/v1/category/list-cate/lv2")
	private ResponseEntity<ResponseDTO<?>> findListCateLV2() throws IOException {
		return ResponseEntity.ok(ResponseDTO.builder()
				.messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
				.messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
				.data(categorySvImpl.listCateLv2())
				.build());
	}
}
