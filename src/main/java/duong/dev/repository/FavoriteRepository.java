package duong.dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import duong.dev.entity.Favorite;

import java.util.Optional;


@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Integer>{
    @Query("select f from Favorite f where f.account.id = ?1 and f.product.id = ?2")
    public Optional<Favorite> findByAccountAndProduct(Integer accountId, Integer productId);
}

