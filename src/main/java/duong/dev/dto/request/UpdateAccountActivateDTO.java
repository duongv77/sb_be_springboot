package duong.dev.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
public class UpdateAccountActivateDTO {
    @NotNull(message = "Không được để trông Account")
    private Integer id;

    @NotNull(message = "Không được để trông trạng thái")
    private Integer status;

}
