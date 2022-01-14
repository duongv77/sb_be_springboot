package duong.dev.repository;

import java.util.List;

import duong.dev.dto.response.DoanhThuNam;
import duong.dev.entity.OrderDetail;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import duong.dev.entity.Order;

import javax.transaction.Transactional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>{
	public List<Order> findByStatus(Integer status, Sort sort);

	@Query("select o from Order o where o.status = 4 or o.status = 6")
	public List<Order> findByStatusSuccess(Sort sort);
	
	public List<Order> findAll();

	@Transactional
	@Modifying
	@Query("update Order o set o.status = ?1 where o.id = ?2")
	public void updateStatusAccept(Integer status, Integer orderId);

	@Transactional
	@Modifying
	@Query("update Order o set o.status = ?1 where o.id = ?2")
	public void updateStatusCancel(Integer status, Integer orderId);

	@Query(value="SELECT * FROM orders o " +
			"WHERE DATEDIFF(CURDATE(), o.create_date) <2 AND o.status = 1 " +
			"ORDER BY o.create_date DESC", nativeQuery = true)
	public List<Order> findByListOrderNew();

	@Query("SELECT o FROM Order o WHERE MONTH(o.createDate) = MONTH(NOW())")
	public List<Order> finByListOrderMonth();

	@Query("select count(o.id) from Order o where o.status = 4")
	public Integer orderQuantitySold();

	@Query("SELECT o FROM Order o where o.account.id = ?1")
	public List<Order> finByListOrderByAccount(Integer accountId, Sort sort);

	@Query("select o from Order o where o.status = 7")
	public List<Order> findByStatusDefault(Sort sort);

	@Query("select Count(o.id) from Order o where o.account.id = ?1")
	public Long findQuantityOrder(Integer id);

	@Query("SELECT count(o.id) from Order o where o.status != 7")
	public Long findCoundTotalOrder();

	@Query("SELECT count(o.id) from Order o where o.status = 4")
	public Long findCoundTotalOrderSuccess();

	@Query("SELECT o FROM Order o where o.address like %:keyword% or o.description like %:keyword% or o.account.fullname like %:keyword%")
	public List<Order> searchOrder(Sort sort,@Param("keyword") String keyword);

	@Query("SELECT count(o.account.id) from Order o where o.account.id = ?1")
	public Long quantityOrderByAccount(Integer idAccount);

	@Query(value="SELECT * FROM orders o WHERE DATE_FORMAT(o.create_date,  '%M %d, %Y') = DATE_FORMAT(CURDATE(),  '%M %d, %Y')", nativeQuery = true)
	public List<Order> findListOrderDay();

	@Query(value="SELECT sum(o.total) FROM orders o WHERE DATE_FORMAT(o.create_date,  '%M %d, %Y') = DATE_FORMAT(CURDATE(),  '%M %d, %Y') AND o.status = 4", nativeQuery = true)
	public Long findTotalDay();
}