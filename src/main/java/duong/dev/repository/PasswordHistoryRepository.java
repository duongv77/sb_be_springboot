package duong.dev.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import duong.dev.entity.PasswordHistory;

public interface PasswordHistoryRepository extends JpaRepository<PasswordHistory, Integer>{
	
	@Query("SELECT e FROM PasswordHistory e WHERE e.account.id = :id and e.status=0")
	public List<PasswordHistory> findByAccountSatusOff(@Param("id") Integer id);
	
	@Query("SELECT e FROM PasswordHistory e WHERE e.account.id = :id and e.status=1")
	public List<PasswordHistory> findByAccountStatusOn(@Param("id") Integer id);

	@Query(value="SELECT * FROM passwordhistorys p JOIN accounts a ON p.account_id = a.id WHERE p.status = 1 AND a.id = :id " +
			"ORDER BY p.datetime DESC LIMIT 0,1", nativeQuery = true)
	public PasswordHistory findByAccountAndStatusOn(@Param("id") Integer id);
}
