package duong.dev.dto.response;

import duong.dev.dto.*;
import duong.dev.entity.Favorite;
import duong.dev.entity.Rate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRateResponseDTO {
    private Integer id;

    @NotBlank(message = "Không được để trống tên")
    private String name;

    @NotNull(message = "Không được để trống giá")
    private Integer price;

    private Integer available;

    private String description;

    private String image;

    @NotNull(message = "Không được để trống số lượng")
    private Integer quantity;

    private String createDate;

    private Integer numberpages;

    private String form;

    private Integer publishyear;

    private String publisher;

    private String supplier;

    private String language;

    private AuthorDTO author;

    private RegionDTO region;

    private TitleDTO title;

    private CategorieDTO categorie;

    private double avgRating;

    private Integer quantityRate;

    private List<RateDTO> rate;

    private List<FavoriteDTO> favorite;
}
