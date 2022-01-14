package duong.dev.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "address_district")
public class AddressDistrict {
//    quận huyện
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
            name = "province_id",
            nullable = false,
            referencedColumnName = "id"
    )
    private AddressProvince addressProvince;

    @OneToMany(mappedBy = "addressDistrict", fetch = FetchType.LAZY)
    private List<AddressWard> addressWard;
}
