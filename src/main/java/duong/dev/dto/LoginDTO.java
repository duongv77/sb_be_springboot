package duong.dev.dto;

import javax.validation.constraints.NotBlank;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class LoginDTO {
	@NotBlank(message = "Username không được để trống!")
	private String username;
	@NotBlank(message = "Password không được để trống!")
	private String password;
	
	private String email;
	
	private String fullname;
}
