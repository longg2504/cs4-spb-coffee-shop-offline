package com.cg.api;

import com.cg.exception.DataInputException;
import com.cg.model.Order;
import com.cg.model.OrderDetail;
import com.cg.model.TableOrder;
import com.cg.model.dto.order.OrderDTO;
import com.cg.model.dto.order.OrderReqDTO;
import com.cg.model.dto.order.OrderResDTO;
import com.cg.model.dto.orderDetail.OrderDetailByTableResDTO;
import com.cg.model.enums.EStatus;
import com.cg.service.order.IOrderService;
import com.cg.service.orderDetail.IOrderDetailService;
import com.cg.service.tableOrder.ITableOrderService;
import com.cg.utils.AppUtils;
import com.cg.utils.ValidateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
public class OrderAPI {
    @Autowired
    private IOrderService orderService;

    @Autowired
    private IOrderDetailService orderDetailService;

    @Autowired
    private ValidateUtils validateUtils;
    @Autowired
    private ITableOrderService tableOrderService;


    @Autowired
    private AppUtils appUtils;


    @GetMapping("/table/{tableId}")
    public ResponseEntity<?> getOrderByTableId(@PathVariable("tableId") String tableIdStr) {
        if (!validateUtils.isNumberValid(tableIdStr)) {
            throw new DataInputException("Mã bàn không hợp lệ");
        }
        Long tableId = Long.valueOf(tableIdStr);

        Optional<Order> orderOptional = orderService.findByTableId(tableId);

        if (orderOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<OrderDetailByTableResDTO> orderDetails = orderDetailService.getOrderDetailByTableResDTO(orderOptional.get().getId());

        if (orderDetails.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(orderDetails, HttpStatus.OK);
    }

    // tạo mới order
//    @PostMapping
//    public ResponseEntity<?> saveOrder(@RequestBody OrderReqDTO orderReqDTO, BindingResult bindingResult) {
//
//        new OrderReqDTO().validate(orderReqDTO, bindingResult);
//        if (bindingResult.hasFieldErrors()) {
//            return appUtils.mapErrorToResponse(bindingResult);
//        }
//
//
//        OrderResDTO orderResDTO = orderService.createOrder(orderReqDTO);
//        return new ResponseEntity<>(orderResDTO, HttpStatus.CREATED);
//    }


    @PostMapping("/order-details")
    public ResponseEntity<?> createOrderDetail(@RequestBody OrderReqDTO orderReqDTO, BindingResult bindingResult) {

        new OrderReqDTO().validate(orderReqDTO, bindingResult);

        if (bindingResult.hasFieldErrors()) {
            return appUtils.mapErrorToResponse(bindingResult);
        }

        Long tableId = Long.valueOf(orderReqDTO.getTableOrder().getId());
        TableOrder tableOrder = tableOrderService.findById(tableId).get();

        if (tableOrder.getStatus() == EStatus.ROLE_STOCKING) {
             OrderResDTO orderResDTO = orderService.createOrder(orderReqDTO);
             return new ResponseEntity<>(orderResDTO ,HttpStatus.OK);
        }
        else {
            OrderResDTO orderResDTO = orderService.updateOrderDetail(orderReqDTO, tableOrder);
            return new ResponseEntity<>(orderResDTO ,HttpStatus.OK);
        }
    }

    // xóa order
    @DeleteMapping("/{orderId}/order-details/{orderDetailId}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long orderId, @PathVariable Long orderDetailId) {
        Order order = orderService.findById(orderId).orElseThrow(() -> {
            throw new DataInputException("sản phẩm không tồn tại");
        });

        Optional<OrderDetail> optionalOrderDetail = orderDetailService.findById(orderDetailId);
        if (optionalOrderDetail.isEmpty()) {
            Map<String, String> data = new HashMap<>();
            data.put("message", "Mã sản phẩm không tồn tại");
            return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
        }

        orderService.deleteByIdOrder(orderId, orderDetailId);
        OrderResDTO orderResDTO = order.toOrderResDTO();
        return new ResponseEntity<>(orderResDTO, HttpStatus.OK);
    }


}
