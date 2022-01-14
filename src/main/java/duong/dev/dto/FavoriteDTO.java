package duong.dev.dto;


import java.util.Date;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteDTO {
	private Integer id;

	private AccountDTO account;
	
	private Integer status;
}
