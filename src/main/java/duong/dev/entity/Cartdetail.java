package duong.dev.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cartdetails")
public class Cartdetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="id")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(
			name = "cart_id",
			nullable = false,
			referencedColumnName = "id"
	)
	private Cart cart;
	
	@OneToOne
	@JoinColumn(
			name = "product_id",
			nullable = false,
			referencedColumnName = "id"
		)
	private Product product;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date")
	private Date createDate = new Date();
	
	@Column(name = "quantity")
	private Integer quantity;
	
}
