package duong.dev.controller.admin;

import duong.dev.common.Common;
import duong.dev.common.ResponeCustom;
import duong.dev.dto.ResponseDTO;
import duong.dev.dto.TitleDTO;
import duong.dev.dto.request.TitleUpdateRequestDTO;
import duong.dev.service.TitleInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = Common.URL)
public class AdminTitleController {
    @Autowired
    private TitleInterface titleSev;

    @GetMapping("/v2/admin/title")
    private ResponseEntity<ResponseDTO<?>> findAll() throws IOException {
        return ResponseEntity.ok(ResponseDTO.builder()
                .messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
                .messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
                .data(titleSev.findAll())
                .build());
    }

    @PostMapping("/v2/admin/title")
    private ResponseEntity<ResponseDTO<?>> createTitle(@RequestBody @Valid TitleDTO titleD) throws IOException {
        return ResponseEntity.ok(ResponseDTO.builder()
                .messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
                .messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
                .data(titleSev.createTitle(titleD))
                .build());
    }

    @DeleteMapping("/v2/admin/title/{id}")
    private ResponseEntity<ResponseDTO<?>> deleteTitle(@PathVariable("id") Integer titleId){
        return ResponseEntity.ok(ResponseDTO.builder()
                .messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
                .messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
                .data(titleSev.deleteTitle(titleId))
                .build());
    }

    @PutMapping("/v2/admin/title")
    private ResponseEntity<ResponseDTO<?>> updateTitle(@RequestBody @Valid TitleDTO obj){
        return ResponseEntity.ok(ResponseDTO.builder()
                .messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
                .messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
                .data(titleSev.updateTitle(obj))
                .build());
    }

    @PutMapping("/v2/admin/title-status")
    private ResponseEntity<ResponseDTO<?>> updateStatus(@RequestBody @Valid TitleUpdateRequestDTO obj){
        return ResponseEntity.ok(ResponseDTO.builder()
                .messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
                .messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
                .data(titleSev.updateStatus(obj))
                .build());
    }
}
