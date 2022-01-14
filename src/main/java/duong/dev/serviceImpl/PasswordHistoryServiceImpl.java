package duong.dev.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import duong.dev.common.Status;
import duong.dev.entity.Account;
import duong.dev.entity.PasswordHistory;
import duong.dev.repository.PasswordHistoryRepository;
import duong.dev.service.PasswordHistoryInterface;

@Component
public class PasswordHistoryServiceImpl implements PasswordHistoryInterface{
	@Autowired private PasswordHistoryRepository passwordHistoryRepo;
	
	public PasswordHistory get(Account account) {
		return passwordHistoryRepo.findByAccountAndStatusOn(account.getId());
	}
	
	public PasswordHistory create(Account account, String strRoandom) {
		LocalDateTime dateTime = java.time.LocalDateTime.now().plusMinutes(5);
		PasswordHistory passwordHistory = new PasswordHistory();
		passwordHistory.setPassword(account.getPassword());
		passwordHistory.setDatetime(dateTime+"");
		passwordHistory.setAccount(account);
		passwordHistory.setStatus(Status.STATUS_HOAT_DONG);
		passwordHistory.setCodeComfirm(strRoandom);
		passwordHistoryRepo.save(passwordHistory);
		return passwordHistory;
	}
	
	public boolean updateStatus(Account account) {
		List<PasswordHistory> passwordHistory = passwordHistoryRepo.findByAccountStatusOn(account.getId());
		if(passwordHistory.size()==0)
			return true;
		passwordHistory.stream().forEach(elm->{
			elm.setStatus(Status.STATUS_KHONG_HOAT_DONG);
			passwordHistoryRepo.save(elm);
		});
		return true;
	}
	
	public List<PasswordHistory> deleteAllOff(Account account) {
		List<PasswordHistory> passwordHistories = passwordHistoryRepo.findByAccountSatusOff(account.getId());
		passwordHistoryRepo.deleteAll(passwordHistories);
		return passwordHistories;
	}
}
