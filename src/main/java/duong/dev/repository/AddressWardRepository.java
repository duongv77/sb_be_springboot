package duong.dev.repository;

import duong.dev.entity.AddressWard;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressWardRepository extends JpaRepository<AddressWard, Integer> {
    @Query("SELECT a FROM AddressWard a")
    public List<AddressWard> findAllCustom(Pageable pageable);

    @Query(value="SELECT * from address_ward a JOIN address_province a2 ON a.province_id = a2.id WHERE a2.id = ?1", nativeQuery = true)
    public List<AddressWard> findByProvince(Integer id );
    @Query(value="SELECT * from address_ward a JOIN address_district a2 ON a.district_id = a2.id WHERE a2.id = ?1", nativeQuery = true)
    public List<AddressWard> findByDistrict(Integer id);
}
