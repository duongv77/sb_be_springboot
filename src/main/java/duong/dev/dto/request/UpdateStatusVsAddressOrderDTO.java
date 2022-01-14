package duong.dev.dto.request;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class UpdateStatusVsAddressOrderDTO {
	@NotBlank(message = "Thiếu id order")
	private Integer id;
	@NotBlank(message = "Thiếu status order")
	private Integer status;
	@NotNull(message = "Thiếu địa chỉ")
	private String address;
}
