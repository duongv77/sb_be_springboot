package duong.dev.controller.website;


import javax.servlet.ServletException;
import javax.validation.Valid;

import duong.dev.dto.request.UpdatePasswordRequestDTO;
import duong.dev.dto.request.UpdatePhotoRequestDTO;
import duong.dev.dto.request.UpdateProfileRequestDTO;
import duong.dev.service.AccountInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import duong.dev.common.Common;
import duong.dev.common.ResponeCustom;
import duong.dev.dto.LoginDTO;
import duong.dev.dto.ResponseDTO;
import duong.dev.serviceImpl.AccountServiceImpl;

import java.io.IOException;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = Common.URL)
public class AccountController {
	@Autowired private AccountInterface accountSV;
	
	@PostMapping("/v1/account")
	private ResponseEntity<ResponseDTO<?>> createAccount(@RequestBody @Valid LoginDTO accountDTO){
		return ResponseEntity.ok(ResponseDTO.builder()
                .messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
                .messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
                .data(accountSV.createAccountLogin(accountDTO))
                .build());
	}


	
	@PostMapping("/v1/login")
	private ResponseEntity<ResponseDTO<?>> login(@RequestBody @Valid LoginDTO loginDTO) throws Exception{
		return ResponseEntity.ok(ResponseDTO.builder()
                .messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
                .messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
                .data(accountSV.login(loginDTO))
                .build());
	}
	
	@GetMapping("/v1/forget_password/{email}")
	private ResponseEntity<ResponseDTO<?>> forgetPassword(@PathVariable("email") String email){
		return ResponseEntity.ok(ResponseDTO.builder()
                .messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
                .messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
                .data(accountSV.forgetPassword(email))
                .build());
	}
	
	@PostMapping("/v1/change/password/{key}")
	private ResponseEntity<ResponseDTO<?>> restartPassword(@PathVariable("key") String key, @RequestBody @Valid LoginDTO changePw){
		return ResponseEntity.ok(ResponseDTO.builder()
                .messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
                .messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
                .data(accountSV.updatePassword(changePw,key))
                .build());
	}
	
	@GetMapping("/v1/restart/password/account_{id}")
	private ResponseEntity<ResponseDTO<?>> restartPasswordGetAccount(@PathVariable("id") Integer  id) {
		return ResponseEntity.ok(ResponseDTO.builder()
                .messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
                .messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
                .data(accountSV.getAccountForgetPasword(id))
                .build());
	}
	
	@GetMapping("/v2/user")
	private ResponseEntity<ResponseDTO<?>> get() throws Exception{
		return ResponseEntity.ok(ResponseDTO.builder()
                .messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
                .messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
                .data(accountSV.getAccount())
                .build());
	}
	
	@GetMapping("/v2/admin/info")
	private ResponseEntity<ResponseDTO<?>> getInfoAdmin() throws Exception{
		return ResponseEntity.ok(ResponseDTO.builder()
                .messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
                .messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
                .data(accountSV.getAccount())
                .build());
	}


	@PutMapping("/v2/user/update-profile")
	private ResponseEntity<ResponseDTO<?>> updateProfile(@RequestBody @Valid UpdateProfileRequestDTO obj){
		return ResponseEntity.ok(ResponseDTO.builder()
				.messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
				.messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
				.data(accountSV.updateProfile(obj))
				.build());
	}

	@PutMapping("/v2/user/update-password")
	private ResponseEntity<ResponseDTO<?>> updatePassword(@RequestBody @Valid UpdatePasswordRequestDTO obj){
		return ResponseEntity.ok(ResponseDTO.builder()
				.messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
				.messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
				.data(accountSV.updatePasswordProfile(obj))
				.build());
	}

	@PutMapping("/v2/user/update-photo")
	private ResponseEntity<ResponseDTO<?>> updatePhoto(@RequestBody @Valid UpdatePhotoRequestDTO photo)throws ServletException, IOException{
		return ResponseEntity.ok(ResponseDTO.builder()
				.messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
				.messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
				.data(accountSV.updatePhoto(photo))
				.build());
	}

}
