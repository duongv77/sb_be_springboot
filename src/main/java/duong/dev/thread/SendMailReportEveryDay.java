package duong.dev.thread;

import duong.dev.common.Status;
import duong.dev.entity.Order;
import duong.dev.entity.Promotion;
import duong.dev.repository.OrderRepository;
import duong.dev.repository.PromotionRepository;
import duong.dev.util.SendMail;
import lombok.extern.log4j.Log4j2;

import java.util.Date;
import java.util.List;

@Log4j2
public class SendMailReportEveryDay extends Thread {

     /*
        Gửi mail báo cáo hằng ngày
     */

    private Thread t;
    private OrderRepository orderRepo;

    public SendMailReportEveryDay(OrderRepository orderRepo2) {
        orderRepo = orderRepo2;
        log.info("Bắt đầu gửi mail báo cáo ");

    }

    public void run(){
        StringBuffer conten = new StringBuffer();
        Long total = orderRepo.findTotalDay();
        conten.append("Doanh thu ngày hôm nay: " +total + "vnđ --------- \r\n");
        List<Order> listOrder = orderRepo.findListOrderDay();
        listOrder.forEach(elm->{
            conten.append(" \r\n --------");
            conten.append(elm.toString());
        });
        String email = "duongv77@gmail.com";
        SendMail.SendMailReportEveryDay(conten.toString(), email);
    }

    public void start() {
        if (t == null) {
            t = new Thread (this);
            t.start();
        }
    }
}
