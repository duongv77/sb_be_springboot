package duong.dev.service;

import java.io.IOException;
import java.util.List;

import duong.dev.dto.StatusOrderDTO;
import duong.dev.entity.StatusOrder;

public interface StatusOrderInterface {
	public List<StatusOrderDTO> readAll() throws IOException;
	
	public StatusOrderDTO create(StatusOrderDTO statusOrderD) throws IOException;
	
	public StatusOrderDTO update(StatusOrderDTO statusOrderD) throws IOException;
	
	public StatusOrderDTO delete(StatusOrder statusOrderE) throws IOException;
}
