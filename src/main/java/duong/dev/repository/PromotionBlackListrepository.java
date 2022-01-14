package duong.dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import duong.dev.entity.PromotionBlackList;

@Repository
public interface PromotionBlackListrepository extends JpaRepository<PromotionBlackList, Integer>{
	@Query("SELECT p FROM PromotionBlackList p WHERE productId = :productId AND promotion.id = :promotionId")
	public PromotionBlackList finByProductIdAndPromotionId(@Param("productId") Integer productId, @Param("promotionId") Integer promotionId);
}
