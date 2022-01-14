package duong.dev.dto.request;

import javax.validation.constraints.NotNull;

import duong.dev.dto.PromotionDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreatePromotionBlackListDTO {
	@NotNull(message="Không được để trống sản phẩm")
	private Integer productId;
	@NotNull(message="Không được để trống chương trình")
	private PromotionDTO promotion;
}
