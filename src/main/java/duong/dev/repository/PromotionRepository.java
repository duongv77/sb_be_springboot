package duong.dev.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import duong.dev.entity.Promotion;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Integer>{
	@Transactional
	@Modifying
	@Query("update Promotion t set t.activated = ?1 where t.id = ?2")
	void updateStatus(Integer status,Integer promotionId );
	
	@Query(value = "SELECT * FROM promotions p JOIN promotioncategory p2 ON p.id = p2.promotion_id " +
			"JOIN categories c ON p2.categorie_id = c.id " +
			"JOIN products p3 ON c.id = p3.categorie_id " +
			"WHERE p3.id = ?1 AND p.activated = 1 AND p3.id NOT IN(SELECT p4.product_id FROM promotionblacklist p4 WHERE p4.promotion_id = p.id)", nativeQuery = true)
	Optional<Promotion> getPromotionActiveByIdProduct(Integer productId);

	@Query("select count(p.id) from Promotion p")
	public Integer promotionQuantity();

	@Query("SELECT p FROM Promotion p WHERE p.name || p.id  like %:keyword%")
	public List<Promotion> searchPromotion(@Param("keyword") String keyword, Sort sort );

//	list chương trình đã quá ngày
	@Query(value = "SELECT * FROM promotions p WHERE p.end_date < CURDATE() AND p.activated = 1 ", nativeQuery = true)
	public List<Promotion> findListPromotionStop();

//	list chương trình đã đến ngày mà chưa start
	@Query(value = "select * FROM promotions p WHERE (CURDATE() BETWEEN p.create_date AND p.end_date) AND p.activated=0", nativeQuery = true)
	public List<Promotion> findListPromotionReady();

	@Query("select count(p.id) from Promotion p")
	public Long findCountPromotion();
}
