package duong.dev.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import duong.dev.dto.PromotionBlackListDTO;
import duong.dev.dto.request.CreatePromotionBlackListDTO;
import duong.dev.entity.PromotionBlackList;
import duong.dev.mapper.PromotionBlackListMapper;
import duong.dev.mapper.PromotionMapper;
import duong.dev.repository.PromotionBlackListrepository;
import duong.dev.service.PromotionBlackListInterface;

@Service
public class PromotionBlackListServiceImpl implements PromotionBlackListInterface{
	@Autowired private PromotionBlackListrepository proMBlackRepo;
	@Autowired private PromotionBlackListMapper proMBlackMapper;
	@Autowired private PromotionMapper promotionMapper;
	
	@Override
	public PromotionBlackListDTO create(CreatePromotionBlackListDTO blackListDTO) {
		PromotionBlackList promotionBlackList = new PromotionBlackList();
		promotionBlackList.setProductId(blackListDTO.getProductId());
		promotionBlackList.setPromotion(promotionMapper.convertToEntity(blackListDTO.getPromotion()));
		proMBlackRepo.save(promotionBlackList);
		return proMBlackMapper.convertToDTO(promotionBlackList);
	}

	@Override
	public boolean delete(CreatePromotionBlackListDTO blackListDTO) {
		PromotionBlackList blackList = proMBlackRepo.finByProductIdAndPromotionId(blackListDTO.getProductId(), blackListDTO.getPromotion().getId());
		proMBlackRepo.delete(blackList);
		return true;
	}
	
	

}
