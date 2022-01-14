package duong.dev.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "turnvotes")
public class TurnVote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    private Integer id;

    @Column(name ="product_id")
    private Integer productId;

    @ManyToOne
    @JoinColumn(
            name = "account_id",
            nullable = true,
            referencedColumnName = "id"
    )
    private Account account;

    @Column(name = "quantity")
    private Integer quantity;
}
