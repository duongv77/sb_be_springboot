package duong.dev.serviceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import duong.dev.common.ResponeCustom;
import duong.dev.common.Status;
import duong.dev.dto.AuthorDTO;
import duong.dev.dto.ProductDTO;
import duong.dev.entity.Author;
import duong.dev.entity.Product;
import duong.dev.exception.AppException;
import duong.dev.mapper.AuthorMapper;
import duong.dev.repository.AuthorRepository;
import duong.dev.service.AuthorInterface;

@Service
public class AuthorServiceImpl implements AuthorInterface{
	
	@Autowired private AuthorMapper authorMapper;
	@Autowired private AuthorRepository authorRepo;
	
	
	@Override
	public List<AuthorDTO> readAll(String sort){
		if(sort.equals("name") || sort.equals("title")){
			Sort sort2 = Sort.by(sort).ascending();
			List<Author> listAu = authorRepo.findAll(sort2);
			return convertListDTO(listAu);
		}
		Sort sort2 = Sort.by(sort).descending();
		List<Author> listAu = authorRepo.findAll(sort2);
		return convertListDTO(listAu);
	}
	
	@Override
	public List<AuthorDTO> searchAuthor(String sort, String keyword) {
		if(sort.equals("name") || sort.equals("title")){
			Sort sort2 = Sort.by(sort).ascending();
			List<Author> listAuthorE = authorRepo.searchAuthor(keyword, sort2);
			return convertListDTO(listAuthorE);
		}
		Sort sort2 = Sort.by(sort).descending();
		List<Author> listAuthorE;
		if(keyword.trim().equals("")){
			listAuthorE = authorRepo.findAll(sort2);
		}else{
			listAuthorE = authorRepo.searchAuthor(keyword, sort2);

		}
		return convertListDTO(listAuthorE);
	}
	
	
	@Override
	public AuthorDTO create(AuthorDTO authorD) throws IOException {
		Author authorE = authorMapper.convertToEntity(authorD);
		authorE.setStatus(Status.STATUS_HOAT_DONG);
		authorRepo.save(authorE);
		return authorMapper.convertToDTO(authorE);
	}

	@Override
	public AuthorDTO update(AuthorDTO authorD) throws IOException {
		Optional<Author> objAuthor = authorRepo.findById(authorD.getId());
		if(!objAuthor.isPresent()) {
			throw new AppException(ResponeCustom.MESSAGE_CODE_KHONG_TON_TAI,"Tác giả không tồn tại !");
		}
		Author authorE = authorMapper.convertToEntity(authorD);
		authorE.setStatus(Status.STATUS_HOAT_DONG);
		authorRepo.save(authorE);
		return authorMapper.convertToDTO(authorE);
	}
	
	@Override
	public AuthorDTO delete(Integer id) throws IOException {
		try {
			Optional<Author> author = authorRepo.findById(id);
			if(!author.isPresent()) {
				throw new AppException(ResponeCustom.MESSAGE_CODE_KHONG_TON_TAI, "Tác giả không tồn tại !");
			}
			authorRepo.delete(author.get());
			return authorMapper.convertToDTO(author.get());
			} catch(Exception e) {
				throw new AppException(ResponeCustom.MESSAGE_CODE_KHONG_THANH_CONG,"Xóa không thành công ! Hãy kiểm tra lại các ràng buộc với tác giả này !");
			}
	}
	
	public List<AuthorDTO> convertListDTO(List<Author> listAuthorE) {
		List<AuthorDTO> listAuthorD = new ArrayList<AuthorDTO>();
		for (Author authorE : listAuthorE) {
			listAuthorD.add(authorMapper.convertToDTO(authorE));
		}
		return listAuthorD;
	}
	
	public AuthorDTO getOnly(Integer id) {
		Optional<Author> author = authorRepo.findById(id);
		if(!author.isPresent()) {
			throw new AppException(ResponeCustom.MESSAGE_CODE_KHONG_TON_TAI, "Tác giả không tồn tại !");
		}
		return authorMapper.convertToDTO(author.get());
	}

	@Override
	public AuthorDTO updateStatus(Integer id) {
		Optional<Author> optional = authorRepo.findById(id);
		if(!optional.isPresent()) {
			throw new AppException(ResponeCustom.MESSAGE_CODE_KHONG_TON_TAI, "Tác giả không tồn tại !");
		}
		Author author = optional.get();
		if(author.getStatus()==Status.STATUS_HOAT_DONG) {
			author.setStatus(Status.STATUS_KHONG_HOAT_DONG);
		} else {
			author.setStatus(Status.STATUS_HOAT_DONG);
		}
		authorRepo.save(author);
		return authorMapper.convertToDTO(author);
	}

	

}
