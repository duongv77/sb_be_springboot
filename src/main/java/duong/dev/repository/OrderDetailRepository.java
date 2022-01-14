package duong.dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import duong.dev.entity.OrderDetail;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer>{
    @Transactional
    @Modifying
    @Query("update OrderDetail o set o.quantity = ?1 where o.id = ?2")
    public void updateQuantity(Integer quantity, Integer orderDetailId);

    @Query("SELECT o FROM OrderDetail o WHERE o.product.id = ?1 AND o.order.id = ?2")
    public Optional<OrderDetail> findOrderDetailByProductIdAndOrderId(Integer productId, Integer orderId);

    @Query("SELECT SUM(o.quantity) FROM OrderDetail o WHERE o.order.status = 4")
    public Integer productQuantitySold();

    @Query("SELECT o FROM OrderDetail o WHERE o.order.id = ?1 AND o.status = 6")
    public List<OrderDetail> findByOrderAndStatus(Integer orderID);

    @Query("SELECT o FROM OrderDetail o WHERE o.order.id = ?1 ")
    public List<OrderDetail> findByOrderId(Integer orderID);
}
