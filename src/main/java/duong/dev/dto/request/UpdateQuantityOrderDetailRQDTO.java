package duong.dev.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class UpdateQuantityOrderDetailRQDTO {
    @NotNull(message="Thiếu id orderdetail")
    private Integer orderDetailId;

    @NotNull(message="Thiếu số lượng sản phẩm")
    private Integer quantity;
}
