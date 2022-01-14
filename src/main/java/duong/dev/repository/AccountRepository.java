package duong.dev.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import duong.dev.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
	public Account findByUsername(String username);
	public Account findByEmail(String email);
	public Optional<Account> findById(Integer id);

	
	@Query(value="SELECT * FROM accounts a2 WHERE a2.id NOT IN ( " +
			" SELECT a.id FROM accounts a join roleaccount r on a.id = r.account_id " +
			"WHERE r.role_id = 1 )", nativeQuery = true)
	public List<Account> findListAccountNoSupperAdmin();

	@Query("SELECT COUNT(a.id) FROM Account a")
	public Integer accountQuantity();
	
	@Query("SELECT a FROM Account a WHERE a.username || a.phone || a.id  like %:keyword%")
	public List<Account> searchAccount(@Param("keyword") String keyword, Sort sort );
	

}
