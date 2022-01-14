package duong.dev.dto.request;

import duong.dev.entity.OrderDetail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderReturnRequestDTO {
    @NotNull(message = "Thiếu id order")
    private Integer id;
    @NotNull(message = "Thiếu sản phẩm cần trả lại")
    private List<OrderDetail> orderdetail;
    @NotNull(message = "Thiếu mô tả lí do")
    private String description;
}
