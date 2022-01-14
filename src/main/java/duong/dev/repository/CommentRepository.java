package duong.dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import duong.dev.entity.Comment;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer>{
    @Query("select c from Comment c where c.product.id = ?1 order by c.createDate desc ")
    public List<Comment> findListCommentByProductId(Integer id);

    @Query("select count(c.id) from Comment c ")
    public Long findCountComment();
}

