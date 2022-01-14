package duong.dev.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressWardDTO {
    private Integer id;

    private String name;

    private String prefix;
}
