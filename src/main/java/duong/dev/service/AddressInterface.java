package duong.dev.service;

import java.io.IOException;
import java.util.List;

import duong.dev.dto.AccountDTO;
import duong.dev.dto.AddressDTO;
import duong.dev.entity.Account;
import duong.dev.entity.Address;

import javax.servlet.ServletException;

public interface AddressInterface {
	public List<AddressDTO> readAll(Account accountE) throws IOException;

	public AddressDTO create(AddressDTO addressD) throws ServletException, IOException ;

	public AccountDTO delete(Integer id) throws ServletException, IOException ;

	public AddressDTO update(AddressDTO addressD) throws IOException;
	
	public AddressDTO delete(Address addressE) throws IOException;
}
