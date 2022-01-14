package duong.dev.dto.response;

import duong.dev.dto.ProductDTO;
 import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PromotionDetailPRDTO {
	
	private Integer sale;
	
	private ProductDTO product;
}
