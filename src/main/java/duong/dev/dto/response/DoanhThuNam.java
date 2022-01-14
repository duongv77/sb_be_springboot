package duong.dev.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoanhThuNam {
    private Integer month;
    private Integer revenue;
    private Integer quantity;
}
