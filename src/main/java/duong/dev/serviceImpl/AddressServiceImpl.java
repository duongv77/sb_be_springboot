package duong.dev.serviceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import duong.dev.common.ResponeCustom;
import duong.dev.dto.AccountDTO;
import duong.dev.exception.AppException;
import duong.dev.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import duong.dev.dto.AddressDTO;
import duong.dev.entity.Account;
import duong.dev.entity.Address;
import duong.dev.mapper.AddressMapper;
import duong.dev.repository.AddressRepository;
import duong.dev.service.AddressInterface;

import javax.servlet.ServletException;

@Service
public class AddressServiceImpl implements AddressInterface{
	@Autowired private OrderServiceImpl orderSev;
	@Autowired private AddressMapper addressMapper;
	@Autowired private AddressRepository addressRepo;
	@Autowired private AccountServiceImpl accountService;

	@Override
	public List<AddressDTO> readAll(Account accountE) throws IOException {
		return convertListDTO(addressRepo.findByIdAccount(accountE.getId()));
	}

	@Override
	public AddressDTO create(AddressDTO addressD) throws ServletException, IOException {
		Account account = orderSev.getAccount();
		Address addressE = addressMapper.convertToEntity(addressD);
		addressE.setAccount(account);
		addressRepo.save(addressE);
		return addressMapper.convertToDTO(addressE);
	}

	@Override
	public AccountDTO delete(Integer id) throws ServletException, IOException {
		Account account = orderSev.getAccount();
		Optional<Address> addressOptional = addressRepo.findByIdAccountAndIdAddress(account.getId(), id);
		if (!addressOptional.isPresent())
			throw new AppException(ResponeCustom.MESSAGE_CODE_KHONG_TON_TAI, "Địa chỉ không tồn tại hoặc bạn không thể xóa địa chỉ này!");
		addressRepo.delete(addressOptional.get());
		return accountService.showDetailAccount(account.getId());
	}

	@Override
	public AddressDTO update(AddressDTO addressD) throws IOException {
		addressRepo.save(addressMapper.convertToEntity(addressD));
		return addressD;
	}

	@Override
	public AddressDTO delete(Address addressE) throws IOException {
		addressRepo.delete(addressE);
		return addressMapper.convertToDTO(addressE);
	}
	
	public List<AddressDTO> convertListDTO(List<Address> listAddressE) {
		List<AddressDTO> listAddressD = new ArrayList<AddressDTO>();
		for (Address addressE : listAddressE) {
			listAddressD.add(addressMapper.convertToDTO(addressE));
		}
		return listAddressD;
	}

	

}
