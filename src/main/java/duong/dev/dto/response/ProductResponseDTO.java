package duong.dev.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDTO {
    private List<PromotionDetailPRDTO> listProduct1;
    private List<PromotionDetailPRDTO> listProduct2;
    private List<PromotionDetailPRDTO> listProduct3;
    private List<PromotionDetailPRDTO> listProduct4;
    private List<PromotionDetailPRDTO> listProduct5;
}
