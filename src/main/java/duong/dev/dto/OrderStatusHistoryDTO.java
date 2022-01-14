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
public class OrderStatusHistoryDTO {
	private Integer id;
	
	private Date createDate;

	private StatusOrderDTO statusorder;
}
