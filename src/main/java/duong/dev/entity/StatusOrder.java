package duong.dev.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "statusorder")
public class StatusOrder {
	@Id
	@Column(name ="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name ="status")
	private String status;
	
	@OneToMany(mappedBy = "statusorder")
	private List<OrderStatusHistory> orderstatushistorys;

	public StatusOrder(Integer id, String status) {
		this.id = id;
		this.status = status;
	}
}
