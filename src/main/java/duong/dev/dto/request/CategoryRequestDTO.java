package duong.dev.dto.request;


import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryRequestDTO {
	
	private Integer id;
	@NotNull(message = "Bạn chưa nhập tên danh mục ")
	private String name;
	
	private String note;

	private Integer status;
	
	@NotNull(message ="Bạn chưa chọn loại danh mục")
	private CategoryRequestDTO categorie;
}
