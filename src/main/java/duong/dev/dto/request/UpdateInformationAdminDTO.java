package duong.dev.dto.request;

import java.util.List;

import javax.validation.constraints.NotNull;

import duong.dev.dto.AddressDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateInformationAdminDTO {
	private Integer id;
	
	@NotNull
	private String fullname;
	
	private String photo;
	
	private String phone;
	
	private Integer activated;
	
	private String mainAddress;
}
