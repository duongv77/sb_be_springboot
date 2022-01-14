package duong.dev.controller.website;

import duong.dev.common.Common;
import duong.dev.common.ResponeCustom;
import duong.dev.dto.ResponseDTO;
import duong.dev.service.AddressApiInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import java.io.IOException;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = Common.URL)
public class AddressControllerApi {
    @Autowired private AddressApiInterface addressApiSev;

    @GetMapping("/v1/user/address-api-districts")
    private ResponseEntity<ResponseDTO<?>> findListDistricts(){
        return ResponseEntity.ok(ResponseDTO.builder()
                .messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
                .messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
                .data(addressApiSev.findAllDistricts())
                .build());
    }

    @GetMapping("/v1/user/address-api-ward")
    private ResponseEntity<ResponseDTO<?>> findListWard(){
        return ResponseEntity.ok(ResponseDTO.builder()
                .messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
                .messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
                .data(addressApiSev.findAllWard())
                .build());
    }

    @GetMapping("/v1/user/address-api-province")
    private ResponseEntity<ResponseDTO<?>> findListProvince(){
        return ResponseEntity.ok(ResponseDTO.builder()
                .messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
                .messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
                .data(addressApiSev.findAllProvince())
                .build());
    }
    @GetMapping("/v1/user/address-api-ward/find-province/{id}")
    private ResponseEntity<ResponseDTO<?>> findAllWardByProvince(@PathVariable("id") Integer id){
        return ResponseEntity.ok(ResponseDTO.builder()
                .messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
                .messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
                .data(addressApiSev.findAllWardByProvince(id))
                .build());
    }
    @GetMapping("/v1/user/address-api-ward/find-districts/{id}")
    private ResponseEntity<ResponseDTO<?>> findAllWardByDistricts(@PathVariable("id") Integer id){
        return ResponseEntity.ok(ResponseDTO.builder()
                .messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
                .messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
                .data(addressApiSev.findAllWardByDistricts(id))
                .build());
    }
    @GetMapping("/v1/user/address-api-districts/find-districts/{id}")
    private ResponseEntity<ResponseDTO<?>> findAllDistrictsByProvince(@PathVariable("id") Integer id){
        return ResponseEntity.ok(ResponseDTO.builder()
                .messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
                .messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
                .data(addressApiSev.findAllDistrictsByProvince(id))
                .build());
    }
}
