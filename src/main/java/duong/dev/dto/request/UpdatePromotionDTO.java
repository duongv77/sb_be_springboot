package duong.dev.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePromotionDTO {
	@NotNull(message = "Thiếu id")
	private Integer id;
	@NotBlank(message = "Thiếu tên")
	private String name;
	@NotNull(message = "Thiếu sale")
	private Integer sale;
	@NotBlank(message = "Thiếu ngày tạo")
	private String createDate;
	private String description;
	@NotBlank(message = "Thiếu ngày kết thúc")
	private String endDate;
}
