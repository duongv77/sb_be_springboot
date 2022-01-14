package duong.dev.entity;


import javax.persistence.*;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roleaccount")
public class RoleAccount {
	@Id
	@Column(name ="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(
		name = "account_id",
		nullable = false,
		referencedColumnName = "id"
	)			
	private Account account;
	
	@ManyToOne
	@JoinColumn(
		name = "role_id",
		nullable = false,
		referencedColumnName = "id"
	)			
	private Role role;
}
