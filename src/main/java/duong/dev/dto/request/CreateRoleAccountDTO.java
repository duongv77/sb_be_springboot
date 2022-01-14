package duong.dev.dto.request;

import duong.dev.dto.AccountDTO;
import duong.dev.dto.RoleDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
@AllArgsConstructor
public class CreateRoleAccountDTO {
    @NotNull(message = "Không được để trống role")
    private RoleDTO role;

    @NotNull(message = "Không được để trống account")
    private AccountDTO account;
}
