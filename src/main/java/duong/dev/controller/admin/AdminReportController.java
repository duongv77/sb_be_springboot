package duong.dev.controller.admin;

import duong.dev.common.Common;
import duong.dev.common.ResponeCustom;
import duong.dev.dto.ResponseDTO;
import duong.dev.service.ReportInteface;
import duong.dev.util.DateUtil;

import java.text.ParseException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = Common.URL)
public class AdminReportController {
    @Autowired
    private ReportInteface reportSev;
    @GetMapping("/v2/supper_admin/doanh-thu-nam")
    private ResponseEntity<ResponseDTO<?>> doanhThuNam(){
        return ResponseEntity.ok(ResponseDTO.builder()
                .messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
                .messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
                .data(reportSev.doanhThuNam())
                .build());
    }
    @GetMapping("/v2/supper_admin/report/rate-star-month")
    private ResponseEntity<ResponseDTO<?>> reateStarMonth(){
        return ResponseEntity.ok(ResponseDTO.builder()
                .messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
                .messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
                .data(reportSev.rateStarMonth())
                .build());
    }

    @GetMapping("/v2/supper_admin/report/report-quantity-all")
    private ResponseEntity<ResponseDTO<?>> reportQuantity(){
        return ResponseEntity.ok(ResponseDTO.builder()
                .messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
                .messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
                .data(reportSev.reportQuantity())
                .build());
    }
    @GetMapping("/v2/supper_admin/report/report-quantity/product-category")
    private ResponseEntity<ResponseDTO<?>> quantityProductToCategory(){
        return ResponseEntity.ok(ResponseDTO.builder()
                .messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
                .messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
                .data(reportSev.quantityProductToCategory())
                .build());
    }
 
    
    
    
    @GetMapping("/v2/supper_admin/report/report-favorite/top10-product")
    private ResponseEntity<ResponseDTO<?>> top10ProductFavorite(@RequestParam(required = false) String monthYear) throws ParseException{
    	Date fromDate = new Date();
    	Date toDate = new Date();
    	if(monthYear==null) {
    		fromDate = DateUtil.getFirstDayOfMonth(new Date());
    		toDate = DateUtil.getLastDayOfMonth(new Date());
    	}else {
    		// parse monthYear to Date in month and get firstDayofmonth, get last day of month 
        	 fromDate = DateUtil.convertStringToTime(monthYear, "yyyy-MM");
        	 toDate =DateUtil.getLastDayOfMonth(fromDate);
    	}
        return ResponseEntity.ok(ResponseDTO.builder()
                .messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
                .messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
                .data(reportSev.top10ProductFavorite(fromDate, toDate))
                .build());
    }
    
    
    
 /*   @GetMapping("/v2/supper_admin/report/report-rate/top10-product")
    private ResponseEntity<ResponseDTO<?>> top10ProductRateGood(@RequestParam(required = false) String monthYear) throws ParseException{
    	//Date monthYear
    	//Kiểm tra xem giá trị này có null không?
    	// nếu null thì lấy ngày hiện tại
    	Date fromDate = new Date();
    	Date toDate = new Date();
    	
    	if(monthYear==null) {
    		fromDate = DateUtil.getFirstDayOfMonth(new Date());
    		toDate = DateUtil.getLastDayOfMonth(new Date());
    	}else {
    		// parse monthYear to Date in month and get firstDayofmonth, get last day of month 
        	 fromDate = DateUtil.convertStringToTime(monthYear, "yyyy-MM");
        	 toDate =DateUtil.getLastDayOfMonth(fromDate);
    	}
    	
        return ResponseEntity.ok(ResponseDTO.builder()
                .messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
                .messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
                .data(reportSev.top10ProductRateGood(fromDate, toDate))
                .build());
    }*/
    
    @GetMapping("/v2/supper_admin/report/report-rate/top10-product")
    private ResponseEntity<ResponseDTO<?>> top10ProductRateGood(@RequestParam(required = false) String monthYear,@RequestParam(required = false) Long productId,@RequestParam(required = false) String productName ) throws ParseException{
    	//Date monthYear
    	//Kiểm tra xem giá trị này có null không?
    	// nếu null thì lấy ngày hiện tại
    	Date fromDate = new Date();
    	Date toDate = new Date();
    	
    	if(monthYear==null) {
    		fromDate = DateUtil.getFirstDayOfMonth(new Date());
    		toDate = DateUtil.getLastDayOfMonth(new Date());
    	}else {
    		// parse monthYear to Date in month and get firstDayofmonth, get last day of month 
        	 fromDate = DateUtil.convertStringToTime(monthYear, "yyyy-MM");
        	 toDate =DateUtil.getLastDayOfMonth(fromDate);
    	}
    	
        return ResponseEntity.ok(ResponseDTO.builder()
                .messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
                .messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
                .data(reportSev.top10ProductRateGood(fromDate, toDate,productId,productName))
                .build());
    }
    
    
    @GetMapping("/v2/supper_admin/report/report-selling/top10-product-a-lot")
    private ResponseEntity<ResponseDTO<?>> top10ProductSelling(@RequestParam(required = false) String monthYear) throws ParseException{
    	Date fromDate = new Date();
    	Date toDate = new Date();
    	
    	if(monthYear==null) {
    		fromDate = DateUtil.getFirstDayOfMonth(new Date());
    		toDate = DateUtil.getLastDayOfMonth(new Date());
    	}else {
    		// parse monthYear to Date in month and get firstDayofmonth, get last day of month 
        	 fromDate = DateUtil.convertStringToTime(monthYear, "yyyy-MM");
        	 toDate =DateUtil.getLastDayOfMonth(fromDate);
    	}
        return ResponseEntity.ok(ResponseDTO.builder()
                .messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
                .messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
                .data(reportSev.top10ProductSelling(fromDate, toDate))
                .build());
    }
    @GetMapping("/v2/supper_admin/report/report-rate/top10-product-bad")
    private ResponseEntity<ResponseDTO<?>> top10ProductRateBad(@RequestParam(required = false) String monthYear) throws ParseException{
    	Date fromDate = new Date();
    	Date toDate = new Date();
    	
    	if(monthYear==null) {
    		fromDate = DateUtil.getFirstDayOfMonth(new Date());
    		toDate = DateUtil.getLastDayOfMonth(new Date());
    	}else {
    		// parse monthYear to Date in month and get firstDayofmonth, get last day of month 
        	 fromDate = DateUtil.convertStringToTime(monthYear, "yyyy-MM");
        	 toDate =DateUtil.getLastDayOfMonth(fromDate);
    	}
        return ResponseEntity.ok(ResponseDTO.builder()
                .messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
                .messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
                .data(reportSev.top10ProductRateBad(fromDate, toDate))
                .build());
    }
    @GetMapping("/v2/supper_admin/report/report-revenue-week")
    private ResponseEntity<ResponseDTO<?>> reportRevenueWeek(){
        return ResponseEntity.ok(ResponseDTO.builder()
                .messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
                .messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
                .data(reportSev.reportRevenueWeek())
                .build());
    }
    
    @GetMapping("/v2/supper_admin/report/doanh-thu-nam")
    private ResponseEntity<ResponseDTO<?>> reportDoanhThuNam(@RequestParam(required = false) String monthYear) throws ParseException{
    
    	Long year=0L;
    	if(monthYear==null) {
    		year = DateUtil.getYearOfDate(new Date());
    		
    	}else {
    		// parse monthYear to Date in month and get firstDayofmonth, get last day of month 
        	 year = DateUtil.getYearOfDate(DateUtil.convertStringToTime(monthYear, "yyyy-MM"));
    	}
    	
    	
        return ResponseEntity.ok(ResponseDTO.builder()
                .messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
                .messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
                .data(reportSev.reportDoanhThuNam(year))
                .build());
    }
}
