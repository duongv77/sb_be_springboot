package duong.dev.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import duong.dev.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{
	@Query("SELECT e FROM Product e WHERE promotiondetail IS NULL")
	public List<Product> listProductIsNullPromotion();
	
	@Query(value = "select * from products JOIN categories ON products.categorie_id = categories.id "
			+ "						JOIN promotioncategory ON categories.id = promotioncategory.categorie_id "
			+ "                        JOIN promotions ON promotioncategory.promotion_id = promotions.id "
			+ "WHERE promotions.id = :id and products.id NOT IN (SELECT promotionblacklist.product_id FROM promotionblacklist JOIN promotions ON promotionblacklist.promotion_id = promotions.id WHERE promotions.id = :id)", nativeQuery = true)
	public List<Product> getByCate(@Param("id") Integer id);
	
	@Query(value= "SELECT * FROM products p "
			+ "WHERE p.id = ANY (SELECT p2.product_id FROM promotionblacklist p2 WHERE p2.promotion_id = :id)", nativeQuery = true)
	public List<Product> listProductBlackList(@Param("id") Integer id);
	
	

	@Query("SELECT p FROM Product p WHERE p.available = 1")
	public List<Product> findListProductStatus1();

	@Query("SELECT p FROM Product p WHERE p.name || p.title.name || p.id || p.categorie.name like %:keyword% and p.available = 1")
	public List<Product> searchListProductStatus1(@Param("keyword") String keyword);

	@Query("SELECT DISTINCT p FROM Product p WHERE p.title.id = :id")
	public List<Product> findProductByTitle(@Param("id") Integer id, Pageable pageable);

	@Query("SELECT DISTINCT p FROM Product p WHERE p.categorie.id = ?1")
	public List<Product> findProductByCategorieId(Integer id, Pageable pageable);

	@Query("SELECT p FROM Product p WHERE p.name || p.title.name || p.id || p.categorie.name || p.author.name || p.categorie.name like %:keyword%")
	public List<Product> searchProduct(@Param("keyword") String keyword, Sort sort );

	@Query("SELECT SUM(p.quantity) FROM Product p")
	public Integer productQuantity();

	//lấy 10 sản phẩm mới nhất
	@Query(value = "SELECT * FROM products p ORDER BY p.create_date DESC LIMIT 0,10", nativeQuery = true)
	public List<Product> top10ProductPlus();

	//lấy 10 sản phẩm có % giảm giá nhiều nhất
	@Query(value = "select * from products p1 JOIN categories c ON p1.categorie_id = c.id " +
			"						JOIN promotioncategory p2 ON c.id = p2.categorie_id " +
			"                        JOIN promotions p ON p2.promotion_id = p.id " +
			"WHERE p.activated = 1 and p1.available=1 and p1.id NOT IN (SELECT b.product_id FROM promotionblacklist b WHERE b.promotion_id = p.id) " +
			"ORDER BY p.sale DESC " +
			"LIMIT 0,10", nativeQuery = true)
	public List<Product> top10ProductBestSeller();

	//lấy 10 sản phẩm nổi bật
	@Query(value = "SELECT * FROM products p LEFT JOIN favorites f ON p.id = f.product_id " +
			"GROUP BY f.product_id " +
			"ORDER BY COUNT(f.id) DESC " +
			"LIMIT 0,10 ", nativeQuery = true)
	public List<Product> top10ProductTrending();

	//lấy 10 sản phẩm bán chạy nhất
	@Query(value = "SELECT * FROM products p JOIN orderdetails o ON p.id = o.product_id " +
			"JOIN orders o2 ON o.order_id = o2.id " +
			"WHERE o2.status = 4 " +
			"GROUP BY o.product_id " +
			"ORDER BY SUM(o.quantity) DESC LIMIT 0,10", nativeQuery = true)
	public List<Product> top10ProductBanChay();

	@Query(value="SELECT * FROM products p JOIN favorites f on p.id = f.product_id " +
			" JOIN accounts a ON f.account_id = a.id " +
			" WHERE p.available=1 AND a.id=?1", nativeQuery = true)
	public List<Product> findListProductFavourites(Integer accountId);

	@Query(value="SELECT DISTINCT p.language FROM products p", nativeQuery = true)
	public List<String> findListLanguage();

	@Query(value="SELECT DISTINCT p.publisher FROM products p", nativeQuery = true)
	public List<String> findListNXB();

	@Query(value = "SELECT * FROM products p JOIN categories c on p.categorie_id = c.id " +
			" JOIN categories c2 on c.category_id = c2.id " +
			" WHERE c2.id = ?1 LIMIT 0,10", nativeQuery = true)
	public List<Product> findListToCateLV1(Integer cateId);
}
