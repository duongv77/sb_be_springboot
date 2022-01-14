package duong.dev.dto;


import java.util.Date;

import duong.dev.entity.Account;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class CommentDTO {
	private Integer id;

	private String comment;
	
	private Date createDate = new Date();
	
	private Integer status;

	private AccountDTO account;
}
