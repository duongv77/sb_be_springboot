package duong.dev.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "accounts")
public class 	Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="id")
	private Integer id;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "fullname")
	private String fullname;
	
	@Column(name = "photo")
	private String photo;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "activated")
	private Integer activated;
	
	@Column(name = "main_address")
	private String mainAddress;
	
	@Column(name = "phone")
	private String phone;
	
	@OneToOne(mappedBy = "account")
	private Cart cart;
	
	@OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
	private List<Address> address;
	
	@OneToMany(mappedBy = "account" , fetch = FetchType.EAGER)
	private List<RoleAccount> roleAccount;
	
	@OneToMany(mappedBy = "account" , fetch = FetchType.LAZY)
	private List<PasswordHistory> passwordHistory;
	
	@OneToMany(mappedBy = "account")
	private List<Comment> comment;

	@OneToMany(mappedBy = "account")
	private List<TurnVote> turnvote;
	
	@OneToMany(mappedBy = "account")
	private List<Rate> rate;
	
	@OneToMany(mappedBy = "account")
	private List<Order> order;
	
	@OneToMany(mappedBy = "account")
	private List<Favorite> favorite;
	
}
