package duong.dev.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import duong.dev.dto.AuthorDTO;
import duong.dev.dto.CategorieDTO;
import duong.dev.dto.RegionDTO;
import duong.dev.dto.TitleDTO;
import duong.dev.entity.Author;
import duong.dev.entity.Categorie;
import duong.dev.entity.Region;
import duong.dev.entity.Title;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateProductDTO {
	private Integer id;
	
	@NotBlank(message = "Không được để trống tên")
	private String name;
	
	@NotNull(message = "Không được để trống giá")
	private Integer price;
	
	private Integer available;
	
	private String description;
	
	private String image;
	
	@NotNull(message = "Không được để trống số lượng")
	private Integer quantity;
	
	private Integer numberpages;
	
	private String form;
	
	private Integer publishyear;
	
	private String publisher;
	
	private String supplier;
	
	private String language;
	
	@NotNull(message = "Không được để trống tác giả")
	private Author author;
	@NotNull(message = "Không được để trống khu vực")
	private Region region;
	
	private Title title;

	private Categorie categorie;
}
