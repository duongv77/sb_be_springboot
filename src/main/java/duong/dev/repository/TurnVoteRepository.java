package duong.dev.repository;

import duong.dev.entity.TurnVote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TurnVoteRepository extends JpaRepository<TurnVote, Integer> {
    @Query("select t from TurnVote t where t.productId = ?1 and t.account.id=?2")
    public Optional<TurnVote> findByProductAndAccount(Integer productId, Integer accountId);
}
