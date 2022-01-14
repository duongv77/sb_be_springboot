package duong.dev.controller.website;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import duong.dev.common.Common;
import duong.dev.common.ResponeCustom;
import duong.dev.dto.ResponseDTO;
import duong.dev.entity.Account;
import duong.dev.serviceImpl.PasswordHistoryServiceImpl;
import duong.dev.serviceImpl.StatusOrderServiceImpl;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = Common.URL)
public class StatusOrderController {
	@Autowired private StatusOrderServiceImpl statusOrderSV;
	@Autowired private PasswordHistoryServiceImpl passwordHistoryServiceImpl;
	
	@GetMapping("/v1/status_order")
	private ResponseEntity<ResponseDTO<?>> showAll() throws IOException {
		return ResponseEntity.ok(ResponseDTO.builder()
                .messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
                .messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
                .data(statusOrderSV.readAll())
                .build());
	}
	
	@GetMapping("/v1/test/account_{id}")
	private ResponseEntity<ResponseDTO<?>> test(@PathVariable("id") Account account) throws IOException {
		return ResponseEntity.ok(ResponseDTO.builder()
                .messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
                .messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
                .data(passwordHistoryServiceImpl.updateStatus(account))
                .build());
	}
}
