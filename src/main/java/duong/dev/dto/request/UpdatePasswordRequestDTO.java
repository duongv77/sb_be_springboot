package duong.dev.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePasswordRequestDTO {
    @NotNull(message = "Thiếu Id ")
    private Integer id;
    @NotNull(message = "Thiếu số điện thoại")
    private String passwordOld;
    @NotNull(message = "Thiếu số họ tên")
    private String passwordNew;
}
