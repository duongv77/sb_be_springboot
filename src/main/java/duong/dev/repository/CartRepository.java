package duong.dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import duong.dev.entity.Cart;


@Repository
public interface CartRepository extends JpaRepository<Cart, Integer>{
	@Query("select entity from Cart entity where entity.account.id =:id")
	public Cart findByIdAccount(@Param("id") Integer id);


}
