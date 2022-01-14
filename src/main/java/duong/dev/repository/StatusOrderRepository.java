package duong.dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import duong.dev.entity.StatusOrder;

@Repository
public interface StatusOrderRepository extends JpaRepository<StatusOrder, Integer>{

}
