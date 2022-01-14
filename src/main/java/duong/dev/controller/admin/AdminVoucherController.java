package duong.dev.controller.admin;

import duong.dev.common.Common;
import duong.dev.common.ResponeCustom;
import duong.dev.dto.ResponseDTO;
import duong.dev.dto.VoucherDTO;
import duong.dev.dto.request.ApplyVoucherRequestDTO;
import duong.dev.entity.Voucher;
import duong.dev.service.VoucherInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = Common.URL)
public class AdminVoucherController {
    @Autowired
    private VoucherInterface voucherSev;

    @GetMapping("/v2/admin/voucher/find-all/sort={sort}")
    private ResponseEntity<ResponseDTO<?>> showAll(@PathVariable("sort") String sort){
        return ResponseEntity.ok(ResponseDTO.builder()
                .messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
                .messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
                .data(voucherSev.findAll(sort))
                .build());
    }
    
    @GetMapping("/v2/admin/voucher/sort={sort}/search={keyword}")
	private ResponseEntity<ResponseDTO<?>> showAll(@PathVariable("sort") String sort, @PathVariable("keyword") String keyword){
		return ResponseEntity.ok(ResponseDTO.builder()
				.messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
				.messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
				.data(voucherSev.searchVoucher(sort, keyword))
				.build());
	}

    @PutMapping("/v2/admin/voucher/pause-voucher/{id}")
    private ResponseEntity<ResponseDTO<?>> updateStatusPauseVoucher(@PathVariable("id") Integer id){
        return ResponseEntity.ok(ResponseDTO.builder()
                .messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
                .messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
                .data(voucherSev.pauseVoucher(id))
                .build());
    }

    @PutMapping("/v2/admin/voucher/start-voucher/{id}")
    private ResponseEntity<ResponseDTO<?>> updateStatusStartVoucher(@PathVariable("id") Integer id){
        return ResponseEntity.ok(ResponseDTO.builder()
                .messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
                .messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
                .data(voucherSev.startVoucher(id))
                .build());
    }

    @PutMapping("/v2/admin/voucher/stop-voucher/{id}")
    private ResponseEntity<ResponseDTO<?>> updateStatusStopVoucher(@PathVariable("id") Integer id){
        return ResponseEntity.ok(ResponseDTO.builder()
                .messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
                .messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
                .data(voucherSev.stopVoucher(id))
                .build());
    }

    @PostMapping("/v2/admin/voucher")
    private ResponseEntity<ResponseDTO<?>> createVoucher(@RequestBody @Valid VoucherDTO voucher){
        return ResponseEntity.ok(ResponseDTO.builder()
                .messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
                .messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
                .data(voucherSev.create(voucher))
                .build());
    }
}
