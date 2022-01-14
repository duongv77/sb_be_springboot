package duong.dev.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCommentRequestDTO {
    @NotNull(message = "Không được để trống comment ")
    private String comment;
    @NotNull(message = "Không đưuọc để trống id sản phẩm")
    private Integer id;
}
