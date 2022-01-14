package duong.dev.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "carts")
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="id")
	private Integer id;
	
	@JsonIgnore
	@OneToOne
	@JoinColumn(
			name = "account_id",
			nullable = false,
			referencedColumnName = "id"
	)
	private Account account;
	
	@JsonIgnore
	@OneToMany(mappedBy = "cart")
	private List<Cartdetail> cartdetail;
	
	@Column(name = "status")
	private Integer status;
}
