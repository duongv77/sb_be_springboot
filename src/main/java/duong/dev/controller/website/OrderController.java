package duong.dev.controller.website;

import javax.servlet.ServletException;
import javax.validation.Valid;

import duong.dev.dto.OrderDTO;
import duong.dev.dto.request.CreateOrderReturnRequestDTO;
import duong.dev.dto.request.UpdateOrderdetailQuantityRequestDTO;
import duong.dev.dto.request.UpdateStatusOrderRequestDTO;
import duong.dev.service.OrderInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import duong.dev.common.Common;
import duong.dev.common.ResponeCustom;
import duong.dev.dto.ResponseDTO;
import duong.dev.dto.request.CreateOrderDTO;
import duong.dev.serviceImpl.OrderServiceImpl;

import java.io.IOException;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = Common.URL)
public class OrderController {
	@Autowired private OrderInterface orderSer;

	@PostMapping("/v2/user/order")
	private ResponseEntity<ResponseDTO<?>> createOrderAccount( @RequestBody @Valid CreateOrderDTO createOrderDTO )  throws IOException, ServletException {
		return ResponseEntity.ok(ResponseDTO.builder()
                .messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
                .messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
                .data(orderSer.createDetail(createOrderDTO))
                .build());
	}

	@GetMapping("/v2/user/order")
	private ResponseEntity<ResponseDTO<?>> getOrderHistory()  throws IOException, ServletException {
		return ResponseEntity.ok(ResponseDTO.builder()
				.messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
				.messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
				.data(orderSer.findListOrderHistory())
				.build());
	}

	@GetMapping("/v2/user/order/{id}")
	private ResponseEntity<ResponseDTO<?>> getOrderHistoryDetail()  throws IOException, ServletException {
		return ResponseEntity.ok(ResponseDTO.builder()
				.messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
				.messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
				.data(orderSer.findListOrderHistory())
				.build());
	}

	@GetMapping("/v2/user/order/quantity-order/history")
	private ResponseEntity<ResponseDTO<?>> findQuantityOrderByAccount()  throws IOException, ServletException {
		return ResponseEntity.ok(ResponseDTO.builder()
				.messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
				.messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
				.data(orderSer.findQuantityOrderByAccount())
				.build());
	}

	@PutMapping("/v2/user/order/update-cancel/{id}")
	public ResponseEntity<ResponseDTO<?>> updateOrderCancel(@PathVariable("id") Integer id){
		return ResponseEntity.ok(ResponseDTO.builder().messageCode(
						ResponeCustom.MESSAGE_CODE_SUCCESS)
				.messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
				.data(orderSer.cancelOrder(id)).build());
	}

}
