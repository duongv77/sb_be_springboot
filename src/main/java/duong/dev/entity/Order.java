package duong.dev.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import duong.dev.common.Status;
import duong.dev.dto.response.DoanhThuNam;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
@NamedNativeQuery(
		name = "query_doanhthunam",
		query = "select month(o.create_date) AS 'month',SUM(o.total) AS 'revenue', COUNT(o.id) AS 'quantity' from orders o  WHERE o.status=4 GROUP BY month(o.create_date)",
		resultSetMapping = "doanhthunamdto"
)
@NamedNativeQuery(
		name = "query_doanhthutuan",
		query = "SELECT SUM(o.total) AS 'value',CONCAT('Thu ', WEEKDAY(ADDDATE(o.create_date, 2))) AS 'name' FROM orders o WHERE o.status = 4 AND YEARWEEK(o.create_date)=YEARWEEK(NOW()) GROUP BY WEEKDAY(o.create_date)",
		resultSetMapping = "favoriteproduct"
)
@SqlResultSetMapping(
		name = "doanhthunamdto",
		classes = @ConstructorResult(
				targetClass = DoanhThuNam.class,
				columns = {
						@ColumnResult(name = "month", type = Integer.class),
						@ColumnResult(name = "revenue", type = Integer.class),
						@ColumnResult(name = "quantity", type = Integer.class)
				}
		)
)
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="id")
	private Integer id;
	
	@Column(name = "address")
	private String address;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date")
	private Date createDate = new Date();
	
	@Column(name = "total")
	private Integer total;
	
	@Column(name = "status")
	private Integer status;
	
	@Column(name = "typeorder")
	private Integer typeorder;
	
	@Column(name = "description")
	private String description;

	@Column(name = "return_order")
	private String returnOrder;

	@Column(name ="voucher_code")
	private String voucherCode;

	@Column(name ="deposit")
	private Integer deposit;

	@ManyToOne
	@JoinColumn(
			name="account_id",
			nullable = false,
			referencedColumnName = "id"
	)
	private Account account;
	
	@OneToMany(mappedBy = "order")
	private List<OrderDetail> orderdetail;
	
	@OneToMany(mappedBy = "order")
	private List<OrderStatusHistory> orderstatushistorys;

	private String getStatusStr(){
		switch ( status ) {
			case 1:
				return "Ch??? x??c nh???n";
			case  2:
				return "???? x??c nh???n";
			case  3:
				return "??ang giao h??ng";
			case  4:
				return "???? giao h??ng";
			case  5:
				return "???? H???y";
			default:
				return "Kh??ng x??c ?????nh";
		}
	}

	@Override
	public String toString() {
		return "????n h??ng: " + id + ", Kh??ch h??ng: " + account.getUsername() + ", T???ng ????n h??ng: " + total + ", Tr???ng th??i ????n h??ng: " + getStatusStr();
	}
}
