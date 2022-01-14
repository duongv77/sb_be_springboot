package duong.dev.controller.admin;


import java.io.IOException;

import duong.dev.dto.request.UpdateAccountActivateDTO;
import duong.dev.dto.request.UpdateAccountEmailDTO;
import duong.dev.dto.request.UpdateInformationAdminDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import duong.dev.common.Common;
import duong.dev.common.ResponeCustom;
import duong.dev.dto.AccountDTO;
import duong.dev.dto.ResponseDTO;
import duong.dev.entity.Account;
import duong.dev.serviceImpl.AccountServiceImpl;
import net.bytebuddy.implementation.bytecode.Throw;

import javax.servlet.ServletException;
import javax.validation.Valid;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = Common.URL)
public class AdminAccountController {
	@Autowired private AccountServiceImpl accountSV;
	
	@GetMapping("/v2/admin/account/sort={sort}")
	private ResponseEntity<ResponseDTO<?>> readAll(@PathVariable("sort") String sort)  throws ServletException, IOException{
		return ResponseEntity.ok(ResponseDTO.builder()
                .messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
                .messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
                .data(accountSV.readAll(sort))
                .build());
	}
	
	@GetMapping("/v2/admin/account/sort={sort}/search={keyword}")
	private ResponseEntity<ResponseDTO<?>> showAll(@PathVariable("sort") String sort, @PathVariable("keyword") String keyword){
		return ResponseEntity.ok(ResponseDTO.builder()
				.messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
				.messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
				.data(accountSV.searchAccount(sort, keyword))
				.build());
	}
	
	@GetMapping("/v2/admin/account/{id}")
	private ResponseEntity<ResponseDTO<?>> showDetailAccounts(@PathVariable("id") Integer id) throws IOException{
		return ResponseEntity.ok(ResponseDTO.builder()
                .messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
                .messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
                .data(accountSV.showDetailAccount(id))
                .build());
	}

	@PutMapping("/v2/admin/account-update-email")
	private ResponseEntity<ResponseDTO<?>> updateEmail(@RequestBody UpdateAccountEmailDTO accountUpdateEmail) {
		return ResponseEntity.ok(ResponseDTO.builder()
				.messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
				.messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
				.data(accountSV.updateEmail(accountUpdateEmail))
				.build());
	}

	@PutMapping("/v2/supper_admin/account-update-status")
	private ResponseEntity<ResponseDTO<?>> updateStatus(@RequestBody UpdateAccountActivateDTO accountUpdateStatus) {
		return ResponseEntity.ok(ResponseDTO.builder()
				.messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
				.messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
				.data(accountSV.updateStatus(accountUpdateStatus))
				.build());
	}

	@DeleteMapping("/v2/supper_admin/account/{id}")
	private ResponseEntity<ResponseDTO<?>> deleteAccount(@PathVariable Integer id) {
		return ResponseEntity.ok(ResponseDTO.builder()
				.messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
				.messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
				.data(accountSV.delete(id))
				.build());
	}

	@PostMapping("/v2/supper_admin/account/{id}")
	private ResponseEntity<ResponseDTO<?>> createAccount(@PathVariable Integer id) {
		return ResponseEntity.ok(ResponseDTO.builder()
				.messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
				.messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
				.data(accountSV.delete(id))
				.build());
	}
	
	@PutMapping("/v2/admin/updateInformation")
	private ResponseEntity<ResponseDTO<?>> updateInformationAdmin(
			@Valid @RequestBody UpdateInformationAdminDTO updateInformationAdminDTO) throws IOException {
		return ResponseEntity.ok(ResponseDTO.builder()
				.messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
				.messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
				.data(accountSV.updateInformation(updateInformationAdminDTO))
				.build());
	}
	
}
