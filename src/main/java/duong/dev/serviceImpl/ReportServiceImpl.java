package duong.dev.serviceImpl;

import duong.dev.dto.response.DoanhThuNam;
import duong.dev.dto.response.RateMonth;
import duong.dev.dto.response.ReportQuantity;
import duong.dev.repository.*;
import duong.dev.service.ReportInteface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ReportServiceImpl implements ReportInteface {
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private ProductRepository productRepo;
    @Autowired
    private OrderDetailRepository odRepo;
    @Autowired
    private AccountRepository accountRepo;
    @Autowired
    private PromotionRepository promotionRepo;
    @Autowired
    private OrderRepository orderRepo;
    @Autowired
    private RateRepository rateRepo;

    @Override
    public List<DoanhThuNam> doanhThuNam(){
        List<DoanhThuNam> results = entityManager.createNamedQuery("query_doanhthunam", DoanhThuNam.class).getResultList();
        return results;
    }
    @Override
    public List<RateMonth> reportDoanhThuNam(Long year ){
    	String sql = new String();
    	
    	sql = "select  CONCAT('tháng: ' , month(o.create_date)) AS 'month',SUM(o.total) AS 'revenue', COUNT(o.id) AS 'quantity' from orders o  " +
    		" WHERE o.status=4 " +
    		" and YEAR(o.create_date)=:year " +
    		" GROUP BY month(o.create_date)";
    	
    	Query query = entityManager.createNativeQuery(sql);
    	query.setParameter("year", year); 
		
    	List<Object[]> results = query.getResultList();
    	
		List<RateMonth> lstRateMonth = new ArrayList<>();
		if(results!=null) {
			for(Object[] obj: results) {
				RateMonth rateMonth = new RateMonth();
				
				rateMonth.setValue(Integer.parseInt(obj[1].toString()));
				rateMonth.setName(obj[0].toString());
				lstRateMonth.add(rateMonth);
			}
		}
		
        return lstRateMonth;
    }

    @Override
    public List<RateMonth> rateStarMonth() {
        List<RateMonth> results = entityManager.createNamedQuery("query_star", RateMonth.class).getResultList();
        return results;
    }

    @Override
    public ReportQuantity reportQuantity() {
        ReportQuantity reportQuantity = new ReportQuantity();
        reportQuantity.setProductQuantity(productRepo.productQuantity());
        reportQuantity.setProductQuantitySold(odRepo.productQuantitySold());
        reportQuantity.setAccountQuantity(accountRepo.accountQuantity());
        reportQuantity.setPromotionQuantity(promotionRepo.promotionQuantity());
        reportQuantity.setOrderSold(orderRepo.orderQuantitySold());
        reportQuantity.setQuantityRate(rateRepo.rateQuantity());
        return reportQuantity;
    }

    @Override
    public List<RateMonth> quantityProductToCategory() {
        List<RateMonth> results = entityManager.createNamedQuery("query_thongKeSoLuongSanPhamCuaDanhMuc", RateMonth.class).getResultList();
        return results;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<RateMonth> top10ProductFavorite(Date fromDate, Date toDate) {
    	String sql = new String();
    	sql = "SELECT COUNT(f.id) AS 'value', p.name AS 'name' FROM products p JOIN favorites f on p.id = f.product_id " +
				" and f.create_date>=:fromDate " +
				" and f.create_date<=:toDate "+
    			"GROUP BY f.product_id  " +
				"ORDER BY COUNT(f.id) DESC " +
				"LIMIT 0,6";
    	
    	
    	Query query = entityManager.createNativeQuery(sql);
    	query.setParameter("fromDate", fromDate);
    	query.setParameter("toDate", toDate); 
    	
    	List<Object[]> results = query.getResultList();
    	
    	
		List<RateMonth> lstRateMonth = new ArrayList<>();
		if(results!=null) {
			for(Object[] obj: results) {
				RateMonth rateMonth = new RateMonth();
				
				rateMonth.setValue(Integer.parseInt(obj[0].toString()));
				rateMonth.setName(obj[1].toString());
				lstRateMonth.add(rateMonth);
			}
		}
		
        return lstRateMonth;
        
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<RateMonth> top10ProductRateGood(Date fromDate, Date toDate,Long productId, String productName) {
    	// truyền thêm tháng đang chọn này 
    	// lấy về ngày đầu tháng, lấy về ngày cuối tháng
    	String sql = new String();
    
    		sql= "SELECT COUNT(r.id) AS 'value', p.name AS 'name' FROM products p JOIN rates r on p.id = r.product_id " +
    				"WHERE r.vote >= 4 " +
    				" and r.create_date>=:fromDate " +
    				" and r.create_date<=:toDate ";
    		if(productId!=null) {
    				sql+= " and p.id =:productId ";
    		}
    		if(productName!=null) {
    				sql+= " and p.name =:productName ";
    		}
    				sql += " GROUP BY r.product_id  " +
    				" ORDER BY COUNT(r.id) DESC " +
    				" LIMIT 0,6";
    		
    	
    	
    	Query query = entityManager.createNativeQuery(sql);
    		query.setParameter("fromDate", fromDate);
        	query.setParameter("toDate", toDate); 

        	if(productId!=null) {
        	query.setParameter("productId", productId);
        	}
        	if(productName!=null) {
        		query.setParameter("productName", productName);
        	}
        	
        	List<Object[]> results = query.getResultList();
        	
        	
    		List<RateMonth> lstRateMonth = new ArrayList<>();
    		if(results!=null) {
    			for(Object[] obj: results) {
    				RateMonth rateMonth = new RateMonth();
    				
    				rateMonth.setValue(Integer.parseInt(obj[0].toString()));
    				rateMonth.setName(obj[1].toString());
    				lstRateMonth.add(rateMonth);
    			}
    		}
    		
            return lstRateMonth;
    }
    
/*    public List<RateMonth> top10ProductRateGood(Long month, Long year){
    	// truyền thêm tháng đang chọn này 
    	// lấy về ngày đầu tháng, lấy về ngày cuối tháng
    	String sql = new String();
    	sql= "SELECT COUNT(r.id) AS 'value', CONCAT('id: ', p.id) AS 'name' FROM products p JOIN rates r on p.id = r.product_id " +
				"WHERE r.vote >= 4 " +
				" and Month(r.create_date)=:month " +
				" and YEAR(r.create_date)=:year "+
				"GROUP BY r.product_id  " +
				"ORDER BY COUNT(r.id) DESC " +
				"LIMIT 0,9";
    	
    	Query query = entityManager.createNativeQuery(sql);
    	query.setParameter("month", month);
    	query.setParameter("year", year); 
		List<RateMonth> results = query.getResultList();
        return results;
    }*/

    @Override
    public List<RateMonth> top10ProductSelling(Date fromDate, Date toDate) {
    	String sql = new String();
// 		sql = "SELECT SUM(o.quantity) AS 'value' ,CONCAT('id: ',  o.product_id) AS 'name' FROM orderdetails o JOIN orders o2 on o.order_id = o2.id " +
//				"WHERE o2.status = 4 " +
//				" and o2.create_date>=:fromDate "+
//				" and o2.create_date<=:toDate " +
//				"GROUP BY o.product_id " +
//				"ORDER BY SUM(o.quantity) DESC " +
//				"LIMIT 0,9";
		sql = "SELECT SUM(o.quantity) AS 'value' ,p.name AS 'name' "+
				"FROM orderdetails o JOIN orders o2 on o.order_id = o2.id " +
				" join products p on o.product_id=p.id " +
				" WHERE o2.status = 4 " +
				" and o2.create_date>=:fromDate "+
				" and o2.create_date<=:toDate " +
				" GROUP BY o.product_id " +
				" ORDER BY SUM(o.quantity) DESC " +
				" LIMIT 0,6 ";
    	
    	Query query = entityManager.createNativeQuery(sql);
    	query.setParameter("fromDate", fromDate);
    	query.setParameter("toDate", toDate); 
    	List<Object[]> results = query.getResultList();
    	
    	
		List<RateMonth> lstRateMonth = new ArrayList<>();
		if(results!=null) {
			for(Object[] obj: results) {
				RateMonth rateMonth = new RateMonth();
				
				rateMonth.setValue(Integer.parseInt(obj[0].toString()));
				rateMonth.setName(obj[1].toString());
				lstRateMonth.add(rateMonth);
			}
		}
		
        return lstRateMonth;
    }

    @Override
    public List<RateMonth> top10ProductRateBad(Date fromDate, Date toDate) {
    	String sql = new String();
    	sql = "SELECT COUNT(r.id) AS 'value', p.name AS 'name' FROM rates r " +
    		  " join products p on r.product_id=p.id " + 
    		  " WHERE r.vote<3 " +
    		  " and r.create_date>=:fromDate " +
    		  " and r.create_date<=:toDate " +
    		  "GROUP BY r.product_id ORDER BY COUNT(r.id) DESC LIMIT 0,6";
    	Query query = entityManager.createNativeQuery(sql);
    	query.setParameter("fromDate", fromDate);
    	query.setParameter("toDate", toDate); 
    	List<Object[]> results = query.getResultList();
    	
    	
		List<RateMonth> lstRateMonth = new ArrayList<>();
		if(results!=null) {
			for(Object[] obj: results) {
				RateMonth rateMonth = new RateMonth();
				
				rateMonth.setValue(Integer.parseInt(obj[0].toString()));
				rateMonth.setName(obj[1].toString());
				lstRateMonth.add(rateMonth);
			}
		}
		
        return lstRateMonth;
    }

    @Override
    public List<RateMonth> reportRevenueWeek() {
        List<RateMonth> results = entityManager.createNamedQuery("query_doanhthutuan", RateMonth.class).getResultList();
        return results;
    }
}
