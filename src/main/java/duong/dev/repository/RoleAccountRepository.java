package duong.dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import duong.dev.entity.RoleAccount;

import java.util.Optional;

@Repository
public interface RoleAccountRepository extends JpaRepository<RoleAccount, Integer>{
    @Query("SELECT r FROM RoleAccount r WHERE r.account.id = ?1 AND r.role.id = ?2")
    public Optional<RoleAccount> findByAccountIdAndRoleId(Integer accountId, Integer roleId);
}
