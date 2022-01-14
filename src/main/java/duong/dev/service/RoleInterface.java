package duong.dev.service;

import java.io.IOException;
import java.util.List;

import duong.dev.dto.RoleDTO;
import duong.dev.entity.Role;

public interface RoleInterface {
public List<RoleDTO> readAll() throws IOException;
	
	public RoleDTO create(RoleDTO roleD) throws IOException;
	
	public RoleDTO update(RoleDTO roleD) throws IOException;
	
	public RoleDTO delete(Role roleE) throws IOException;
}
