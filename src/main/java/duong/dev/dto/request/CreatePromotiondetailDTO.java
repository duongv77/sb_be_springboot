package duong.dev.dto.request;

import javax.validation.constraints.NotNull;

import duong.dev.dto.ProductDTO;
import duong.dev.dto.PromotionDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreatePromotiondetailDTO {
	private Integer id;
	@NotNull(message = "Thiếu sản phẩm")
	private ProductDTO product;
	
	@NotNull(message = "Thiếu chương trình giảm giá")
	private PromotionDTO promotion;
}
