package duong.dev.dto.request;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class CreateOrderTypeDTO {
	private String description;
	@NotNull(message = "Thiếu địa chỉ")
	private String address;
	@NotNull(message = "Không được để trống id đơn hàng")
	private Integer orderId;

	private Integer shipping;
}
