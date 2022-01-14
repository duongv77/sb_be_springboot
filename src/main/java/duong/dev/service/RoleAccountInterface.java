package duong.dev.service;

import java.io.IOException;
import java.util.List;

import duong.dev.dto.AccountDTO;
import duong.dev.dto.RoleAccountDTO;
import duong.dev.dto.request.CreateRoleAccountDTO;
import duong.dev.entity.RoleAccount;

public interface RoleAccountInterface {
	public List<RoleAccountDTO> readAll() throws IOException;

	public RoleAccount getAccountcreate(RoleAccount roleAccountDTO);
	
	public RoleAccountDTO update(RoleAccountDTO roleAccountD) throws IOException;
	
	public RoleAccountDTO delete(RoleAccount roleAccountE) throws IOException;

	public AccountDTO changeRole(CreateRoleAccountDTO roleAccountDTO);
}
