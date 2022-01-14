package duong.dev.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
@AllArgsConstructor
public class UpdateOrderdetailQuantityRequestDTO {
    @NotNull(message = "Không được để trống sản phẩm")
    private Integer productId;
    @NotNull(message="Không được để trống số lượng")
    private Integer quantity;
}
