package duong.dev.controller.website;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import duong.dev.common.Common;
import duong.dev.common.ResponeCustom;
import duong.dev.dto.ResponseDTO;
import duong.dev.serviceImpl.TitleServiceImpl;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = Common.URL)
public class TitleController {
	@Autowired private TitleServiceImpl titleSVImpl;
	
	@GetMapping("/v1/title")
	private ResponseEntity<ResponseDTO<?>> showAll() throws IOException {
		return ResponseEntity.ok(ResponseDTO.builder()
                .messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
                .messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
                .data(titleSVImpl.showAll())
                .build());
	}
}
