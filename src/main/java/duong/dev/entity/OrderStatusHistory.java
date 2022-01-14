package duong.dev.entity;

import java.util.Date;

import javax.persistence.*;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orderstatushistorys")
public class OrderStatusHistory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="id")
	private Integer id;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date")
	private Date createDate = new Date();
	
	@ManyToOne
	@JoinColumn(
		name = "order_id",
		nullable = false,
		referencedColumnName = "id"
	)			
	private Order order;
	
	@ManyToOne
	@JoinColumn(
		name = "statusorder_id ",
		nullable = false,
		referencedColumnName = "id"
	)			
	private StatusOrder statusorder;
}
