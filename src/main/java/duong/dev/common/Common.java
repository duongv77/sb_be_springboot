package duong.dev.common;


import duong.dev.JwtTokenUtil;
import duong.dev.dto.AccountDTO;
import duong.dev.entity.Account;
import duong.dev.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Common {

	public static final String URL = "http://localhost:3000";
	public static final Integer ID_CATEGORY_BIG = 1;
	public static final Integer ID_CATEGORY_KHONG_XAC_DINH = 2;
	public static final String DATE_NOW = java.time.LocalDate.now()+" 00:00:00";
	 static final String DATE_TIME_NOW = java.time.LocalDateTime.now()+"";
	public static Date convertDateTime(String dateTimeStr){
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
			return sdf.parse(dateTimeStr);
		}catch(Exception e){
			return null;
		}
	}

	//Trả về số phút giữa 2 mốc thời gian
	public static long beetweenTime(String startTimeStr, String endTimeStr){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime startTime= LocalDateTime.parse(startTimeStr, formatter);
		LocalDateTime endTime = LocalDateTime.parse(endTimeStr, formatter);
		return java.time.Duration.between(endTime, startTime).toMinutes();
	}
}
