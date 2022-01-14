package duong.dev.serviceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import duong.dev.dto.RoleDTO;
import duong.dev.entity.Role;
import duong.dev.mapper.RoleMapper;
import duong.dev.repository.RoleRepository;
import duong.dev.service.RoleInterface;

@Service
public class RoleServiceImpl implements RoleInterface {
	@Autowired
	RoleRepository roleRepo;
	@Autowired
	RoleMapper roleMapper;
	
	@Override
	public List<RoleDTO> readAll() throws IOException {
		return convertListDTO(roleRepo.findAll());
	}

	@Override
	public RoleDTO create(RoleDTO roleD) throws IOException {
		Role roleE = roleMapper.convertToEntity(roleD);
		roleRepo.save(roleE);
		return roleMapper.convertToDTO(roleE);
		
	}

	@Override
	public RoleDTO update(RoleDTO roleD) throws IOException {
		roleRepo.save(roleMapper.convertToEntity(roleD));
		return roleD;
	}

	@Override
	public RoleDTO delete(Role roleE) throws IOException {
		roleRepo.delete(roleE);
		return roleMapper.convertToDTO(roleE);
	}
	
	public List<RoleDTO> convertListDTO(List<Role> listRoleE ) {
		List<RoleDTO> listRoleD = new ArrayList<RoleDTO>();
		for(Role roleE : listRoleE ) {
			listRoleD.add(roleMapper.convertToDTO(roleE));
		}
		return listRoleD;
	}

}
