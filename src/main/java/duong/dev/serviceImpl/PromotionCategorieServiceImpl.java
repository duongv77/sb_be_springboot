package duong.dev.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import duong.dev.common.ResponeCustom;
import duong.dev.dto.PromotionCategorieDTO;
import duong.dev.dto.request.CreatePromotionCategoriesDTO;
import duong.dev.entity.Categorie;
import duong.dev.entity.PromotionCategorie;
import duong.dev.exception.AppException;
import duong.dev.mapper.CategorieMapper;
import duong.dev.mapper.PromotionCategorieMapper;
import duong.dev.mapper.PromotionMapper;
import duong.dev.repository.CategorieRepository;
import duong.dev.repository.PromotionCategorieRepository;
import duong.dev.service.PromotionCategorieInterface;

@Service
public class PromotionCategorieServiceImpl implements PromotionCategorieInterface{
	@Autowired private PromotionCategorieRepository proMoCateRepo;
	@Autowired private PromotionCategorieMapper proMomapper;
	@Autowired private PromotionMapper promMapper;
	@Autowired private CategorieMapper cateMapper;
	@Autowired private CategorieRepository cateRepo;
	@Override
	public PromotionCategorieDTO create(CreatePromotionCategoriesDTO promotionCategorieD) {
		Optional<PromotionCategorie> value = proMoCateRepo.finByPromotionAndProduct(
				promotionCategorieD.getPromotion().getId(), 
				promotionCategorieD.getCategorie().getId());
		if(value.isPresent()) {
			throw new AppException(ResponeCustom.MESSAGE_CODE_DA_TON_TAI, "Danh mục đã tồn tại !");
		}
		Optional<Categorie> category = cateRepo.findByICateStatusOff(promotionCategorieD.getCategorie().getId());
		if(category.isPresent()) {
			throw new AppException(ResponeCustom.MESSAGE_CODE_DA_TON_TAI, "Danh mục sản phẩm đang nằm trong chương trình khác !");
		}
		PromotionCategorie promotionCategorie = new PromotionCategorie();
		promotionCategorie.setCategorie(cateMapper.convertToEntity(promotionCategorieD.getCategorie()));
		promotionCategorie.setPromotion(promMapper.convertToEntity(promotionCategorieD.getPromotion()));
		proMoCateRepo.save(promotionCategorie);
		return proMomapper.convertToDTO(promotionCategorie);
	}

	@Override
	public PromotionCategorieDTO delete(Integer id) {
		Optional<PromotionCategorie> promotionCategorie = proMoCateRepo.findById(id);
		if(!promotionCategorie.isPresent()) {
			throw new AppException(ResponeCustom.MESSAGE_CODE_KHONG_TON_TAI, "Không tồn tại");
		}
		proMoCateRepo.delete(promotionCategorie.get());
		return proMomapper.convertToDTO(promotionCategorie.get());
	}
	
	public List<PromotionCategorieDTO> convertListDTO(List<PromotionCategorie> promotionCategories) {
		List<PromotionCategorieDTO> categorieDTOs = new ArrayList<PromotionCategorieDTO>();
		for (PromotionCategorie promotionCategorie : promotionCategories) {
			categorieDTOs.add(proMomapper.convertToDTO(promotionCategorie));
		}
		return categorieDTOs;
	}

}
