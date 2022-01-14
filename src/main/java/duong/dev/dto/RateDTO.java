package duong.dev.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RateDTO {
	private Integer id;

	private Date createDate;

	private String comment;
	
	private Integer vote;

	private AccountDTO account;
}
