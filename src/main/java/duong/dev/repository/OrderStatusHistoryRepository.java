package duong.dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import duong.dev.entity.OrderStatusHistory;



@Repository
public interface OrderStatusHistoryRepository extends JpaRepository<OrderStatusHistory, Integer>{

}