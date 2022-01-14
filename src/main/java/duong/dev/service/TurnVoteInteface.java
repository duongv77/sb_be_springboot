package duong.dev.service;

import duong.dev.dto.TurnVoteDTO;
import duong.dev.entity.Account;

import javax.servlet.ServletException;
import java.io.IOException;

public interface TurnVoteInteface {
    public TurnVoteDTO findByAccountAndProduct(Integer productId) throws IOException, ServletException;
    public TurnVoteDTO updateQuantityMinus(Integer productId) throws IOException, ServletException;
    public TurnVoteDTO updateQuantityToAdd(Account account, Integer productId);
}
