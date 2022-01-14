package duong.dev.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import duong.dev.entity.Categorie;
import duong.dev.entity.Product;

@Repository
public interface CategorieRepository extends JpaRepository<Categorie, Integer>{
	@Query("SELECT e FROM Categorie e WHERE id = :id")
	public List<Categorie> findCategoryBig(@Param("id") Integer id);
	
	@Query(value="SELECT * FROM categories "
			+ "WHERE categories.id NOT IN  (SELECT categories.id FROM categories WHERE categories.category_id=1) "
			+ "	AND category_id IS NOT NULL "
			+ "	AND categories.id NOT IN (SELECT p.categorie_id FROM promotioncategory p WHERE p.promotion_id = :promotionId) "
			+ "	AND categories.id NOT IN (SELECT p.categorie_id FROM promotioncategory p JOIN promotions p2 ON p.promotion_id = p2.id WHERE p2.activated != 3)", nativeQuery = true)
	public List<Categorie> findCatenotPromotion(@Param("promotionId") Integer promotionId);
	
	@Query(value = "SELECT  * FROM categories c "
			+ "WHERE c.id NOT IN  (SELECT categories.id FROM categories WHERE categories.category_id=1) "
			+ "	AND category_id IS NOT NULL "
			+ "	AND c.id  IN (SELECT p.categorie_id FROM promotioncategory p JOIN promotions p2 ON p.promotion_id = p2.id WHERE p2.activated != 3) "
			+ "	AND  c.id = :categorieId", nativeQuery = true)
	public Optional<Categorie> findByICateStatusOff(@Param("categorieId") Integer categorieId);
	
	@Query(value = "SELECT * from categories c WHERE c.category_id != 1  ", nativeQuery = true)
	public List<Categorie> findListCateLV2();
	
	@Query(value = "select * from categories where category_id =1", nativeQuery = true)
	public List<Categorie> findListCateLV1();
	
	@Query(value = "select * from categories where category_id is not null",nativeQuery = true)
	public List<Categorie> viewListCategory(); 
	
	@Query(value = "SELECT e.id,e.name,e.status,e.note,e.category_id FROM categories e inner join categories m on m.id = e.category_id\r\n"
			+ "where m.id = :id", nativeQuery = true)
	public List<Categorie> listSubCategory(@Param("id") Integer id);

	@Query("SELECT c from  Categorie c where c.id != 2 and c.categorie.id = 1")
	public List<Categorie> listCategoryShowNav();
	
	@Query("SELECT c FROM Categorie c WHERE (c.name || c.id   like %:keyword%) and c.id != 1")
	public List<Categorie> searchCategory(@Param("keyword") String keyword, Sort sort );
	
	@Query(value="SELECT * from categories where category_id = 1 ", nativeQuery = true)
	public List<Categorie> listTypeCate();
	
	@Query(value="SELECT * from categories where category_id = 1 or category_id is null ", nativeQuery = true)
	public List<Categorie> listTypeCate1();
}
