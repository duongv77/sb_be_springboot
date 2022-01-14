package duong.dev.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import duong.dev.entity.Author;
import duong.dev.entity.Order;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
	
	@Query("SELECT a FROM Author a WHERE a.name || a.id like %:keyword% ")
	public List<Author> searchAuthor(@Param("keyword") String keyword, Sort sort);
	
	

}
