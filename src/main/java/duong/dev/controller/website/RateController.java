package duong.dev.controller.website;

import duong.dev.common.Common;
import duong.dev.common.ResponeCustom;
import duong.dev.dto.ResponseDTO;
import duong.dev.dto.request.CreateRateRequestDTO;
import duong.dev.service.RateInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = Common.URL)
public class RateController {
    @Autowired
    private RateInterface rateSev;

    @GetMapping("/v1/rate-product/page={page}/{id}")
    private ResponseEntity<ResponseDTO<?>> findRate(@PathVariable("id") Integer id, @PathVariable("page") Integer page){
        return ResponseEntity.ok(ResponseDTO.builder()
                .messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
                .messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
                .data(rateSev.findByProduct(id, page))
                .build());
    }

    @PostMapping("/v2/user/rate")
    private ResponseEntity<ResponseDTO<?>> createRate(@RequestBody @Valid CreateRateRequestDTO createRate )  throws IOException, ServletException {
        return ResponseEntity.ok(ResponseDTO.builder()
                .messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
                .messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
                .data(rateSev.create(createRate))
                .build());
    }
}
