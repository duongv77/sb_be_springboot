package duong.dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import duong.dev.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{

}
