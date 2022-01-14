package duong.dev.mapper;

import duong.dev.common.Status;
import duong.dev.dto.response.ProductRateResponseDTO;
import duong.dev.entity.Rate;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import duong.dev.dto.ProductDTO;
import duong.dev.entity.Product;

import java.util.List;


@Component
public class ProductMapper {
	@Autowired
	private ModelMapper mapper;
	
	public Product convertToEntity(ProductDTO productD) {
		Product entity = new Product();
		mapper.map(productD, entity);
		return entity;
	}
	
	public ProductDTO convertToDTO(Product entity) {
		ProductDTO productDTO = new ProductDTO();
		mapper.map(entity, productDTO);
		productDTO.setAvgRating(avgRating(entity.getRate()));
		Integer size = entity.getRate() == null? 0 :  entity.getRate().size();
		productDTO.setQuantityRate(size);
		return productDTO;
	}

	public ProductRateResponseDTO convertToDTO2(Product entity) {
		ProductRateResponseDTO productDTO = new ProductRateResponseDTO();
		mapper.map(entity, productDTO);
		productDTO.setAvgRating(avgRating(entity.getRate()));
		Integer size = entity.getRate() == null? 0 :  entity.getRate().size();
		productDTO.setQuantityRate(size);
		return productDTO;
	}

	private double avgRating(List<Rate> listRate){
		try{
			int rageTotal = 0;
			for (Rate rate : listRate ) {
				if(rate.getStatus()== Status.STATUS_HOAT_DONG){
					rageTotal += rate.getVote();
				}
			}
			return rageTotal/listRate.size();
		}catch(Exception e){
			return 0;
		}
	}
}
