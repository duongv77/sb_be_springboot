package duong.dev.repository;

import duong.dev.entity.AddressDistrict;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressDistrictRepository extends JpaRepository<AddressDistrict, Integer> {
    @Query(value="SELECT * from address_district a JOIN address_province a2 ON a.province_id = a2.id WHERE a2.id = ?1", nativeQuery = true)
    public List<AddressDistrict> findByProvince(Integer id);

    @Query("SELECT a FROM AddressDistrict a")
    public List<AddressDistrict> findAllCustom(Pageable pageable);
}
