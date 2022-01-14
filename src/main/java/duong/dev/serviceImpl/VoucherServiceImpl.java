package duong.dev.serviceImpl;

import duong.dev.common.Common;
import duong.dev.common.ResponeCustom;
import duong.dev.common.Status;
import duong.dev.dto.ProductDTO;
import duong.dev.dto.VoucherDTO;
import duong.dev.dto.request.ApplyVoucherRequestDTO;
import duong.dev.entity.Product;
import duong.dev.entity.Voucher;
import duong.dev.exception.AppException;
import duong.dev.mapper.VoucherMapper;
import duong.dev.repository.VoucherRepository;
import duong.dev.service.OrderInterface;
import duong.dev.service.VoucherInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VoucherServiceImpl implements VoucherInterface {
    @Autowired private VoucherRepository voucherRepo;
    @Autowired private VoucherMapper voucherMapper;
    @Autowired private OrderInterface orderSev;

    public List<VoucherDTO> convertListDTO(List<Voucher> vouchers){
        return vouchers.stream().map(elm -> voucherMapper.convertToDTO(elm)).collect(Collectors.toList());
    }

    @Override
    public List<VoucherDTO> findAll(String sort){
		if(sort.equals("name") || sort.equals("title")){
			Sort sort2 = Sort.by(sort).ascending();
			List<Voucher> listVoucherE = voucherRepo.findAll(sort2);
			return convertListDTO(listVoucherE);
		}
		Sort sort2 = Sort.by(sort).descending();
		List<Voucher> listVoucherE = voucherRepo.findAll(sort2);
		return convertListDTO(listVoucherE);
	}
    
	@Override
	public List<VoucherDTO> searchVoucher(String sort, String keyword) {
		if(sort.equals("name") || sort.equals("title")){
			Sort sort2 = Sort.by(sort).ascending();
			List<Voucher> listVoucherE = voucherRepo.searchVoucher(keyword, sort2);
			return convertListDTO(listVoucherE);
		}
		Sort sort2 = Sort.by(sort).descending();
		List<Voucher> listVoucherE = new ArrayList<>();
		System.out.println(keyword.trim());
		if(keyword.trim().equals("")){
			listVoucherE = voucherRepo.findAll(sort2);
		}else{
			System.out.println("vào false");
			listVoucherE = voucherRepo.searchVoucher(keyword, sort2);

		}
		return convertListDTO(listVoucherE);
	}

    @Override
    public List<VoucherDTO> findAllStatusOnline() {
        return convertListDTO(voucherRepo.findAllVouchersByStatus(Status.STATUS_PROMOTION_DANG_HOAT_DONG));
    }

    @Override
    public VoucherDTO create(VoucherDTO voucherDTO) {
        Voucher voucher = voucherMapper.convertToEntity(voucherDTO);
        Optional<Voucher> voucherOptional = voucherRepo.findByVoucherCode(voucherDTO.getVoucherCode());
        if(voucherOptional.isPresent())
            throw new AppException(ResponeCustom.MESSAGE_CODE_KHONG_TON_TAI, "Voucher code đã tồn tại!");

        long betweenTime = Common.beetweenTime(voucherDTO.getStartDate(), voucherDTO.getEndDate());
        if(betweenTime==0)
            throw new AppException(ResponeCustom.MESSAGE_CODE_KHONG_THANH_CONG, "Ngày kết thúc phải sau ngày bắt đầu!");

        if(voucher.getSalePercent()!=null && voucher.getSalePrice()!=null){
            throw new AppException(ResponeCustom.MESSAGE_CODE_KHONG_THANH_CONG, "Chỉ được chọn 1 trong 2 cách giảm giá!");
        }else if(voucher.getSalePercent()==null && voucher.getSalePrice()==null){
            throw new AppException(ResponeCustom.MESSAGE_CODE_KHONG_THANH_CONG, "Vui lòng chọn 1 trong 2 cách giảm giá!");
        }
        voucher.setStatus(Status.STATUS_PROMOTION_DANG_CHO);
        voucherRepo.save(voucher);
        return voucherMapper.convertToDTO(voucher);
    }

    @Override
    public VoucherDTO pauseVoucher(Integer voucherId) {
        Optional<Voucher> voucherOptional = voucherRepo.findById(voucherId);
        if(!voucherOptional.isPresent())
            throw new AppException(ResponeCustom.MESSAGE_CODE_KHONG_TON_TAI, "Voucher Không tồn tại!");
        Voucher voucher = voucherOptional.get();
        voucher.setStatus(Status.STATUS_PROMOTION_TAM_DUNG);
        voucherRepo.save(voucher);
        return voucherMapper.convertToDTO(voucher);
    }

    @Override
    public VoucherDTO startVoucher(Integer voucherId) {
        Optional<Voucher> voucherOptional = voucherRepo.findById(voucherId);
        if(!voucherOptional.isPresent())
            throw new AppException(ResponeCustom.MESSAGE_CODE_KHONG_TON_TAI, "Voucher Không tồn tại!");
        Voucher voucher = voucherOptional.get();
        voucher.setStatus(Status.STATUS_PROMOTION_DANG_HOAT_DONG);
        voucherRepo.save(voucher);
        return voucherMapper.convertToDTO(voucher);
    }

    @Override
    public VoucherDTO stopVoucher(Integer voucherId) {
        Optional<Voucher> voucherOptional = voucherRepo.findById(voucherId);
        if(!voucherOptional.isPresent())
            throw new AppException(ResponeCustom.MESSAGE_CODE_KHONG_TON_TAI, "Voucher Không tồn tại!");
        Voucher voucher = voucherOptional.get();
        voucher.setStatus(Status.STATUS_PROMOTION_DA_KET_THUC);
        voucherRepo.save(voucher);
        return voucherMapper.convertToDTO(voucher);
    }

    @Override
    public VoucherDTO applyVoucher(ApplyVoucherRequestDTO obj)  throws IOException, ServletException {
        Optional<Voucher> voucherOptional = voucherRepo.findByVoucherCode(obj.getVoucherCode());
        if(!voucherOptional.isPresent())
            throw new AppException(ResponeCustom.MESSAGE_CODE_KHONG_TON_TAI, "Voucher không tồn tại!");
        Voucher voucher = voucherOptional.get();
        if(voucher.getStatus()!=Status.STATUS_PROMOTION_DANG_HOAT_DONG)
            throw new AppException(ResponeCustom.MESSAGE_CODE_KHONG_TON_TAI, "Voucher không khả dụng!");
        if(voucher.getTotalPrice()!=null){
            if(voucher.getTotalPrice()> obj.getTotal())
                throw new AppException(ResponeCustom.MESSAGE_CODE_KHONG_THANH_CONG, "Đơn hàng chưa đủ tổng tiền!");
        }
        if(voucher.getProductQuantity()!=null){
            if(voucher.getProductQuantity()> obj.getQuantity())
                throw new AppException(ResponeCustom.MESSAGE_CODE_KHONG_THANH_CONG, "Đơn hàng không đủ sản phẩm!");
        }
        if(voucher.getOrderQuantity()!=null){
            if(voucher.getOrderQuantity()>orderSev.findQuantityOrderByAccount())
                throw new AppException(ResponeCustom.MESSAGE_CODE_KHONG_THANH_CONG, "Bạn chưa đủ số lần mua hàng!");
        }
        return voucherMapper.convertToDTO(voucher);
    }

    public Voucher voucherCheck(String voucher){
        if(voucher.trim()=="" || voucher==null){
            return new Voucher();
        }
        Optional<Voucher> voucherOptional = voucherRepo.findByVoucherCode(voucher);
        if(!voucherOptional.isPresent())
            throw new AppException(ResponeCustom.MESSAGE_CODE_KHONG_TON_TAI, "Mã giảm giá không tồn tại!");
        Voucher voucherE = voucherOptional.get();
        if(voucherE.getStatus()!=Status.STATUS_PROMOTION_DANG_HOAT_DONG)
            throw new AppException(ResponeCustom.MESSAGE_CODE_KHONG_TON_TAI, "Mã giảm giá không khả dụng!");
        return voucherE;
    }
}
