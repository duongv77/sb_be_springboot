package duong.dev.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import duong.dev.dto.AddressDTO;
import duong.dev.entity.Address;

@Component
public class AddressMapper {
	@Autowired
	private ModelMapper mapper;
	
	//convert từ DTO về entity
	public Address convertToEntity(AddressDTO accountdetailDTO) {
		Address entity = new Address();
		mapper.map(accountdetailDTO, entity);
		return entity;
	}
	
	//convert từ entity về DTO
	public AddressDTO convertToDTO(Address entity) {
		AddressDTO accountdetailDTO = new AddressDTO();
		mapper.map(entity, accountdetailDTO);
		return accountdetailDTO;
	}
}
