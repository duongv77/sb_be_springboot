package duong.dev.controller.admin;

import duong.dev.common.Common;
import duong.dev.common.ResponeCustom;
import duong.dev.dto.ResponseDTO;
import duong.dev.service.TurnVoteInteface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import java.io.IOException;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = Common.URL)
public class AdminTurnVoteController {
    @Autowired
    private TurnVoteInteface turnVoteSev;

    @GetMapping("/v2/user/product_id/{id}")
    private ResponseEntity<ResponseDTO<?>> readAll(@PathVariable("id") Integer id)  throws IOException, ServletException {
        return ResponseEntity.ok(ResponseDTO.builder()
                .messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
                .messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
                .data(turnVoteSev.findByAccountAndProduct(id))
                .build());
    }
}
