package duong.dev;

import duong.dev.entity.Promotion;
import duong.dev.repository.OrderRepository;
import duong.dev.repository.PromotionRepository;
import duong.dev.service.PromotionInterface;
import duong.dev.thread.PromotionStart;
import duong.dev.thread.PromotionThread;
import duong.dev.thread.SendMailReportEveryDay;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Log4j2
@SpringBootApplication
@EnableScheduling
public class ShopbookApplication {
	@Autowired
	private PromotionRepository promotionRepo;
	@Autowired private OrderRepository orderRepo;

	public static void main(String[] args) {
		Environment env = SpringApplication.run(ShopbookApplication.class, args).getEnvironment();
		String appName = env.getProperty("spring.application.name");
		if (appName != null) {
			appName = appName.toUpperCase();
		}

		String port = env.getProperty("server.port");
		log.info("-------------------------START " + appName
				+ " Application------------------------------");
		log.info("   Application         : " + appName);
		log.info("   Url wellcome        : http://localhost:" + port);
		log.info("-------------------------START SUCCESS " + appName
				+ " Application------------------------------");

		String ip = "0.255.0.0,0.0.0.0,1.1.1.1,2.2.2.2,255.2.2.2,3.3.3.3,";
		Pattern pattern = Pattern.compile("^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)((,)((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?))*$");
//		Pattern pattern = Pattern.compile("^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$");
		Boolean check = pattern.matcher(ip).matches();
		System.out.println(check);
	}

	@Scheduled(cron = "0 0 1 * * ?")
	public void findStopPromotion (){
		log.info("Bắt đầu tìm kiếm chương trình giảm giá hết hạn");
		List<Promotion> listPromotion = promotionRepo.findListPromotionStop();
		if(!listPromotion.isEmpty()){
			log.info("Đã tìm thấy chương trình giảm giá hết hạn!");
			Thread thread = new PromotionThread(listPromotion, promotionRepo);
			thread.start();
			return;
		}
		log.info("Không có chương trình hết hạn");
	}

	@Scheduled(cron = "0 3 1 * * ?")
	public void findStartPromotion (){
		log.info("Bắt đầu tìm kiếm chương trình giảm chưa đưuọc khởi động");
		List<Promotion> listPromotion = promotionRepo.findListPromotionReady();
		if(!listPromotion.isEmpty()){
			log.info("Đã tìm thấy chương trình giảm đến hạn nhưng chưa được start!");
			Thread thread = new PromotionStart(listPromotion, promotionRepo);
			thread.start();
			return;
		}
		log.info("Không có chương trình nào chwua được khởi động");
	}

	@Scheduled(cron = "0 0 21 * * ?")
	public void sendMailReport(){
		log.info("Bắt đầu gửi mail báo cáo hàng ngày");
		Thread thread = new SendMailReportEveryDay(orderRepo);
		thread.start();
	}

}
