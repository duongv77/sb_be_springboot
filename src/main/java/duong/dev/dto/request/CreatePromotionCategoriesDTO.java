package duong.dev.dto.request;

import javax.validation.constraints.NotNull;

import duong.dev.dto.CategorieDTO;
import duong.dev.dto.PromotionDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreatePromotionCategoriesDTO {
	@NotNull(message = "Không được để trống thể loại")
	private CategorieDTO categorie;
	@NotNull(message = "Không được để trống chương trình giảm giá")
	private PromotionDTO promotion;
}
