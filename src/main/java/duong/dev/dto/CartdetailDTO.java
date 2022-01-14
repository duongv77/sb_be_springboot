package duong.dev.dto;


import java.util.Date;

import org.springframework.stereotype.Component;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class CartdetailDTO {
	private Integer id;
	
	private CartDTO cart;
	
	private ProductDTO product;
	
	private Date createDate;
	
	private Integer quantity;
}
