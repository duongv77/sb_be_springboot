package duong.dev.serviceImpl;

import duong.dev.JwtTokenUtil;
import duong.dev.common.ResponeCustom;
import duong.dev.dto.AccountDTO;
import duong.dev.dto.TurnVoteDTO;
import duong.dev.entity.Account;
import duong.dev.entity.TurnVote;
import duong.dev.exception.AppException;
import duong.dev.mapper.TurnVoteMapper;
import duong.dev.repository.TurnVoteRepository;
import duong.dev.service.TurnVoteInteface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Optional;

@Service
public class TurnVoteServiceImpl implements TurnVoteInteface {
    @Autowired private TurnVoteRepository turnVoteRepo;
    @Autowired private OrderServiceImpl orderService;
    @Autowired private TurnVoteMapper turnVoteMapper;

    public Account getAccount() throws IOException, ServletException{
        return orderService.getAccount();
    }


    @Override
    public TurnVoteDTO findByAccountAndProduct(Integer productId) throws IOException, ServletException {
        Optional<TurnVote> turnVoteOptional = turnVoteRepo.findByProductAndAccount( productId, getAccount().getId());
        if(!turnVoteOptional.isPresent())
            throw new AppException(ResponeCustom.MESSAGE_CODE_KHONG_THANH_CONG, "Bạn không có lượt đánh giá nào");
        return turnVoteMapper.convertToDTO(turnVoteOptional.get());
    }

    @Override
    public TurnVoteDTO updateQuantityMinus(Integer productId) throws IOException, ServletException{
        Optional<TurnVote> turnVoteOptional = turnVoteRepo.findByProductAndAccount(productId, getAccount().getId());
        if(!turnVoteOptional.isPresent())
            throw new AppException(ResponeCustom.MESSAGE_CODE_KHONG_THANH_CONG, "Bạn không có lượt đánh giá nào");
        TurnVote turnVote = turnVoteOptional.get();
        if(turnVote.getQuantity()<=0)
            throw new AppException(ResponeCustom.MESSAGE_CODE_KHONG_THANH_CONG, "Bạn không còn lượt đánh giá");
        turnVote.setQuantity(turnVote.getQuantity()-1);
        turnVoteRepo.save(turnVote);
        return turnVoteMapper.convertToDTO(turnVote);
    }

    @Override
    public TurnVoteDTO updateQuantityToAdd(Account account, Integer productId) {
        Optional<TurnVote> turnVoteOptional = turnVoteRepo.findByProductAndAccount( productId, account.getId());
        if(turnVoteOptional.isPresent()){
            TurnVote turnVote = turnVoteOptional.get();
            turnVote.setQuantity(turnVote.getQuantity()+1);
            turnVoteRepo.save(turnVote);
        }else{
            TurnVote turnVote = new TurnVote();
            turnVote.setQuantity(1);
            turnVote.setAccount(account);
            turnVote.setProductId(productId);
            turnVoteRepo.save(turnVote);
        }
        return null;
    }

}
