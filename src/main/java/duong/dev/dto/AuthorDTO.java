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
public class AuthorDTO {
	private Integer id;
	
	@NotBlank(message = "Bạn chưa nhập tên tác giả")
	private String name;
	
	private String photo;
	
	
	private Integer status;
}
