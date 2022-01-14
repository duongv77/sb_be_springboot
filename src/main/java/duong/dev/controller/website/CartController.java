package duong.dev.controller.website;

import duong.dev.common.Common;
import duong.dev.common.ResponeCustom;
import duong.dev.dto.ResponseDTO;
import duong.dev.service.CartInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = Common.URL)
public class CartController {
    @Autowired
    CartInterface cartSev;

    @GetMapping("/v1/cart/quantity-product/{id}")
    private ResponseEntity<ResponseDTO<?>> getQuantityProductInCart(@PathVariable("id") Integer id)  {
        return ResponseEntity.ok(ResponseDTO.builder().messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
                .messageName(ResponeCustom.MESSAGE_NAME_SUCCESS).data(cartSev.getQuantityProductInCart(id)).build());
    }
}
