package duong.dev.serviceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import duong.dev.common.ResponeCustom;
import duong.dev.dto.AccountDTO;
import duong.dev.dto.request.CreateRoleAccountDTO;
import duong.dev.entity.Account;
import duong.dev.entity.Role;
import duong.dev.exception.AppException;
import duong.dev.mapper.AccountMapper;
import duong.dev.repository.AccountRepository;
import duong.dev.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import duong.dev.dto.RoleAccountDTO;
import duong.dev.entity.RoleAccount;
import duong.dev.mapper.RoleAccountMapper;
import duong.dev.repository.RoleAccountRepository;
import duong.dev.service.RoleAccountInterface;

@Service
public class RoleAccountServiceImpl implements RoleAccountInterface {
	@Autowired
	RoleAccountRepository roleAccountRepo;
	@Autowired
	RoleAccountMapper roleAccountMapper;
	@Autowired
	private AccountRepository accountRepo;
	@Autowired
	private RoleRepository roleRepo;
	@Autowired
	private AccountMapper accountMapper;
	 
	public List<RoleAccountDTO> convertListDTO(List<RoleAccount> listRoleAccountE ) {
		List<RoleAccountDTO> listRoleAccountD = new ArrayList<RoleAccountDTO>();
		for(RoleAccount roleAccountE : listRoleAccountE ) {
			listRoleAccountD.add(roleAccountMapper.convertToDTO(roleAccountE));
		}
		return listRoleAccountD;
	}

	@Override
	public RoleAccount getAccountcreate(RoleAccount roleAccount) {
		roleAccountRepo.save(roleAccount);
		return roleAccount;
	}

	@Override
	public RoleAccountDTO update(RoleAccountDTO roleAccountD) throws IOException {
		roleAccountRepo.save(roleAccountMapper.convertToEntity(roleAccountD));
		return roleAccountD;
	}

	@Override
	public RoleAccountDTO delete(RoleAccount roleAccountE) throws IOException {
		roleAccountRepo.delete(roleAccountE);
		return roleAccountMapper.convertToDTO(roleAccountE);
	}

	@Override
	public AccountDTO changeRole(CreateRoleAccountDTO roleAccountDTO) {
		Optional<Account> accountOp = accountRepo.findById(roleAccountDTO.getAccount().getId());
		if(!accountOp.isPresent()) {
			throw new AppException(ResponeCustom.MESSAGE_CODE_DA_TON_TAI, "Account không tồn tại");
		}
		Optional<Role> roleOp = roleRepo.findById(roleAccountDTO.getRole().getId());
		if(!roleOp.isPresent()) {
			throw new AppException(ResponeCustom.MESSAGE_CODE_DA_TON_TAI, "Quyền không tồn tại");
		}
		Optional<RoleAccount> roleAccountOp = roleAccountRepo.findByAccountIdAndRoleId(roleAccountDTO.getAccount().getId(), roleAccountDTO.getRole().getId());
		if(roleAccountOp.isPresent()) {
			roleAccountRepo.delete(roleAccountOp.get());
		}else{
			RoleAccount roleAccount = new RoleAccount();
			roleAccount.setAccount(accountOp.get());
			roleAccount.setRole(roleOp.get());
			roleAccountRepo.save(roleAccount);
		}
		return accountMapper.convertToDTO(accountRepo.findById(accountOp.get().getId()).get());
	}

	@Override
	public List<RoleAccountDTO> readAll() throws IOException {
		return convertListDTO(roleAccountRepo.findAll());
	}
	
}






















