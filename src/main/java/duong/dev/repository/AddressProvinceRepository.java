package duong.dev.repository;

import duong.dev.entity.AddressProvince;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressProvinceRepository extends JpaRepository<AddressProvince, Integer> {
}
