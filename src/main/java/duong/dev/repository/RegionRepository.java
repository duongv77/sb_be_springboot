package duong.dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import duong.dev.entity.Region;

@Repository
public interface RegionRepository  extends JpaRepository<Region, Integer>{

}
