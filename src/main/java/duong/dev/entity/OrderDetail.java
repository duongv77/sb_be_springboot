package duong.dev.entity;

import javax.persistence.*;

import lombok.*;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orderdetails")
@NamedNativeQuery(
		name = "query_sanPhambanChay",
		query = "SELECT SUM(o.quantity) AS 'value' ,CONCAT('id: ',  o.product_id) AS 'name' FROM orderdetails o JOIN orders o2 on o.order_id = o2.id " +
				"WHERE o2.status = 4 " +
				"GROUP BY o.product_id " +
				"ORDER BY SUM(o.quantity) DESC " +
				"LIMIT 0,9",
		resultSetMapping = "favoriteproduct"
)
public class OrderDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="id")
	private Integer id;
	
	@Column(name = "quantity")
	private Integer quantity;
	
	@Column(name = "price")
	private Integer price;
	
	@Column(name = "status")
	private Integer status;
	
	@Column(name = "sale")
	private Integer sale;
	
	@ManyToOne
	@JoinColumn(
		name = "order_id",
		nullable = false,
		referencedColumnName = "id"
	)			
	private Order order;
	
	@ManyToOne
	@JoinColumn(
		name = "product_id",
		nullable = false,
		referencedColumnName = "id"
	)
	private Product product;
}
