package duong.dev.service;

import duong.dev.dto.ProductDTO;
import duong.dev.dto.VoucherDTO;
import duong.dev.dto.request.ApplyVoucherRequestDTO;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

public interface VoucherInterface {
    public List<VoucherDTO> findAll(String sort);

    public List<VoucherDTO> findAllStatusOnline();

    public VoucherDTO create(VoucherDTO voucherDTO);

    public VoucherDTO pauseVoucher(Integer voucherId);

    public VoucherDTO startVoucher(Integer voucherId);

    public VoucherDTO stopVoucher(Integer voucherId);

    public VoucherDTO applyVoucher(ApplyVoucherRequestDTO obj)  throws IOException, ServletException;
    
    public List<VoucherDTO> searchVoucher(String sort, String keyword);

	
}
