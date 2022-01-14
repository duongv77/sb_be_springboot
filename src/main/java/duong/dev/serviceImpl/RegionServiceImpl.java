package duong.dev.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import duong.dev.dto.RegionDTO;
import duong.dev.entity.Region;
import duong.dev.mapper.RegionMapper;
import duong.dev.repository.RegionRepository;
import duong.dev.service.Regioninterface;

@Component
public class RegionServiceImpl implements Regioninterface{
	@Autowired private RegionRepository regionRepo;
	@Autowired private RegionMapper regionMapper;
	@Override
	public List<RegionDTO> getAll() {
		return convertListDTO(regionRepo.findAll());
	}
	
	private List<RegionDTO> convertListDTO(List<Region> listRegions){
		List<RegionDTO> regionDTOs = new ArrayList<RegionDTO>();
		for (Region region : listRegions) {
			regionDTOs.add(regionMapper.convertToDTO(region));
		}
		return regionDTOs;
	}

}
