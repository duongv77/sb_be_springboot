package duong.dev.entity;


import java.util.Date;

import javax.persistence.*;

import duong.dev.dto.response.RateMonth;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "favorites")
@NamedNativeQuery(
		name = "query_topfavorite",
		query = "SELECT COUNT(f.id) AS 'value', CONCAT('id: ', p.id) AS 'name' FROM products p JOIN favorites f on p.id = f.product_id " +
				"GROUP BY f.product_id  " +
				"ORDER BY COUNT(f.id) DESC " +
				"LIMIT 0,9",
		resultSetMapping = "favoriteproduct"
)
@SqlResultSetMapping(
		name = "favoriteproduct",
		classes = @ConstructorResult(
				targetClass = RateMonth.class,
				columns = {
						@ColumnResult(name = "name", type = String.class),
						@ColumnResult(name = "value", type = Integer.class)
				}
		)
)
public class Favorite {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="id")
	private Integer id;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date")
	private Date createDate = new Date();
	
	@Column(name = "status")
	private Integer status;
	
	@ManyToOne
	@JoinColumn(
			name = "account_id",
			nullable = false,
			referencedColumnName = "id"
	)
	private Account account;
	
	@ManyToOne
	@JoinColumn(
			name = "product_id",
			nullable = false,
			referencedColumnName = "id"
	)
	private Product product;
}
