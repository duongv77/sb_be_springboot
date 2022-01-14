package duong.dev.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "vouchers")
public class Voucher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    private Integer id;

    @Column(name ="voucher_code")
    private String voucherCode;

    @Column(name ="product_quantity")
    private Integer productQuantity;

    @Column(name ="total_price")
    private Integer totalPrice;

    @Column(name ="order_quantity")
    private Integer orderQuantity;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name ="create_date")
    private Date createDate = new Date();

    @Column(name ="start_date")
    private String startDate;

    @Column(name ="end_date")
    private String endDate;

    @Column(name ="sale_percent")
    private Integer salePercent;

    @Column(name ="sale_price")
    private Integer salePrice;

    @Column(name ="status")
    private Integer status;
}
