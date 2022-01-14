package duong.dev.service;

import duong.dev.dto.PromotionCategorieDTO;
import duong.dev.dto.request.CreatePromotionCategoriesDTO;

public interface PromotionCategorieInterface {
	public PromotionCategorieDTO create(CreatePromotionCategoriesDTO promotionCategorieD);
	public PromotionCategorieDTO delete(Integer id);
}
