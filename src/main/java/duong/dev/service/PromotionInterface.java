package duong.dev.service;

import java.util.List;

import duong.dev.dto.PromotionDTO;
import duong.dev.dto.request.UpdatePromotionDTO;
import duong.dev.dto.response.PromotionPrDTO;
import duong.dev.entity.Promotion;

public interface PromotionInterface {
	public List<PromotionDTO> readAll(String sort);
	
	public PromotionPrDTO showDetail(Integer id);
	
	public PromotionDTO create(PromotionDTO promotionD);
	
	public PromotionDTO update(UpdatePromotionDTO promotionD);
	
	public boolean delete(Integer id);
	
	public PromotionDTO stop(Integer id);
	
	public PromotionDTO pause(Integer id);
	
	public PromotionDTO play(Integer id);

	public List<PromotionDTO> searchPromotion(String sort, String keyword);

	public List<Promotion> listPromotionDate();

	public long findCountPromotion();
}
