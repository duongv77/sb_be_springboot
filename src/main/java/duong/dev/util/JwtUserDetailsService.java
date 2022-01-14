package duong.dev.util;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import duong.dev.common.ResponeCustom;
import duong.dev.entity.Account;
import duong.dev.exception.AppException;
import duong.dev.repository.AccountRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService{
	@Autowired private AccountRepository accountRepo;
	@Override 
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		Account account = accountRepo.findByUsername(username);
		if(account == null) {
			throw new AppException(ResponeCustom.MESSAGE_CODE_KHONG_TON_TAI, "Username không tồn tại");
		}
		String password = account.getPassword();
		String[] roles = account.getRoleAccount().stream().map(rn -> rn.getRole().getName())
							.collect(Collectors.toList()).toArray(new String[0]);
		return org.springframework.security.core.userdetails.User.withUsername(username).password(password).roles(roles).build();
	}
}




























