package duong.dev.dto.response;

import java.util.List;

import duong.dev.dto.PromotionBlackListDTO;
import duong.dev.dto.PromotionCategorieDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PromotionPrDTO {
	private Integer id;
	
	private String name;
	
	private Integer sale;
	
	private String createDate;
	
	private String description;
	
	private String endDate;
	
	private Integer activated;
	
	private List<PromotionDetailPRDTO> promotiondetail;
	
	private List<PromotionCategorieDTO> promotionCategorie;
	
	private List<PromotionBlackListDTO> promotionBlackLists;
}
