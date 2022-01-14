package duong.dev.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateStatusOrderRequestDTO {
    @NotNull(message = "Không được để trống Id order")
    private  Integer id;

    private String description;
}
