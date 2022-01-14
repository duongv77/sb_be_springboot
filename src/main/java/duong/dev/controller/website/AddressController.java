package duong.dev.controller.website;

import duong.dev.common.Common;
import duong.dev.common.ResponeCustom;
import duong.dev.dto.AddressDTO;
import duong.dev.dto.ResponseDTO;
import duong.dev.service.AddressInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = Common.URL)
public class AddressController {
    @Autowired
    private AddressInterface addressSev;

    @PostMapping("/v1/user/address-create")
    private ResponseEntity<ResponseDTO<?>> findListDistricts(@RequestBody @Valid AddressDTO addressDTO) throws ServletException, IOException {
        return ResponseEntity.ok(ResponseDTO.builder()
                .messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
                .messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
                .data(addressSev.create(addressDTO))
                .build());
    }

    @DeleteMapping("/v1/user/address/{id}")
    private ResponseEntity<ResponseDTO<?>> delete(@PathVariable("id") Integer id) throws ServletException, IOException {
        return ResponseEntity.ok(ResponseDTO.builder()
                .messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
                .messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
                .data(addressSev.delete(id))
                .build());
    }
}
