package duong.dev.dto.request;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import duong.dev.dto.CartdetailDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderDTO {
	@NotNull(message = "Không được để trống địa chỉ")
	private String address;
	@NotNull(message = "Trong giỏ hàng không có gì!")
	private List<CartdetailDTO> cartdetail;
	private String voucherCode;
}
