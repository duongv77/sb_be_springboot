package duong.dev.service;

import java.io.IOException;
import java.util.List;

import duong.dev.dto.AccountDTO;
import duong.dev.dto.request.UpdateAccountActivateDTO;
import duong.dev.dto.request.UpdateAccountEmailDTO;
import duong.dev.dto.request.UpdateInformationAdminDTO;
import duong.dev.dto.AccountTokenDTO;
import duong.dev.dto.LoginDTO;
import duong.dev.dto.request.*;
import org.springframework.http.ResponseEntity;

import javax.servlet.ServletException;

public interface AccountInterface {
	
	public List<AccountDTO> readAll(String sort) throws ServletException, IOException;
	
	public AccountDTO create(AccountDTO accountD) throws IOException;
	
	public List<AccountDTO> searchAccount(String sort, String keyword);
	
	public AccountDTO delete(Integer id);

	public AccountDTO updateEmail(UpdateAccountEmailDTO accountEmail);

	public AccountDTO updateStatus(UpdateAccountActivateDTO accountActivate);
	
	public AccountDTO updateInformation(UpdateInformationAdminDTO updateInformationAdminDTO);

	public AccountDTO createAccountLogin(LoginDTO accountD);

	public AccountTokenDTO login(LoginDTO accounLogin) throws Exception;

	public ResponseEntity<?> forgetPassword(String email);

	public ResponseEntity<?> updatePassword(LoginDTO accountChangPw, String key);

	public AccountDTO getAccountForgetPasword(Integer id);

	public AccountDTO getAccount() throws ServletException, IOException;

	public AccountDTO updateProfile(UpdateProfileRequestDTO obj);

	public boolean updatePasswordProfile(UpdatePasswordRequestDTO obj);

	public AccountDTO updatePhoto(UpdatePhotoRequestDTO photo) throws ServletException, IOException;

}
