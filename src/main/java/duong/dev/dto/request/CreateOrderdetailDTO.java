package duong.dev.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
@AllArgsConstructor
public class CreateOrderdetailDTO {
    @NotNull(message = "Không được để trống order")
    private Integer orderId;

    @NotNull(message = "Không được để trống sản phẩm")
    private Integer productId;
}
