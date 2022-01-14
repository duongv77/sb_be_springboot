package duong.dev.dto;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class CartDTO {
	private Integer id;
	
	private AccountDTO accountD;
	
	
	private Integer status;
	
	private List<CartdetailDTO> cartdetailDTO;
}
