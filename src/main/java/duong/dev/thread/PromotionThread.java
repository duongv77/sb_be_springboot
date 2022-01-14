package duong.dev.thread;

import duong.dev.common.Status;
import duong.dev.entity.Promotion;
import duong.dev.repository.PromotionRepository;
import lombok.extern.log4j.Log4j2;

import java.util.Date;
import java.util.List;

@Log4j2
public class PromotionThread extends Thread {
    /*
        stop chương trình
     */

    private Thread t;
    private PromotionRepository promotionRepo;
    List<Promotion> listPromotion;


    public PromotionThread(List<Promotion> listPromotionE,PromotionRepository promotionRepoF) {
        listPromotion = listPromotionE;
        promotionRepo = promotionRepoF;
    }

    public void run(){
        log.info("Bắt đầu dừng: ");
        log.info("Bắt đầu khởi tạo thread dừng chương trình!");
        log.info(new Date());
        listPromotion.parallelStream().forEach(elm->{
            elm.setActivated(Status.STATUS_PROMOTION_DA_KET_THUC);
            promotionRepo.save(elm);
            log.info("Đã kết thúc chương trình " + elm.getName());
        });
    }

    public void start() {
        if (t == null) {
            t = new Thread (this);
            t.start();
        }
    }
}
