package duong.dev.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import duong.dev.entity.Rate;

import java.util.List;

@Repository
public interface RateRepository extends JpaRepository<Rate, Integer>{
    @Query("select e from Rate e where e.product.id = ?1 and e.status = 1")
    public List<Rate> findListRateByProductId(Integer productId, Pageable pageable);

    @Query("select count(e.id) from Rate e ")
    public Integer rateQuantity();

    @Query("select count(e.id) from Rate e where e.vote = 4 or e.vote = 5 ")
    public Long rateCountGood();

    @Query("select count(e.id) from Rate e ")
    public Long rateCountAll();
}
