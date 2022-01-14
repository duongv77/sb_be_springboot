package duong.dev.dto.response;

import duong.dev.dto.CartdetailDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CartdetailSaleResponseDTO {
	private Integer sale;
	private CartdetailDTO cartdetail;
}	
