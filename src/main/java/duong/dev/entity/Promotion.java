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
@Table(name = "promotions")
public class Promotion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "sale")
	private Integer sale;
	
	@Column(name = "create_date")
	private String createDate;
	
	@Column(name = "end_date")
	private String endDate;
	
	@Column(name = "activated")
	private Integer activated;
	
	@Column(name = "description")
	private String description;
	
	@OneToMany(mappedBy = "promotion")
	private List<PromotionBlackList> promotionBlackLists;
	
	@OneToMany(mappedBy = "promotion")
	private List<PromotionCategorie> promotionCategorie;
}
