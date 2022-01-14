package duong.dev.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import duong.dev.entity.Cart;
import duong.dev.entity.Cartdetail;

@Repository
public interface CartdetailRepository extends JpaRepository<Cartdetail, Integer>{
	 
	 @Query("select o from Cartdetail o where o.cart.id=:id ")
	 List<Cartdetail> findCartDetailByIdCart(@Param("id") Integer id);
	 
	 @Query("SELECT entity FROM Cartdetail entity WHERE entity.cart.account.id = :id")
	 public List<Cartdetail> findByAccountID(@Param("id") Integer id);
	 
	 public List<Cartdetail> findByCart(Cart cart);

	 @Query("SELECT c FROM Cartdetail c WHERE c.product.id = ?1 AND c.cart.id = ?2")
	 public Optional<Cartdetail> findCartDetailByProductAndCart(Integer productID, Integer cartId);

	@Query("select c from Cartdetail c where c.cart.account.id = :id")
	public Optional<List<Cartdetail>> findByIdAccounts(@Param("id") Integer id);

	@Query("select SUM(c.quantity) from Cartdetail c where c.cart.account.id = :id")
	public Long quantityProductCart(@Param("id") Integer id);
}
