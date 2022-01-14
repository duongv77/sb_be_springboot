package duong.dev.serviceImpl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import duong.dev.common.Common;
import duong.dev.common.ResponeCustom;
import duong.dev.common.Status;
import duong.dev.dto.PromotionDTO;
import duong.dev.dto.request.UpdatePromotionDTO;
import duong.dev.dto.response.PromotionPrDTO;
import duong.dev.entity.Promotion;
import duong.dev.exception.AppException;
import duong.dev.mapper.PromotionMapper;
import duong.dev.repository.PromotionRepository;
import duong.dev.service.PromotionInterface;

@Service
public class PromotionServiceImpl implements PromotionInterface {
	@Autowired private PromotionRepository promotionRepo;
	@Autowired private PromotionMapper promotionMapper;
	@Autowired private PromotionCategorieServiceImpl proMoCateSV;
	
	@Override
	public PromotionPrDTO showDetail(Integer id) {
		Promotion promotion = promotionRepo.getById(id);
		PromotionPrDTO promotionPrTO = new PromotionPrDTO();
		promotionPrTO.setActivated(promotion.getActivated());
		promotionPrTO.setCreateDate(promotion.getCreateDate());
		promotionPrTO.setDescription(promotion.getDescription());
		promotionPrTO.setEndDate(promotion.getEndDate());
		promotionPrTO.setId(promotion.getId());
		promotionPrTO.setName(promotion.getName());
		promotionPrTO.setSale(promotion.getSale());
		promotionPrTO.setPromotionCategorie(proMoCateSV.convertListDTO(promotion.getPromotionCategorie()));
		return promotionPrTO ;
	}
	
	public List<PromotionDTO> convertListDTO(List<Promotion> listPromotionE ) {
		List<PromotionDTO> listPromotionD = new ArrayList<PromotionDTO>();
		for(Promotion promotionE : listPromotionE ) {
			listPromotionD.add(promotionMapper.convertToDTO(promotionE));
		}
		return listPromotionD;
	}

	public List<PromotionDTO> readAll(String sort){
		if(sort.equals("name") || sort.equals("title")){
			Sort sort2 = Sort.by(sort).ascending();
			List<Promotion> listPromotionE = promotionRepo.findAll(sort2);
			return convertListDTO(listPromotionE);
		}
		Sort sort2 = Sort.by(sort).descending();
		List<Promotion> listPromotionE = promotionRepo.findAll(sort2);
		return convertListDTO(listPromotionE);
	}
	
	@Override
	public List<PromotionDTO> searchPromotion(String sort, String keyword) {
		if(sort.equals("name") || sort.equals("title")){
			Sort sort2 = Sort.by(sort).ascending();
			List<Promotion> listPromotionE = promotionRepo.searchPromotion(keyword, sort2);
			return convertListDTO(listPromotionE);
		}
		Sort sort2 = Sort.by(sort).descending();
		List<Promotion> listPromotionE = new ArrayList<>();
		System.out.println(keyword.trim());
		if(keyword.trim().equals("")){
			listPromotionE = promotionRepo.findAll(sort2);
		}else{
			System.out.println("vào false");
			listPromotionE = promotionRepo.searchPromotion(keyword, sort2);

		}
		return convertListDTO(listPromotionE);
	}

	@Override
	public PromotionDTO create(PromotionDTO promotionD) {
		Promotion promotionE = promotionMapper.convertToEntity(promotionD);
		promotionE.setActivated(statusPromotionCreate(promotionE));
		promotionRepo.save(promotionE);
		return promotionMapper.convertToDTO(promotionE);
	}
	
	private Integer statusPromotionCreate(Promotion promotionE) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime createDatePart= LocalDateTime.parse(promotionE.getCreateDate()+" 00:00:00", formatter);
		LocalDateTime endDatePart= LocalDateTime.parse(promotionE.getEndDate()+" 00:00:00", formatter);
		LocalDateTime dateNow= LocalDateTime.parse(Common.DATE_NOW, formatter);
		long betweenTimeStart = java.time.Duration.between(createDatePart, dateNow).toDays();
		long betweenTimeEnd = java.time.Duration.between(dateNow, endDatePart).toDays();
		if(betweenTimeStart>=0 && betweenTimeEnd>=0) {
			return Status.STATUS_PROMOTION_DANG_HOAT_DONG;
		}else if(betweenTimeStart<0) {
			return Status.STATUS_PROMOTION_DANG_CHO;
		}else if(betweenTimeEnd<0) {
			return Status.STATUS_PROMOTION_DA_KET_THUC;
		}
		return Status.STATUS_PROMOTION_DA_KET_THUC;
	}

	@Override
	public PromotionDTO update(UpdatePromotionDTO promotionD){
		Optional<Promotion> promotionOptional = promotionRepo.findById(promotionD.getId());
		if(!promotionOptional.isPresent()) {
			throw new AppException(ResponeCustom.MESSAGE_CODE_KHONG_TON_TAI, "Chương trình không tồn tại"); 
		}
		Promotion promotion = promotionOptional.get();
		promotion.setCreateDate(promotionD.getCreateDate());
		promotion.setDescription(promotionD.getDescription());
		promotion.setEndDate(promotion.getEndDate());
		promotion.setName(promotionD.getName());
		promotion.setSale(promotionD.getSale());
		promotionRepo.save(promotion);
		return promotionMapper.convertToDTO(promotion);
	}

	@Override
	public boolean delete(Integer id){
		Optional<Promotion> promotion = promotionRepo.findById(id);
		if(!promotion.isPresent()) {
			throw new AppException(ResponeCustom.MESSAGE_CODE_KHONG_TON_TAI, "Chương trình không tồn tại"); 
		}
		try{
			promotionRepo.delete(promotion.get());
		}catch(Exception e){
			throw new AppException(ResponeCustom.MESSAGE_CODE_KHONG_THANH_CONG, "Không thể xóa chương trình màu");
		}
		return true;
	}
	
	@Override
	public PromotionDTO stop(Integer id) {
		Optional<Promotion> promotion = promotionRepo.findById(id);
		if(!promotion.isPresent()) {
			throw new AppException(ResponeCustom.MESSAGE_CODE_KHONG_TON_TAI, "Chương trình không tồn tại"); 
		}
		promotionRepo.updateStatus(Status.STATUS_PROMOTION_DA_KET_THUC, id);
		promotion.get().setActivated(Status.STATUS_PROMOTION_DA_KET_THUC);
		return promotionMapper.convertToDTO(promotion.get());
	}

	@Override
	public PromotionDTO pause(Integer id) {
		Optional<Promotion> promotion = promotionRepo.findById(id);
		if(!promotion.isPresent()) {
			throw new AppException(ResponeCustom.MESSAGE_CODE_KHONG_TON_TAI, "Chương trình không tồn tại"); 
		}
		promotionRepo.updateStatus(Status.STATUS_PROMOTION_TAM_DUNG, id);
		promotion.get().setActivated(Status.STATUS_PROMOTION_TAM_DUNG);
		return promotionMapper.convertToDTO(promotion.get());
	}

	@Override
	public PromotionDTO play(Integer id) {
		Optional<Promotion> promotion = promotionRepo.findById(id);
		if(!promotion.isPresent()) {
			throw new AppException(ResponeCustom.MESSAGE_CODE_KHONG_TON_TAI, "Chương trình không tồn tại"); 
		}
		promotionRepo.updateStatus(Status.STATUS_PROMOTION_DANG_HOAT_DONG, id);
		promotion.get().setActivated(Status.STATUS_PROMOTION_DANG_HOAT_DONG);
		return promotionMapper.convertToDTO(promotion.get());
	}

	@Override
	public List<Promotion> listPromotionDate() {
		return promotionRepo.findListPromotionStop();
	}

	@Override
	public long findCountPromotion() {
		return promotionRepo.findCountPromotion();
	}

	public List<Promotion> listPromotionEndDate(){
		return promotionRepo.findListPromotionStop();
	}
}
