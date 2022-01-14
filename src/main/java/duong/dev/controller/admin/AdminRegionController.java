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
import duong.dev.dto.AuthorDTO;
import duong.dev.dto.ResponseDTO;
import duong.dev.serviceImpl.RegionServiceImpl;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = Common.URL)
public class AdminRegionController {
	@Autowired private RegionServiceImpl regionServiceImpl;
	
	@PostMapping("/v2/admin/region")
	private ResponseEntity<ResponseDTO<?>> create(@Valid @RequestBody AuthorDTO authorD) throws IOException{
		return ResponseEntity.ok(ResponseDTO.builder()
                .messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
                .messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
                .data(regionServiceImpl.getAll())
                .build());
	}
}
