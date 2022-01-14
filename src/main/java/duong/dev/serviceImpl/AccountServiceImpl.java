package duong.dev.serviceImpl;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.servlet.ServletException;
import duong.dev.dto.request.UpdateAccountActivateDTO;
import duong.dev.dto.request.UpdateAccountEmailDTO;

import duong.dev.dto.request.UpdateInformationAdminDTO;
import duong.dev.dto.request.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import duong.dev.JwtTokenUtil;
import duong.dev.common.CommonObject;
import duong.dev.common.ResponeCustom;
import duong.dev.common.Status;
import duong.dev.dto.AccountDTO;
import duong.dev.dto.AccountTokenDTO;
import duong.dev.dto.LoginDTO;
import duong.dev.dto.OrderDTO;
import duong.dev.dto.ProductDTO;
import duong.dev.entity.Account;
import duong.dev.entity.Order;
import duong.dev.entity.PasswordHistory;
import duong.dev.entity.Product;
import duong.dev.entity.RoleAccount;
import duong.dev.exception.AppException;
import duong.dev.libs.HashUtil;
import duong.dev.mapper.AccountMapper;
import duong.dev.repository.AccountRepository;
import duong.dev.service.AccountInterface;
import duong.dev.util.JwtUserDetailsService;
import duong.dev.util.Roandom;
import duong.dev.util.SendMail;

@Service
public class AccountServiceImpl implements AccountInterface {
	@Autowired
	private AccountMapper accountMapper;
	@Autowired
	private AccountRepository accountRepo;
	@Autowired
	private JwtUserDetailsService userDetailsService;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private RoleAccountServiceImpl accountServiceImpl;
	@Autowired
	private PasswordHistoryServiceImpl passwordHistoryServiceImpl;
	@Autowired
	private CartServiceImpl cartServiceImpl;
	@Autowired
	private OrderServiceImpl orderService;

	public List<AccountDTO> convertListDTO(List<Account> listAccountE) {
		List<AccountDTO> listAccountD = new ArrayList<AccountDTO>();
		for (Account accountE : listAccountE) {
			listAccountD.add(accountMapper.convertToDTO(accountE));
		}
		return listAccountD;
	}

	@Override
	public List<AccountDTO> readAll(String sortStr) throws ServletException, IOException {
		Sort sort = Sort.by(sortStr).ascending();
		Account account = accountRepo.findById(getAccount().getId()).get();
		boolean check = false;
		for (RoleAccount roleAccount : account.getRoleAccount()) {
			if (roleAccount.getRole().getId() == 1) {
				check = true;
			}
		}
		List<Account> listAccountE = new ArrayList<>();
		if (check) {
			listAccountE = accountRepo.findAll(sort);
		} else {
			listAccountE = accountRepo.findListAccountNoSupperAdmin();
		}
		return convertListDTO(listAccountE);
	}


	@Override
	public List<AccountDTO> searchAccount(String sort, String keyword) {
		if (sort.equals("name") || sort.equals("title")) {
			Sort sort2 = Sort.by(sort).ascending();
			List<Account> listAccountE = accountRepo.searchAccount(keyword, sort2);
			return convertListDTO(listAccountE);
		}
		Sort sort2 = Sort.by(sort).descending();
		List<Account> listAccountE = new ArrayList<>();
		if (keyword.trim().equals("")) {
			listAccountE = accountRepo.findAll(sort2);
		} else {
			listAccountE = accountRepo.searchAccount(keyword, sort2);

		}
		return convertListDTO(listAccountE);
	}

	@Override
	public AccountDTO create(AccountDTO accountD) throws IOException {
		accountD.setActivated(Status.STATUS_HOAT_DONG);
		Account accountE = accountMapper.convertToEntity(accountD);
		accountRepo.save(accountE);
		return accountMapper.convertToDTO(accountE);
	}

	// tạo user đăng nhập chưa có thông tin
	@Override
	public AccountDTO createAccountLogin(LoginDTO accountD) {
		if(accountRepo.findByUsername(accountD.getUsername())!=null)
			throw new AppException(ResponeCustom.MESSAGE_CODE_DA_TON_TAI, "Số điện thoại đã được sử dụng");
		if(accountRepo.findByEmail(accountD.getEmail())!=null)
			throw new AppException(ResponeCustom.MESSAGE_CODE_DA_TON_TAI, "Email đã được sử dụng");

		Account accountE = new Account();
		accountE.setUsername(accountD.getUsername());
		accountE.setPhone(accountD.getUsername());
		accountE.setActivated(Status.STATUS_HOAT_DONG);
		accountE.setPassword(HashUtil.hash(accountD.getPassword()));
		accountE.setFullname(accountD.getFullname());
		accountE.setEmail(accountD.getEmail());
		accountE.setPhoto(Status.PHOTO_AVT);
		accountRepo.save(accountE);

		cartServiceImpl.create(accountE);

		RoleAccount roleAccount = new RoleAccount();
		roleAccount.setAccount(accountE);
		roleAccount.setRole(CommonObject.ROLE_USER);
		accountServiceImpl.getAccountcreate(roleAccount);

		SendMail.senMaiChaoMung(accountE.getUsername(), accountD.getPassword(), accountE.getEmail());

		return accountMapper.convertToDTO(accountE);
	}

	@Override
	public AccountDTO delete(Integer id) {
		Optional<Account> accountOp = accountRepo.findById(id);
		if (!accountOp.isPresent())
			throw new AppException(ResponeCustom.MESSAGE_CODE_KHONG_TON_TAI, "Account không tồn tại");
		Account account = accountOp.get();
		if (account.getRoleAccount().size() != 0 || account.getCart() != null || account.getAddress().size() != 0) {
			throw new AppException(ResponeCustom.MESSAGE_CODE_KHONG_THANH_CONG, "Không thể xóa account!");
		}
		try {
			accountRepo.delete(accountOp.get());
		} catch (Exception e) {
			throw new AppException(ResponeCustom.MESSAGE_CODE_KHONG_THANH_CONG, "Không thể xóa account!");
		}
		return accountMapper.convertToDTO(accountOp.get());
	}

	@Override
	public AccountDTO updateEmail(UpdateAccountEmailDTO accountEmail) {
		Optional<Account> accountOp = accountRepo.findById(accountEmail.getAccountId());
		if (!accountOp.isPresent())
			throw new AppException(ResponeCustom.MESSAGE_CODE_KHONG_TON_TAI, "Account không tồn tại");
		Account account = accountOp.get();
		account.setEmail(accountEmail.getEmail());
		accountRepo.save(account);
		return accountMapper.convertToDTO(account);
	}

	@Override
	public AccountDTO updateStatus(UpdateAccountActivateDTO accountActivate) {
		Optional<Account> accountOp = accountRepo.findById(accountActivate.getId());
		if (!accountOp.isPresent())
			throw new AppException(ResponeCustom.MESSAGE_CODE_KHONG_TON_TAI, "Account không tồn tại");
		Account account = accountOp.get();
		account.setActivated(accountActivate.getStatus());
		accountRepo.save(account);
		return accountMapper.convertToDTO(account);
	}

	@Override
	public ResponseEntity<?> updatePassword(LoginDTO accountChangPw, String key) {
		Account accountE = accountRepo.findByUsername(accountChangPw.getUsername());
		if (accountE == null)
			throw new AppException(ResponeCustom.MESSAGE_CODE_KHONG_TON_TAI, "Account không tồn tại");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		PasswordHistory passwordHistory = passwordHistoryServiceImpl.get(accountE);
		if (passwordHistory == null)
			throw new AppException(ResponeCustom.MESSAGE_CODE_KHONG_THANH_CONG, "Xảy ra lỗi");
		LocalDateTime dateTime = LocalDateTime.parse(passwordHistory.getDatetime(), formatter);
		LocalDateTime timeNow = java.time.LocalDateTime.now();
		long betweenTime = java.time.Duration.between(timeNow, dateTime).toMinutes();
		if (!key.equalsIgnoreCase(passwordHistory.getCodeComfirm()))
			throw new AppException(ResponeCustom.MESSAGE_CODE_KHONG_THANH_CONG, "Bạn không có quyền đổi mật khẩu này!");
		if (betweenTime < 0)
			throw new AppException(ResponeCustom.MESSAGE_CODE_KHONG_THANH_CONG, "Đã hết thời gian đổi mật khẩu");
		accountE.setPassword(HashUtil.hash(accountChangPw.getPassword()));
		accountRepo.save(accountE);
		passwordHistoryServiceImpl.deleteAllOff(accountE);
		passwordHistoryServiceImpl.updateStatus(accountE);
		return null;
	}

	@Override
	public ResponseEntity<?> forgetPassword(String email) {
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		Account account = accountRepo.findByEmail(email);
		if (account == null)
			throw new AppException(ResponeCustom.MESSAGE_CODE_KHONG_TON_TAI, "Account không tồn tại");

		String strRoandom = Roandom.randomNumberAndString();

		String endUrl = account.getId() + "/" + strRoandom;

		SendMail.forgetPassword(email, endUrl);

		passwordHistoryServiceImpl.deleteAllOff(account);
		passwordHistoryServiceImpl.create(account, strRoandom);
		return null;
	}

	@Override
	public AccountDTO getAccountForgetPasword(Integer id) {
		Optional<Account> accountOp = accountRepo.findById(id);
		if (!accountOp.isPresent())
			throw new AppException(ResponeCustom.MESSAGE_CODE_KHONG_TON_TAI, "Account không tồn tại");

		Account account = accountOp.get();
		AccountDTO accountDTO = new AccountDTO();
		accountDTO.setId(account.getId());
		accountDTO.setFullname(account.getFullname() == null ? account.getUsername() : account.getFullname());
		accountDTO.setPhoto(account.getPhoto());
		accountDTO.setUsername(account.getUsername());
		return accountDTO;
	}

	public AccountDTO updateProfile(AccountDTO accountD) throws IOException {
		Account accountE = accountRepo.getById(accountD.getId());
		if (accountE == null)
			return null;
		accountD.setPassword(accountE.getPassword());
		accountRepo.save(accountMapper.convertToEntity(accountD));
		return accountMapper.convertToDTO(accountE);
	}

	@Override
	public AccountTokenDTO login(LoginDTO accounLogin) throws Exception {
		Account account = accountRepo.findByUsername(accounLogin.getUsername());
		if (account == null)
			throw new AppException(ResponeCustom.MESSAGE_CODE_KHONG_TON_TAI, "Account không tồn tại");
		if (!HashUtil.verifile(accounLogin.getPassword(), account.getPassword()))
			throw new AppException(ResponeCustom.MESSAGE_CODE_KHONG_CHINH_XAC, "Mật khẩu không chính xác");
		if (account.getActivated() == Status.STATUS_KHONG_HOAT_DONG)
			throw new AppException(ResponeCustom.MESSAGE_CODE_KHONG_THANH_CONG, "Tài khoản của bạn đã bị khóa");

		authenticate(accounLogin.getUsername(), accounLogin.getPassword());
		final UserDetails userDetails = userDetailsService.loadUserByUsername(accounLogin.getUsername());

		AccountDTO accountDTO = accountMapper.convertToDTO(account);
		AccountTokenDTO accountTokenDTO = new AccountTokenDTO();

		final String token = jwtTokenUtil.generateToken(userDetails);

		accountTokenDTO.setId(accountDTO.getId());
		accountTokenDTO.setAccessToken(token);
		accountTokenDTO.setFullname(accountDTO.getFullname());
		accountTokenDTO.setImage(account.getPhoto());
		accountTokenDTO.setUsername(account.getUsername());
		accountTokenDTO.setRoleAccount(accountServiceImpl.convertListDTO(account.getRoleAccount()));
		return accountTokenDTO;
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}

	@Override
	public AccountDTO getAccount() throws ServletException, IOException {
		AccountDTO accountDTO = jwtTokenUtil.getUserToToken();
		Optional<Account> account = accountRepo.findById(accountDTO.getId());
		if (!account.isPresent()) {
			throw new AppException(ResponeCustom.MESSAGE_CODE_KHONG_TON_TAI, "Account không tồn tại");
		}
		account.get().setPassword(null);
		return accountMapper.convertToDTO(account.get());
	}

	@Override
	public AccountDTO updateProfile(UpdateProfileRequestDTO obj) {
		Optional<Account> accountOptional = accountRepo.findById(obj.getId());
		if (!accountOptional.isPresent()) {
			throw new AppException(ResponeCustom.MESSAGE_CODE_KHONG_TON_TAI, "Account không tồn tại");
		}
		Account account = accountOptional.get();
		account.setEmail(obj.getEmail());
		account.setFullname(obj.getFullname());
		account.setPhone(obj.getPhone());
		accountRepo.save(account);
		return accountMapper.convertToDTO(account);
	}

	@Override
	public boolean updatePasswordProfile(UpdatePasswordRequestDTO obj) {
		Optional<Account> accountOptional = accountRepo.findById(obj.getId());
		if (!accountOptional.isPresent()) {
			throw new AppException(ResponeCustom.MESSAGE_CODE_KHONG_TON_TAI, "Account không tồn tại");
		}
		Account account = accountOptional.get();
		if (!HashUtil.verifile(obj.getPasswordOld(), account.getPassword()))
			throw new AppException(ResponeCustom.MESSAGE_CODE_KHONG_CHINH_XAC, "Mật khẩu không chính xác");
		account.setPassword(HashUtil.hash(obj.getPasswordNew()));
		accountRepo.save(account);
		return true;
	}

	@Override
	public AccountDTO updatePhoto(UpdatePhotoRequestDTO photo) throws ServletException, IOException {
		Account account = orderService.getAccount();
		account.setPhoto(photo.getPhoto());
		accountRepo.save(account);
		return accountMapper.convertToDTO(account);
	}

	public AccountDTO showDetailAccount(Integer id) {
		Account account = accountRepo.findById(id).get();
		return accountMapper.convertToDTO(account);
	}

	@Override
	public AccountDTO updateInformation(UpdateInformationAdminDTO updateInformationAdminDTO) {
		Optional<Account> objAccount = accountRepo.findById(updateInformationAdminDTO.getId());
		if (!objAccount.isPresent()) {
			throw new AppException(ResponeCustom.MESSAGE_CODE_KHONG_TON_TAI, "Tài khoản không tồn tại !");
		}
		Account account = objAccount.get();
		account.setFullname(updateInformationAdminDTO.getFullname());
		account.setPhoto(updateInformationAdminDTO.getPhoto());
		account.setPhone(updateInformationAdminDTO.getPhone());
		account.setMainAddress(updateInformationAdminDTO.getMainAddress());
		accountRepo.save(account);
		return accountMapper.convertToDTO(account);
	}

}
