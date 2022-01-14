package duong.dev.dto.request;

import duong.dev.dto.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TitleProductDTO {
    private Integer id;

    private String name;

    private String note;

    private Integer status;

    private List<ProductDTO> products;
}
