package duong.dev.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import duong.dev.dto.AccountDTO;
import duong.dev.dto.request.UpdateInformationAdminDTO;
import duong.dev.entity.Account;


@Component
public class AccountMapper {
	@Autowired
	private ModelMapper mapper;
	
	//convert từ DTO về entity
	public Account convertToEntity(AccountDTO accountDTO) {
		Account entity = new Account();
		mapper.map(accountDTO, entity);
		return entity;
	}
	
	//convert từ DTO về entity
		public Account convertToEntity1(UpdateInformationAdminDTO updateInformationAdminDTO) {
			Account entity = new Account();
			mapper.map(updateInformationAdminDTO, entity);
			return entity;
		}
	
	//convert từ entity về DTO
	public AccountDTO convertToDTO(Account entity) {
		AccountDTO accountDTO = new AccountDTO();
		mapper.map(entity, accountDTO);
		return accountDTO;
	}
	
	public UpdateInformationAdminDTO convertToDTO1(Account entity) {
		UpdateInformationAdminDTO accountDTO = new UpdateInformationAdminDTO();
		mapper.map(entity, accountDTO);
		return accountDTO;
	}
}
