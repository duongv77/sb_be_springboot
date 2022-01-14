package duong.dev.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "address_ward")
public class AddressWard {
//    xã phường
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "prefix")
    private String prefix;

    @ManyToOne
    @JoinColumn(
            name = "district_id",
            nullable = false,
            referencedColumnName = "id"
    )
    private AddressDistrict addressDistrict;

    @ManyToOne
    @JoinColumn(
            name = "province_id",
            nullable = false,
            referencedColumnName = "id"
    )
    private AddressProvince addressProvince;
}
