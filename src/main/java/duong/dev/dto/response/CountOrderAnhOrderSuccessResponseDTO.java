package duong.dev.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CountOrderAnhOrderSuccessResponseDTO {
    private Long countOrder;
    private Long countOrderSuccess;

    public CountOrderAnhOrderSuccessResponseDTO(Long countOrder, Long countOrderSuccess) {
        this.countOrder = countOrder;
        this.countOrderSuccess = countOrderSuccess;
    }
}
