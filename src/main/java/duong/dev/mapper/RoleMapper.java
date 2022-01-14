package duong.dev.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import duong.dev.dto.RoleDTO;
import duong.dev.entity.Role;
@Component
public class RoleMapper {
	@Autowired
	private ModelMapper mapper;
	
	//convert từ DTO về entity
	public Role convertToEntity(RoleDTO roleDTO) {
		Role entity = new Role();
		mapper.map(roleDTO, entity);
		return entity;
	}
	
	//convert từ entity về DTO
	public RoleDTO convertToDTO(Role entity) {
		RoleDTO roleDTO = new RoleDTO();
		mapper.map(entity, roleDTO);
		return roleDTO;
	}
}
