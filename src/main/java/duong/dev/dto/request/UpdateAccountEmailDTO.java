package duong.dev.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
@AllArgsConstructor
public class UpdateAccountEmailDTO {
    @NotNull(message = "Không được để trống id account")
    private Integer accountId;

    @NotNull(message = "Không được để trống email")
    private String email;
}
