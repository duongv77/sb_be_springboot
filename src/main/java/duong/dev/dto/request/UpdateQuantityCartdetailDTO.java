package duong.dev.dto.request;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class UpdateQuantityCartdetailDTO {
	@NotNull(message="Thiếu id cartdetail")
	private Integer id;
	
	@NotNull(message="Thiếu số lượng sản phẩm")
	private Integer quantity;
}
