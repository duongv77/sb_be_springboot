package duong.dev.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import duong.dev.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer>{
	@Query("SELECT e FROM Address e WHERE e.account.id = :id")
	public List<Address> findByIdAccount(@Param("id") Integer id);

	@Query("SELECT e FROM Address e WHERE e.account.id = ?1 and e.id = ?2")
	public Optional<Address> findByIdAccountAndIdAddress(Integer idAccount, Integer idAddress);
}
