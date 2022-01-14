package duong.dev.service;

import duong.dev.dto.PromotionBlackListDTO;
import duong.dev.dto.request.CreatePromotionBlackListDTO;

public interface PromotionBlackListInterface {
	PromotionBlackListDTO create(CreatePromotionBlackListDTO blackListDTO);
	boolean delete(CreatePromotionBlackListDTO blackListDTO);
}
