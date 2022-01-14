package duong.dev.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import duong.dev.entity.RoleAccount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class AccountDTO {
	private Integer id;
	
	private String username;
	
	private String password;
	
	@NotNull
	private String fullname;
	
	private String photo;
	
	@NotBlank(message="Không được để trống email")
	private String email;
	
	private String mainAddress;
	
	private String phone;
	
	private Integer activated;
	
	private List<RoleAccountDTO> roleAccount;
	
	private List<AddressDTO> address;
}
