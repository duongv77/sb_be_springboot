package duong.dev.entity;

import javax.persistence.*;

import duong.dev.dto.response.DoanhThuNam;
import duong.dev.dto.response.RateMonth;
import lombok.*;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "rates")
@NamedNativeQuery(
		name = "query_star",
		query = "SELECT CONCAT(r.vote,' sao') AS 'name',COUNT(r.vote) AS 'value' FROM rates r WHERE MONTH(r.create_date) = MONTH(NOW()) GROUP BY r.vote",
		resultSetMapping = "vote_start"
)
@NamedNativeQuery(
		name = "query_voteProductBad",
		query = "SELECT COUNT(r.id) AS 'value', CONCAT('id: ',r.product_id) AS 'name' FROM rates r WHERE r.vote<3 GROUP BY r.product_id ORDER BY COUNT(r.id) DESC LIMIT 0,10",
		resultSetMapping = "vote_start"
)
@SqlResultSetMapping(
		name = "vote_start",
		classes = @ConstructorResult(
				targetClass = RateMonth.class,
				columns = {
						@ColumnResult(name = "name", type = String.class),
						@ColumnResult(name = "value", type = Integer.class)
				}
		)
)
public class Rate {
	@Id
	@Column(name ="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name ="vote")
	private Integer vote;
	
	@Column(name = "status")
	private Integer status;

	@Column(name = "comment")
	private String comment;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date")
	private Date createDate = new Date();
	
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
