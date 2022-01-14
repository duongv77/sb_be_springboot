package duong.dev.mapper;

import duong.dev.dto.TurnVoteDTO;
import duong.dev.entity.TurnVote;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TurnVoteMapper {
    @Autowired
    private ModelMapper mapper;

    public TurnVote convertToEntity(TurnVoteDTO dto) {
        TurnVote entity = new TurnVote();
        mapper.map(dto, entity);
        return entity;
    }

    public TurnVoteDTO convertToDTO(TurnVote entity) {
        TurnVoteDTO dto = new TurnVoteDTO();
        mapper.map(entity, dto);
        return dto;
    }
}
