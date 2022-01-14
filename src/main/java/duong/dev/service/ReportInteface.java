package duong.dev.service;

import duong.dev.dto.response.DoanhThuNam;
import duong.dev.dto.response.RateMonth;
import duong.dev.dto.response.ReportQuantity;

import java.util.Date;
import java.util.List;

public interface ReportInteface {
    public List<DoanhThuNam> doanhThuNam();
    
    public List<RateMonth> reportDoanhThuNam(Long year );
    
    public List<RateMonth> rateStarMonth();
    
    public ReportQuantity reportQuantity();
    
    public List<RateMonth> quantityProductToCategory();
    
    public List<RateMonth> top10ProductFavorite(Date fromDate, Date toDate);
    
    public List<RateMonth> top10ProductRateGood(Date fromDate, Date toDate , Long productId, String productName);
    
   // public List<RateMonth> top10ProductRateGood(Long month, Long Year);
    
    public List<RateMonth> top10ProductSelling(Date fromDate, Date toDate);
    
    public List<RateMonth> top10ProductRateBad(Date fromDate, Date toDate);
    
    public List<RateMonth> reportRevenueWeek();
}
