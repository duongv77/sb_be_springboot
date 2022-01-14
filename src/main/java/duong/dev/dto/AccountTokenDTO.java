package duong.dev.dto;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class AccountTokenDTO {
	private Integer id;

	private String accessToken;
	
	private String username;
	
	private String fullname;
	
	private String image;
	
	private List<RoleAccountDTO> roleAccount;
}
