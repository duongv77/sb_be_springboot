package duong.dev.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplyVoucherRequestDTO {
    private String voucherCode;
    private Integer total;
    private Integer quantity;
}
