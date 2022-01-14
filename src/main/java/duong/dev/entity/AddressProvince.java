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
@Table(name = "address_province")
public class AddressProvince {
    //tỉnh- thành phố
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

    @OneToMany(mappedBy = "addressProvince", fetch = FetchType.LAZY)
    private List<AddressDistrict> addressDistrict;

    @OneToMany(mappedBy = "addressProvince", fetch = FetchType.LAZY)
    private List<AddressWard> addressWard;
}
