package duong.dev.controller.admin;

import duong.dev.common.Common;
import duong.dev.common.ResponeCustom;
import duong.dev.dto.ResponseDTO;
import duong.dev.serviceImpl.RateServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = Common.URL)
public class AdminVoteController {
    @Autowired
    private RateServiceImpl rateSev;

    @GetMapping("/v2/admin/vote/count-good/count-all")
    private ResponseEntity<ResponseDTO<?>> findCountVoteGoodAndAll(){
        return ResponseEntity.ok(ResponseDTO.builder()
                .messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
                .messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
                .data(rateSev.findCountVoteGoodAndAll())
                .build());
    }

}
