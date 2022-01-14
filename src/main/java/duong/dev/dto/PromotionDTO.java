package duong.dev.dto;


import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PromotionDTO {
	private Integer id;
	
	@NotBlank(message = "Không được để trống tên chương trình")
	private String name;
	@NotNull(message = "Không được để trống sale")
	private Integer sale;
	@NotBlank(message = "Không được để trống ngày tạo chương trình")
	private String createDate;
	
	private String description;
	@NotBlank(message = "Không được để trống ngày kết thúc chương trình")
	private String endDate;
	
	private Integer activated;
	
	private List<PromotionBlackListDTO> promotionBlackLists;
}
