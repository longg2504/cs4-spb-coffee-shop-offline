package com.cg.api;

import com.cg.exception.DataInputException;
import com.cg.model.Order;
import com.cg.model.Staff;
import com.cg.model.TableOrder;
import com.cg.model.User;
import com.cg.model.dto.order.*;
import com.cg.model.dto.orderDetail.OrderDetailByTableResDTO;
import com.cg.model.enums.EStatus;
import com.cg.service.order.IOrderService;
import com.cg.service.orderDetail.IOrderDetailService;
import com.cg.service.staff.IStaffService;
import com.cg.service.tableOrder.ITableOrderService;
import com.cg.service.user.IUserService;
import com.cg.utils.AppUtils;
import com.cg.utils.ValidateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
    private IUserService userService;

    @Autowired
    private IStaffService staffService;


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


//    @PostMapping("/order-details")
//    public ResponseEntity<?> createOrderDetail(@RequestBody OrderReqDTO orderReqDTO, BindingResult bindingResult) {
//
//
//        new OrderReqDTO().validate(orderReqDTO, bindingResult);
//        if (bindingResult.hasFieldErrors()) {
//            return appUtils.mapErrorToResponse(bindingResult);
//        }
//        Long tableId = Long.valueOf(orderReqDTO.getTableOrder().getId());
//        TableOrder tableOrder = tableOrderService.findById(tableId).orElseThrow(() -> {
//            throw new DataInputException("Bàn không tồn tại");
//        });
//        if (tableOrder.getStatus() == EStatus.ROLE_STOCKING) {
//            OrderResDTO orderResDTO = orderService.createOrder(orderReqDTO);
//            return new ResponseEntity<>(orderResDTO ,HttpStatus.OK);
//        } else {
//            OrderResDTO orderResDTO = orderService.updateOrderDetail(orderReqDTO, tableOrder);
//            return new ResponseEntity<>(orderResDTO ,HttpStatus.OK);
//        }
//
//
//
//
//    }

    // xóa order
    @DeleteMapping("/delete/orderDetail")
    public ResponseEntity<?> deleteOrder(@RequestBody OrderReqDTO orderReqDTO) {
        Long tableId = Long.valueOf(orderReqDTO.getTableOrder().getId());
        TableOrder tableOrder = tableOrderService.findById(tableId).get();
        if(tableOrder.getStatus() == EStatus.ROLE_STOCKING) {
            throw new DataInputException("Bàn không có sản phẩm nào");
        } else {
            OrderResDTO orderResDTO = orderService.deleteByIdOrder(orderReqDTO, tableOrder);
            return new ResponseEntity<>(orderResDTO, HttpStatus.OK);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> creOrder(@RequestBody OrderCreReqDTO orderCreReqDTO){
        String username = appUtils.getPrincipalUsername();
        Optional<User> userOptional = userService.findByName(username);

        TableOrder tableOrder = tableOrderService.findById(orderCreReqDTO.getTableId()).orElseThrow(() -> {
            throw new DataInputException("Bàn không tồn tại");
        });

        if (tableOrder.getStatus() == EStatus.ROLE_STOCKING) {
            OrderCreResDTO orderCreResDTO = orderService.creOrder(orderCreReqDTO, userOptional.get());
            return new ResponseEntity<>(orderCreResDTO,HttpStatus.CREATED);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PatchMapping("/update")
    public ResponseEntity<?> upOrder(@RequestBody OrderUpReqDTO orderUpReqDTO){

        String username = appUtils.getPrincipalUsername();
        Optional<User> userOptional = userService.findByName(username);
        TableOrder tableOrder = tableOrderService.findById(orderUpReqDTO.getTableId()).orElseThrow(() -> {
            throw new DataInputException("Bàn không tồn tại");
        });
        if (tableOrder.getStatus() == EStatus.ROLE_OUT_OF_STOCK){
            OrderUpResDTO orderUpResDTO = orderService.upOrderDetail(orderUpReqDTO, tableOrder,userOptional.get());
            return new ResponseEntity<>(orderUpResDTO ,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/get-username")
    public ResponseEntity<?> getUserName(){
        String username = appUtils.getPrincipalUsername();
        Optional<User> optionalUser = userService.findByName(username);

        Optional<Staff> optionalStaff = staffService.findByUserAndDeletedIsFalse(optionalUser.get());

        Long name = optionalUser.get().getId();



        return new ResponseEntity<>(name,HttpStatus.OK);
    }



}
