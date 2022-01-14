package duong.dev.controller.website;

import duong.dev.common.Common;
import duong.dev.common.ResponeCustom;
import duong.dev.dto.ResponseDTO;
import duong.dev.dto.request.ApplyVoucherRequestDTO;
import duong.dev.service.VoucherInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import java.io.IOException;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = Common.URL)
public class VoucherController {
    @Autowired
    private VoucherInterface voucherSev;

    @GetMapping("/v1/voucher/find-all")
    private ResponseEntity<ResponseDTO<?>> showAllStatusOnline(){
        return ResponseEntity.ok(ResponseDTO.builder()
                .messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
                .messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
                .data(voucherSev.findAllStatusOnline())
                .build());
    }

    @PostMapping("/v2/user/voucher/apply-voucher")
    private ResponseEntity<ResponseDTO<?>> applyVoucher(@RequestBody ApplyVoucherRequestDTO obj)  throws IOException, ServletException {
        return ResponseEntity.ok(ResponseDTO.builder()
                .messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
                .messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
                .data(voucherSev.applyVoucher(obj))
                .build());
    }
}
