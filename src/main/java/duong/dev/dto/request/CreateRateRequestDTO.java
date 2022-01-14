package duong.dev.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class CreateRateRequestDTO {
    @NotNull(message = "Không được để trống rate")
    private Integer rate;
    @NotNull(message = "Không được để trống sản phẩm")
    private Integer productId;
    private String comment;
}
