package duong.dev.dto;


import java.util.List;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class CategorieDTO {
	private Integer id;
	
	private String name;
	
	private String note;
	
	private Integer status;
	
	private List<CategorieDTO> categories;
	
	
//	private List<PromotionCategorieDTO> promotionCategorie;
	
}
