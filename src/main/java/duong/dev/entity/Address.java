package duong.dev.entity;

import javax.persistence.*;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "address")
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="id")
	private Integer id;
	
	@Column(name = "address")
	private String address;
	
	@ManyToOne
	@JoinColumn(
			name = "account_id",
			nullable = false,
			referencedColumnName = "id"
	)
	private Account account;
}
