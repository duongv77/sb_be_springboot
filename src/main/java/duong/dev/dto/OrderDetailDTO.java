package duong.dev.dto;



import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class OrderDetailDTO {
	private Integer id;
	
	private Integer quantity;
	
	private Integer price;
	
	private ProductDTO product;
	
	private Integer sale;

	private Integer status;
}

