package duong.dev.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "authors")
public class Author {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="id")
	private Integer id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "photo")
	private String photo;
	
	@Column(name = "status")
	private Integer status;
	
	@OneToMany(mappedBy = "author", fetch = FetchType.EAGER)
	private List<Product> product;
}
