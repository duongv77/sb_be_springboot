package duong.dev.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import duong.dev.common.Common;
import duong.dev.common.ResponeCustom;
import duong.dev.common.Status;
import duong.dev.dto.CategorieDTO;
import duong.dev.dto.ProductDTO;
import duong.dev.dto.request.CategoryRequestDTO;
import duong.dev.entity.Categorie;
import duong.dev.entity.Product;
import duong.dev.entity.Promotion;
import duong.dev.exception.AppException;
import duong.dev.mapper.CategorieMapper;
import duong.dev.repository.CategorieRepository;
import duong.dev.repository.PromotionRepository;
import duong.dev.service.CategorieInterface;

@Service
public class CategorieServiceImpl implements CategorieInterface{
	@Autowired private CategorieRepository cateRepo;
	@Autowired private CategorieMapper cateMapper;
	@Autowired private PromotionRepository proMoRep;

	@Override
	public List<CategorieDTO> readCateoryBig() {
		return convertListDTO(cateRepo.findCategoryBig(Common.ID_CATEGORY_BIG));
	}

	@Override
	public List<CategorieDTO> readCateoryByCateGoryId(Integer id){
		return convertListDTO(cateRepo.findCategoryBig(id));
	}

	@Override
	public List<CategorieDTO> readCateoryShowNav() {
		return convertListDTO(cateRepo.listCategoryShowNav());
	}
	
	@Override
	public List<CategoryRequestDTO> searchCategory(String sort, String keyword) {
		if(sort.equals("name") || sort.equals("title")){
			Sort sort2 = Sort.by(sort).ascending();
			List<Categorie> listCategoryE = cateRepo.searchCategory(keyword, sort2);
			return convertListDTO1(listCategoryE);
		}
		Sort sort2 = Sort.by(sort).descending();
		List<Categorie> listCategoryE = new ArrayList<>();
		if(keyword.trim().equals("")){
			listCategoryE = cateRepo.findAll(sort2);
		}else{
			listCategoryE = cateRepo.searchCategory(keyword, sort2);

		}
		return convertListDTO1(listCategoryE);
	}

	@Override
	public CategoryRequestDTO createBigcategory(CategoryRequestDTO categoryRequestDTO)  {
		Categorie cateE = cateMapper.convertToEntity1(categoryRequestDTO);
		if(categoryRequestDTO.getName() == cateE.getName()) {
			throw new AppException(ResponeCustom.MESSAGE_CODE_KHONG_THANH_CONG,"Tên danh mục đã tồn tại !");
		}
		cateE.setStatus(1);
		cateRepo.save(cateE);
		return cateMapper.convertToDTO1(cateE);
	}
	
	@Override
	public CategoryRequestDTO update(CategoryRequestDTO categoryRequestDTO) {
		Optional<Categorie> obj = cateRepo.findById(categoryRequestDTO.getId());
		if(!obj.isPresent()) {
			throw new AppException(ResponeCustom.MESSAGE_CODE_KHONG_TON_TAI,"Danh mục không tồn tại !");
		}
		Categorie cateE = cateMapper.convertToEntity1(categoryRequestDTO);
		cateE.setStatus(1);
		cateRepo.save(cateE);
		return cateMapper.convertToDTO1(cateRepo.findById(categoryRequestDTO.getId()).get());
	}

	@Override
	public CategoryRequestDTO delete(Integer id) {
		try {
		Optional<Categorie> categorie = cateRepo.findById(id);
		if(!categorie.isPresent()) {
			throw new AppException(ResponeCustom.MESSAGE_CODE_KHONG_TON_TAI, "Danh mục không tồn tại !");
		}
		cateRepo.delete(categorie.get());
		return cateMapper.convertToDTO1(categorie.get());
		} catch(Exception e) {
			throw new AppException(ResponeCustom.MESSAGE_CODE_KHONG_THANH_CONG,"Xóa không thành công ! Hãy kiểm tra lại các ràng buộc với danh mục này !");
		}
	}
	
	
	public List<CategorieDTO> convertListDTO(List<Categorie> listCategorieE) {
		List<CategorieDTO> listCategorieD = new ArrayList<CategorieDTO>();
		for (Categorie categorieE : listCategorieE) {
			listCategorieD.add(cateMapper.convertToDTO(categorieE));
		}
		return listCategorieD;
	}
	
	public List<CategoryRequestDTO> convertListDTO1(List<Categorie> listCategorieE) {
		List<CategoryRequestDTO> listCategorieD = new ArrayList<CategoryRequestDTO>();
		for (Categorie categorieE : listCategorieE) {
			listCategorieD.add(cateMapper.convertToDTO1(categorieE));
		}
		return listCategorieD;
	}
	
	public List<Categorie> convertListE(List<CategorieDTO> listCategorieDTO) {
		List<Categorie> listCategorieE = new ArrayList<Categorie>();
		for (CategorieDTO categorieD : listCategorieDTO) {
			listCategorieE.add(cateMapper.convertToEntity(categorieD));
		}
		return listCategorieE;
	}

	@Override
	public List<CategorieDTO> finListCateNoPromotion(Integer id) {
		Optional<Promotion> promotion = proMoRep.findById(id);
		if(!promotion.isPresent()) {
			throw new AppException(ResponeCustom.MESSAGE_CODE_KHONG_TON_TAI, "Chương trình giảm giá không tồn tại");
		}
		List<Categorie> listCateE = cateRepo.findCatenotPromotion(id);
		return convertListDTO(listCateE);
	}

	@Override
	public List<CategorieDTO> listCateLv2() {
		List<Categorie> listCateE = cateRepo.findListCateLV2();
		return convertListDTO(listCateE);
	}

	@Override
	public List<CategoryRequestDTO> realAll(){
		return convertListDTO1(cateRepo.viewListCategory());
	}


	@Override
	public List<CategorieDTO> listCateLv1() {
		List<Categorie> listCateE = cateRepo.findListCateLV1();
		return convertListDTO(listCateE);
		
	}

	@Override
	public List<CategoryRequestDTO> readNameCategory() {
		return convertListDTO1(cateRepo.findAll());
	}

	@Override
	public CategoryRequestDTO updateStatus(Integer id) {
		Optional<Categorie> optionCate = cateRepo.findById(id);
		if(!optionCate.isPresent()) {
			throw new AppException(ResponeCustom.MESSAGE_CODE_KHONG_TON_TAI, "Danh mục không tồn tại !");
		}
		Categorie categorie = optionCate.get();
		if(categorie.getStatus() == Status.STATUS_HOAT_DONG) {
			categorie.setStatus(Status.STATUS_KHONG_HOAT_DONG);
		} else {
			categorie.setStatus(Status.STATUS_HOAT_DONG);
		}
		cateRepo.save(categorie);
		return cateMapper.convertToDTO1(categorie);
	}

	@Override
	public List<CategorieDTO> listSubCategory(Integer id) {
	return convertListDTO(cateRepo.listSubCategory(id));
	}

	@Override
	public List<CategoryRequestDTO> readTypeBigCategory() {
		return convertListDTO1(cateRepo.listTypeCate1());
	}




}
