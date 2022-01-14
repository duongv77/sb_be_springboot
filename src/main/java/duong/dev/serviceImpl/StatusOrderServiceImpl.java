package duong.dev.serviceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import duong.dev.dto.StatusOrderDTO;
import duong.dev.entity.StatusOrder;
import duong.dev.mapper.StatusOrderMapper;
import duong.dev.repository.StatusOrderRepository;
import duong.dev.service.StatusOrderInterface;

@Service
public class StatusOrderServiceImpl implements StatusOrderInterface {
	@Autowired
	StatusOrderRepository statusOrderRepo;
	@Autowired
	StatusOrderMapper statusOrderMapper;
		
	@Override
	public List<StatusOrderDTO> readAll() throws IOException {
		return convertListDTO(statusOrderRepo.findAll());
	}

	@Override
	public StatusOrderDTO create(StatusOrderDTO statusOrderD) throws IOException {
		StatusOrder statusOrderE = statusOrderMapper.convertToEntity(statusOrderD);
		statusOrderRepo.save(statusOrderE);
		return statusOrderMapper.convertToDTO(statusOrderE);
	}

	@Override
	public StatusOrderDTO update(StatusOrderDTO statusOrderD) throws IOException {
		statusOrderRepo.save(statusOrderMapper.convertToEntity(statusOrderD));
		return statusOrderD;
	}

	@Override
	public StatusOrderDTO delete(StatusOrder statusOrderE) throws IOException {
		statusOrderRepo.delete(statusOrderE);
		return statusOrderMapper.convertToDTO(statusOrderE);
	}
	
	public List<StatusOrderDTO> convertListDTO(List<StatusOrder> listStatusOrderE ) {
		List<StatusOrderDTO> listStatusOrderD = new ArrayList<StatusOrderDTO>();
		for(StatusOrder statusOrderE : listStatusOrderE ) {
			listStatusOrderD.add(statusOrderMapper.convertToDTO(statusOrderE));
		}
		return listStatusOrderD;
	}
	

}
