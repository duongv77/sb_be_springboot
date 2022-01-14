package duong.dev.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class VoucherDTO {
    private Integer id;

    @NotNull(message = "Không được để trống mã code")
    private String voucherCode;

    private Integer productQuantity;

    private Integer totalPrice;

    private Integer orderQuantity;

    private Date createDate= new Date();

    @NotNull(message = "Không được để trống ngày bắt đầu")
    private String startDate;

    @NotNull(message = "Không được để trống ngày kết thúc")
    private String endDate;

    private Integer salePercent;

    private Integer salePrice;

    private Integer status;
}
