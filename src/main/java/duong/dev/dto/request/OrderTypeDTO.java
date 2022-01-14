package duong.dev.dto.request;


import duong.dev.dto.ProductDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class OrderTypeDTO {
	@NotNull(message = "Thiếu số lượng")
	private Integer quantity;
	@NotNull(message = "Thiếu sản phẩm")
	private ProductDTO product;
}
