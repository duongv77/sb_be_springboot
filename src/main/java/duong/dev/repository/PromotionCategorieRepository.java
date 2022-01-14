package duong.dev.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import duong.dev.entity.PromotionCategorie;

@Repository
public interface PromotionCategorieRepository extends JpaRepository<PromotionCategorie, Integer>{
	@Query("SELECT p FROM PromotionCategorie p WHERE promotion.id = ?1 AND categorie.id = ?2")
	public Optional<PromotionCategorie> finByPromotionAndProduct(Integer promotionId, Integer categoryId);
}
