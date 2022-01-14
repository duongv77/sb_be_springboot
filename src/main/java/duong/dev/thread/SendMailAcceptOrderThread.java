package duong.dev.thread;

import duong.dev.util.SendMail;
import lombok.extern.log4j.Log4j2;

import java.util.Date;

@Log4j2
public class SendMailAcceptOrderThread extends Thread {
    /*
        Gửi mail thông báo thành công khi duyệt đơn hàng
     */

    private Thread t;
    private String mail;
    private Integer orderId;


    public SendMailAcceptOrderThread(String emailStr, Integer orderIdStr) {
        mail = emailStr;
        orderId = orderIdStr;
    }

    public void run(){
        log.info("Bắt Gửi mai thông báo duyệt đơn: ");
        log.info(new Date());
        String content = "Đơn hàng #"+orderId+" của bạn đã được duyệt.";
        SendMail.SenMailAcceptOrder(content, mail);
    }

    public void start() {
        if (t == null) {
            t = new Thread (this);
            t.start();
        }
    }
}
