package duong.dev.thread;

import duong.dev.common.Status;
import duong.dev.entity.Promotion;
import duong.dev.repository.PromotionRepository;
import lombok.extern.log4j.Log4j2;

import java.util.Date;
import java.util.List;

@Log4j2
public class PromotionStart extends Thread {
    /*
        start chương trình
     */

    private Thread t;
    private PromotionRepository promotionRepo;
    List<Promotion> listPromotion;


    public PromotionStart(List<Promotion> listPromotionE,PromotionRepository promotionRepoF) {
        listPromotion = listPromotionE;
        promotionRepo = promotionRepoF;
    }

    public void run(){
        log.info("Bắt đầu start chương trình: ");
        log.info("Bắt đầu khởi tạo thread start chương trình!");
        log.info(new Date());
        listPromotion.parallelStream().forEach(elm->{
            elm.setActivated(Status.STATUS_PROMOTION_DANG_HOAT_DONG);
            promotionRepo.save(elm);
            log.info("Đã chạy chương trình " + elm.getName());
        });
    }

    public void start() {
        if (t == null) {
            t = new Thread (this);
            t.start();
        }
    }
}
