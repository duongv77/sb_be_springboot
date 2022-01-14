package duong.dev.controller.admin;

import javax.servlet.ServletException;
import javax.validation.Valid;

import duong.dev.dto.OrderDTO;
import duong.dev.dto.request.CreateOrderReturnRequestDTO;
import duong.dev.service.OrderInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import duong.dev.common.Common;
import duong.dev.common.ResponeCustom;
import duong.dev.dto.ResponseDTO;
import duong.dev.dto.request.CreateOrderTypeDTO;
import duong.dev.dto.request.UpdateStatusVsAddressOrderDTO;

import java.io.IOException;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = Common.URL)
public class AdminOrderController {
	@Autowired private OrderInterface orderSev;

	@GetMapping("/v2/admin/order/{orderstatus}/{sort}")
	public ResponseEntity<ResponseDTO<?>> getListOrderbyStatus(@PathVariable("orderstatus") Integer status,@PathVariable("sort") String sort){
		return ResponseEntity.ok(ResponseDTO.builder().messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
				.messageName(ResponeCustom.MESSAGE_NAME_SUCCESS).data(orderSev.getListOrderByStatus(status,sort))
				.build());
	}
	
	@GetMapping("/v2/admin/order-filter/{sort}")
	public ResponseEntity<ResponseDTO<?>> findListOrder(@PathVariable("sort") String sort){
		return ResponseEntity.ok(ResponseDTO.builder()
				.messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
				.messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
				.data(orderSev.findListOrder(sort))
				.build());
	}
	
	@GetMapping("/v2/admin/order/success-order/{sort}")
	public ResponseEntity<ResponseDTO<?>> findOrderSuccess(@PathVariable("sort") String sort){
		return ResponseEntity.ok(ResponseDTO.builder().messageCode(
						ResponeCustom.MESSAGE_CODE_SUCCESS)
				.messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
				.data(orderSev.getListOrderSuccess(sort)).build());
	}


	@GetMapping("/v2/admin/order-new")
	public ResponseEntity<ResponseDTO<?>> getListOrderNew(){
		return ResponseEntity.ok(ResponseDTO.builder().messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
				.messageName(ResponeCustom.MESSAGE_NAME_SUCCESS).data(orderSev.findByListOrderNew())
				.build());
	}

	@GetMapping("/v2/admin/order-month")
	public ResponseEntity<ResponseDTO<?>> getListOrderMonth(){
		return ResponseEntity.ok(ResponseDTO.builder().messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
				.messageName(ResponeCustom.MESSAGE_NAME_SUCCESS).data(orderSev.finByListOrderMonth())
				.build());
	}

	@GetMapping("/v2/admin/order")
	public ResponseEntity<ResponseDTO<?>> getListOrder(){
		return ResponseEntity.ok(ResponseDTO.builder()
                .messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
                .messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
                .data(orderSev.getListOrder())
                .build());
	}


	@GetMapping("/v2/admin/order-search/{sort}/key/{keyword}")
	public ResponseEntity<ResponseDTO<?>> searchOrder(@PathVariable("sort") String sort, @PathVariable("keyword") String keyword){
		return ResponseEntity.ok(ResponseDTO.builder()
				.messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
				.messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
				.data(orderSev.searchOrder(sort, keyword))
				.build());
	}
	
	@PutMapping("/v2/admin/order")
	public ResponseEntity<ResponseDTO<?>> updateOrder(@RequestBody UpdateStatusVsAddressOrderDTO orderDTO) {
		return ResponseEntity.ok(ResponseDTO.builder().messageCode(ResponeCustom.MESSAGE_CODE_SUCCESS)
				.messageName(ResponeCustom.MESSAGE_NAME_SUCCESS).data(orderSev.updateStatusVsAddress(orderDTO))
				.build());
	}

	@PostMapping("/v2/admin/payment")
	public ResponseEntity<ResponseDTO<?>> createOrderOff(@RequestBody @Valid CreateOrderTypeDTO createOrderTypeDTO) {
		return ResponseEntity.ok(ResponseDTO.builder().messageCode(
				ResponeCustom.MESSAGE_CODE_SUCCESS)
				.messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
				.data(orderSev.createDetailOff(createOrderTypeDTO)).build());

	}
	
	@PostMapping("/v2/admin/payment/online")
	public ResponseEntity<ResponseDTO<?>> createOrderOnline(@RequestBody @Valid CreateOrderTypeDTO createOrderTypeDTO){
		return ResponseEntity.ok(ResponseDTO.builder().messageCode(
				ResponeCustom.MESSAGE_CODE_SUCCESS)
				.messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
				.data(orderSev.createDetailOnline(createOrderTypeDTO)).build());

	}

	@PutMapping("/v2/admin/order/update-accept/{id}/price/{price}")
	public ResponseEntity<ResponseDTO<?>> updateOrderAccept(@PathVariable("id") Integer id, @PathVariable("price") Integer price){
		return ResponseEntity.ok(ResponseDTO.builder().messageCode(
						ResponeCustom.MESSAGE_CODE_SUCCESS)
				.messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
				.data(orderSev.acceptOrder(id, price)).build());
	}

	@PutMapping("/v2/admin/order/update-cancel/{id}")
	public ResponseEntity<ResponseDTO<?>> updateOrderCancel(@PathVariable("id") Integer id){
		return ResponseEntity.ok(ResponseDTO.builder().messageCode(
						ResponeCustom.MESSAGE_CODE_SUCCESS)
				.messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
				.data(orderSev.cancelOrder(id)).build());
	}

	@PutMapping("/v2/admin/order/update-transform/{id}")
	public ResponseEntity<ResponseDTO<?>> updateOrderStatusTransform(@PathVariable("id") Integer id){
		return ResponseEntity.ok(ResponseDTO.builder().messageCode(
						ResponeCustom.MESSAGE_CODE_SUCCESS)
				.messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
				.data(orderSev.updateOrderStatusTransform(id)).build());
	}

	@PutMapping("/v2/admin/order/update-final/{id}")
	public ResponseEntity<ResponseDTO<?>> updateOrderStatusFinal(@PathVariable("id") Integer id) {
		return ResponseEntity.ok(ResponseDTO.builder().messageCode(
						ResponeCustom.MESSAGE_CODE_SUCCESS)
				.messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
				.data(orderSev.updateOrderStatusFinal(id)).build());
	}

	@PutMapping("/v2/admin/order/update-new/{id}")
	public ResponseEntity<ResponseDTO<?>> updateOrderStatusNew(@PathVariable("id") Integer id){
		return ResponseEntity.ok(ResponseDTO.builder().messageCode(
						ResponeCustom.MESSAGE_CODE_SUCCESS)
				.messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
				.data(orderSev.updateOrderStatusNew(id)).build());
	}

	@PutMapping("/v2/admin/order/return-order")
	public ResponseEntity<ResponseDTO<?>> updateReturnOrder(@Valid @RequestBody CreateOrderReturnRequestDTO obj){
		return ResponseEntity.ok(ResponseDTO.builder().messageCode(
						ResponeCustom.MESSAGE_CODE_SUCCESS)
				.messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
				.data(orderSev.createReturnOrder(obj)).build());
	}
	

	//tạo đơn hàng treo
	@PostMapping("/v2/admin/order/create-order-default")
	public ResponseEntity<ResponseDTO<?>> createOrderDefault()throws IOException, ServletException{
		return ResponseEntity.ok(ResponseDTO.builder().messageCode(
						ResponeCustom.MESSAGE_CODE_SUCCESS)
				.messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
				.data(orderSev.createOrderDefault()).build());
	}

	@GetMapping("/v2/admin/order/find-order-default")
	public ResponseEntity<ResponseDTO<?>> findAllOrderDefault(){
		return ResponseEntity.ok(ResponseDTO.builder().messageCode(
						ResponeCustom.MESSAGE_CODE_SUCCESS)
				.messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
				.data(orderSev.findAllOrderDefault()).build());
	}

//	lấy tổng đơn hàng và tổng đơn hàng thành công để so sánh
	@GetMapping("/v2/admin/order/find-count/order/order-success")
	public ResponseEntity<ResponseDTO<?>> findCountOrderAndCountOrderSuccess(){
		return ResponseEntity.ok(ResponseDTO.builder().messageCode(
						ResponeCustom.MESSAGE_CODE_SUCCESS)
				.messageName(ResponeCustom.MESSAGE_NAME_SUCCESS)
				.data(orderSev.reportCountOrder()).build());
	}
}
