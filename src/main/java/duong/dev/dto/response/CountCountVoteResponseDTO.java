package duong.dev.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CountCountVoteResponseDTO {
    private Long countVoteGood;
    private Long countVoteAll;

    public CountCountVoteResponseDTO(Long countVoteGood, Long countVoteAll) {
        this.countVoteGood = countVoteGood;
        this.countVoteAll = countVoteAll;
    }
}
