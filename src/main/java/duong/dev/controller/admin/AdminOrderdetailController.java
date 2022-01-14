package duong.dev.controller.admin;

import duong.dev.common.Common;
import duong.dev.common.ResponeCustom;
import duong.dev.dto.OrderDetailDTO;
import duong.dev.dto.ResponseDTO;
import duong.dev.dto.request.CreateOrderdetailDTO;
import duong.dev.dto.request.UpdateQuantityOrderDetailRQDTO;
import duong.dev.service.OrderDetailInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = Common.URL)
public class AdminOrderdetailController {
    @Autowired
    private OrderDetailInterface orderDetailSev;

    @DeleteMapping("/v2/admin/order-detail/{id}")
    private ResponseEntity<ResponseDTO<?>> deleteOrderDetail(@PathVariable("id") Integer id){
        return ResponseEntity.ok(ResponseDTO.builder()
                .messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
                .messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
                .data(orderDetailSev.delete(id))
                .build());
    }

    @PutMapping("/v2/admin/order-detail")
    private ResponseEntity<ResponseDTO<?>> updateOrderDetail(@RequestBody OrderDetailDTO orderDetailDTO){
        return ResponseEntity.ok(ResponseDTO.builder()
                .messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
                .messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
                .data(orderDetailSev.update(orderDetailDTO))
                .build());
    }

    @PostMapping("/v2/admin/order-detail")
    private ResponseEntity<ResponseDTO<?>> createOrderDetail(@RequestBody CreateOrderdetailDTO createorderdetailDTO){
        return ResponseEntity.ok(ResponseDTO.builder()
                .messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
                .messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
                .data(orderDetailSev.create(createorderdetailDTO))
                .build());
    }

    //thêm sản phẩm của chức năng tạo hóa đơn
    @PostMapping("/v2/admin/order-detail2")
    private ResponseEntity<ResponseDTO<?>> createOrderDetail2(@RequestBody @Valid CreateOrderdetailDTO createorderdetailDTO){
        return ResponseEntity.ok(ResponseDTO.builder()
                .messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
                .messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
                .data(orderDetailSev.create2(createorderdetailDTO))
                .build());
    }

    @PostMapping("/v2/admin/order-detail/in-proget")
    private ResponseEntity<ResponseDTO<?>> updateOrderDetailInProget(@RequestBody CreateOrderdetailDTO createorderdetailDTO){
        return ResponseEntity.ok(ResponseDTO.builder()
                .messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
                .messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
                .data(orderDetailSev.create(createorderdetailDTO))
                .build());
    }

    @GetMapping("/v2/admin/order-detail/order-id/{id}")
    private ResponseEntity<ResponseDTO<?>> updateOrderDetailInProget(@PathVariable("id") Integer id){
        return ResponseEntity.ok(ResponseDTO.builder()
                .messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
                .messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
                .data(orderDetailSev.findAllByOrderId(id))
                .build());
    }

    @PutMapping("/v2/admin/order-detail/update-quantity")
    private ResponseEntity<ResponseDTO<?>> updateQuantityOrderDetail(@RequestBody @Valid UpdateQuantityOrderDetailRQDTO obj){
        return ResponseEntity.ok(ResponseDTO.builder()
                .messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
                .messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
                .data(orderDetailSev.updateQuantity(obj))
                .build());
    }
}
