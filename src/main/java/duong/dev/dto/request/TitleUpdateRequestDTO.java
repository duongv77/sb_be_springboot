package duong.dev.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TitleUpdateRequestDTO {
    @NotNull(message = "Không được để trống trạng thái")
    private Integer status;
    @NotNull(message = "Thiếu id sản phẩm")
    private Integer id;
}
