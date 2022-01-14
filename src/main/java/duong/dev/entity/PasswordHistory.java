package duong.dev.entity;


import javax.persistence.*;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "passwordhistorys")
public class PasswordHistory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="id")
	private Integer id;
	
	@Column(name ="datetime")
	private String datetime;
	
	@Column(name ="password")
	private String password;
	
	@Column(name ="code_comfirm")
	private String codeComfirm;
	
	@Column(name ="status")
	private Integer status;
	
	@ManyToOne
	@JoinColumn(
		name = "account_id",
		nullable = false,
		referencedColumnName = "id"
	)
	private Account account;
}
