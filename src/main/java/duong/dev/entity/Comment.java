package duong.dev.entity;

import java.util.Date;

import javax.persistence.*;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comments")
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="id")
	private Integer id;
	
	@Column(name = "comment")
	private String comment;
	
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
