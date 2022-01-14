package duong.dev.controller.admin;

import java.io.IOException;

import duong.dev.dto.request.CreateRoleAccountDTO;
import duong.dev.service.RoleAccountInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import duong.dev.common.Common;
import duong.dev.common.ResponeCustom;
import duong.dev.dto.ResponseDTO;
import duong.dev.serviceImpl.RoleAccountServiceImpl;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = Common.URL)
public class AdminRoleAccountController {
	@Autowired private RoleAccountInterface roleAccountSev;
	
	@GetMapping("/v2/admin/roleAccount")
	private ResponseEntity<ResponseDTO<?>> readAll() throws IOException{
		return ResponseEntity.ok(ResponseDTO.builder()
                .messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
                .messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
                .data(roleAccountSev.readAll())
                .build());
	}

	@PostMapping("/v2/supper_admin/roleAccount")
	private ResponseEntity<ResponseDTO<?>> changeRoleAccount(@RequestBody CreateRoleAccountDTO createRoleAccount) throws IOException{
		return ResponseEntity.ok(ResponseDTO.builder()
				.messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
				.messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
				.data(roleAccountSev.changeRole(createRoleAccount))
				.build());
	}
}
