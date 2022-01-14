package duong.dev.dto;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class TitleDTO {
	private Integer id;

	@NotNull(message="Không được để trống tên đầu sách")
	private String name;
	
	private String note;

	private Integer status;
}
