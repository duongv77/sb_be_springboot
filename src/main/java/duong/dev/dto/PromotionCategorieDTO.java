package duong.dev.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PromotionCategorieDTO {
	private Integer id;
	
//	private PromotionDTO promotion;
	
	private CategorieDTO categorie;
}
