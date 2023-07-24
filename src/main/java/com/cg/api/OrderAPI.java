package com.cg.api;

import com.cg.exception.DataInputException;
import com.cg.model.Order;
import com.cg.model.OrderDetail;
import com.cg.model.dto.order.OrderReqDTO;
import com.cg.model.dto.order.OrderResDTO;
import com.cg.service.order.IOrderService;
import com.cg.service.orderDetail.IOrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderAPI {
    @Autowired
    private IOrderService orderService;

    @Autowired
    private IOrderDetailService orderDetailService;

    @GetMapping
    public ResponseEntity<?> getAllOrder(){
        List<Order> orders = orderService.findAll();


        return new ResponseEntity<>(HttpStatus.OK);
    }

    // tạo mới order
    @PostMapping
    public ResponseEntity<?> saveOrder(@RequestBody OrderReqDTO orderReqDTO){


        OrderResDTO orderResDTO = orderService.createOrder(orderReqDTO);
        return new ResponseEntity<>(orderResDTO, HttpStatus.CREATED);
    }

    // thêm order vào order củ
    @PostMapping("/{id}/order-details")
    public ResponseEntity<?> createOrderDetail(@RequestBody OrderReqDTO orderReqDTO, @PathVariable Long id){

        OrderResDTO orderResDTO = orderService.createOrderDetail(orderReqDTO, id);
        return new ResponseEntity<>(orderResDTO, HttpStatus.CREATED);
    }

    // sửa order
    @PatchMapping("/{id}/order-details")
    public ResponseEntity<?> updateOrderDetail(@RequestBody OrderReqDTO orderReqDTO, @PathVariable Long id){

       OrderResDTO orderResDTO = orderService.updateOrderDetail(orderReqDTO, id);
         return new ResponseEntity<>(orderResDTO, HttpStatus.OK);
    }

    // xóa order
    @DeleteMapping("/{orderId}/order-details/{orderDetailId}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long orderId, @PathVariable Long orderDetailId) {
        Order order = orderService.findById(orderId).orElseThrow(() -> {
           throw new DataInputException("sản phẩm không tồn tại");
        });

        OrderDetail orderDetail = orderDetailService.findById(orderDetailId).orElseThrow(() -> {
           throw new DataInputException("Mã sản phẩm không tồn tại");
        });


        orderService.deleteByIdOrder(orderId, orderDetailId);

        order = orderService.findById(orderId).get();
        OrderResDTO orderResDTO = order.toOrderResDTO();
        return new ResponseEntity<>(orderResDTO, HttpStatus.OK);
    }
}
