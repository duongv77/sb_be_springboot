package duong.dev.entity;

import java.util.List;

import javax.persistence.*;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
@NamedNativeQuery(
		name = "query_thongKeSoLuongSanPhamCuaDanhMuc",
		query = "SELECT categories.name, SUM(p.quantity) AS 'value' FROM products p join categories on p.categorie_id = categories.id GROUP BY p.categorie_id",
		resultSetMapping = "favoriteproduct"
)
@NamedNativeQuery(
		name = "query_SanPhamDuocDanhGiaTot",
		query = "SELECT COUNT(r.id) AS 'value', CONCAT('id: ', p.id) AS 'name' FROM products p JOIN rates r on p.id = r.product_id " +
				"WHERE r.vote >= 4 " +
				" and r.create_date>=? " +
				" and r.create_date<=? "+
				"GROUP BY r.product_id  " +
				"ORDER BY COUNT(r.id) DESC " +
				"LIMIT 0,9",
		resultSetMapping = "favoriteproduct"
)
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="id")
	private Integer id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "price")
	private Integer price;
	
	@Column(name = "available")
	private Integer available;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "image")
	private String image;
	
	@Column(name = "quantity")
	private Integer quantity;
	
	@Column(name = "create_date")
	private String createDate;
	
	@Column(name = "numberpages")
	private Integer numberpages;
	
	@Column(name = "form")
	private String form;
	
	@Column(name = "publishyear")
	private Integer publishyear;
	
	@Column(name = "publisher")
	private String publisher;
	
	@Column(name = "supplier")
	private String supplier;
	
	@Column(name = "language")
	private String language;
	
	@OneToMany(mappedBy = "product")
	private List<Comment> comment;
	
	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
	private List<Rate> rate;

	@OneToMany(mappedBy = "product")
	private List<OrderDetail> orderdetail;
	
	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
	private List<Favorite> favorite;
	
	@ManyToOne
	@JoinColumn(
			name = "author_id",
			nullable = true,
			referencedColumnName = "id"
	)
	private Author author;
	
	@ManyToOne
	@JoinColumn(
			name = "categorie_id",
			nullable = true,
			referencedColumnName = "id"
	)
	private Categorie categorie;
	
	@ManyToOne
	@JoinColumn(
			name = "region_id",
			nullable = true,
			referencedColumnName = "id"
	)
	private Region region;
	
	@ManyToOne
	@JoinColumn(
			name = "title_id",
			nullable = true,
			referencedColumnName = "id"
	)
	private Title title;
	
}
