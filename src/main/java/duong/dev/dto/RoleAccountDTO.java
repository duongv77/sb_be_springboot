package duong.dev.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleAccountDTO {
	private Integer id;
	
	private RoleDTO role;
}
