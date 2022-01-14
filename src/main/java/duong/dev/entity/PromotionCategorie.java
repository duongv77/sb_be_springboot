package duong.dev.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "promotioncategory")
public class PromotionCategorie {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(
			name = "promotion_id",
			nullable = false,
			referencedColumnName = "id"
	)
	private Promotion promotion;
	
	@ManyToOne
	@JoinColumn(
			name = "categorie_id",
			nullable = false,
			referencedColumnName = "id"
	)
	private Categorie categorie;
}
