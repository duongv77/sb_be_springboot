package duong.dev.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportQuantity {
    private Integer productQuantity;
    private Integer productQuantitySold;
    private Integer accountQuantity;
    private Integer promotionQuantity;
    private Integer orderSold;
    private Integer quantityRate;
}
