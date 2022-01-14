package duong.dev.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import duong.dev.common.ResponeCustom;
import duong.dev.common.Status;
import duong.dev.dto.request.TitleProductDTO;
import duong.dev.dto.request.TitleUpdateRequestDTO;
import duong.dev.exception.AppException;
import org.springframework.beans.factory.annotation.Autowired;

import duong.dev.dto.TitleDTO;
import duong.dev.entity.Title;
import duong.dev.mapper.TitleMapper;
import duong.dev.repository.TitleRepository;
import duong.dev.service.TitleInterface;
import org.springframework.stereotype.Service;

@Service
public class TitleServiceImpl implements TitleInterface{
	@Autowired private TitleRepository titleRepo;
	@Autowired private TitleMapper titleMapper;
	@Override
	public List<TitleDTO> showAll() {
		return convertListDTO(titleRepo.findAll());
	}

	@Override
	public List<TitleProductDTO> findAll() {
		return titleRepo.findAll().stream().map(elm->{return titleMapper.convertToDTO2(elm);}).collect(Collectors.toList());
	}

	@Override
	public TitleDTO createTitle(TitleDTO titleD) {
		Optional<Title> titleOptional = titleRepo.findByName(titleD.getName());
		if(titleOptional.isPresent())
			throw new AppException(ResponeCustom.MESSAGE_CODE_DA_TON_TAI, "Đầu sách đã tồn tại!");
		Title title = titleMapper.convertToEntity(titleD);
		title.setStatus(Status.STATUS_HOAT_DONG);
		titleRepo.save(title);
		return titleMapper.convertToDTO(title);
	}

	@Override
	public TitleProductDTO updateTitle(TitleDTO titleD) {
		Optional<Title> titleOptional = titleRepo.findById(titleD.getId());
		if(!titleOptional.isPresent())
			throw new AppException(ResponeCustom.MESSAGE_CODE_KHONG_TON_TAI, "Đầu sách không tồn tại!");
		Title title = titleOptional.get();
		title.setName(titleD.getName());
		title.setNote(titleD.getNote());
		titleRepo.save(title);
		return titleMapper.convertToDTO2(title);
	}

	@Override
	public boolean deleteTitle(Integer id) {
		Optional<Title> titleOptional = titleRepo.findById(id);
		if(!titleOptional.isPresent())
			throw new AppException(ResponeCustom.MESSAGE_CODE_KHONG_TON_TAI, "Đầu sách không tồn tại!");
		try{
			titleRepo.delete(titleOptional.get());
		}catch(Exception e){
			throw new AppException(ResponeCustom.MESSAGE_CODE_KHONG_THANH_CONG, "Đầu sách đã được sử dụng không thể xóa !");
		}
		return true;
	}

	@Override
	public TitleProductDTO updateStatus(TitleUpdateRequestDTO obj) {
		Optional<Title> titleOptional = titleRepo.findById(obj.getId());
		if(!titleOptional.isPresent())
			throw new AppException(ResponeCustom.MESSAGE_CODE_KHONG_TON_TAI, "Đầu sách không tồn tại!");
		Title title = titleOptional.get();
		title.setStatus(obj.getStatus());
		titleRepo.save(title);
		return titleMapper.convertToDTO2(title);
	}

	private List<TitleDTO> convertListDTO(List<Title> titles) {
		return titles.stream().map(elm->{return titleMapper.convertToDTO(elm);}).collect(Collectors.toList());
	}

}
