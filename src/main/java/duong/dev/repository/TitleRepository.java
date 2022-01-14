package duong.dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import duong.dev.entity.Title;

import java.util.Optional;

@Repository
public interface TitleRepository extends JpaRepository<Title, Integer>{
    @Query("select t from Title t where t.name = ?1")
    public Optional<Title> findByName(String name);

}
