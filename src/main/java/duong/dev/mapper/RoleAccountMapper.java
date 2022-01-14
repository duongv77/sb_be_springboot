package duong.dev.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import duong.dev.dto.RoleAccountDTO;
import duong.dev.entity.RoleAccount;

@Component
public class RoleAccountMapper {
	@Autowired
	private ModelMapper mapper;
	
	//convert từ DTO về entity
	public RoleAccount convertToEntity(RoleAccountDTO roleAccountDTO) {
		RoleAccount entity = new RoleAccount();
		mapper.map(roleAccountDTO, entity);
		return entity;
	}
	
	//convert từ entity về DTO
	public RoleAccountDTO convertToDTO(RoleAccount entity) {
		RoleAccountDTO roleAccountDTO = new RoleAccountDTO();
		mapper.map(entity, roleAccountDTO);
		return roleAccountDTO;
	}
}
