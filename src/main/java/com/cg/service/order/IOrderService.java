package com.cg.service.order;

import com.cg.model.Order;
import com.cg.model.TableOrder;
import com.cg.model.User;
import com.cg.model.dto.order.*;
import com.cg.service.IGeneralService;

import java.util.Optional;

public interface IOrderService extends IGeneralService<Order,Long> {


    Optional<Order> findByTableId(Long tableId);

//    OrderResDTO createOrder(OrderReqDTO orderReqDTO);
//
//    OrderResDTO updateOrderDetail(OrderReqDTO orderReqDTO, TableOrder tableOrder);


    OrderResDTO deleteByIdOrder(OrderReqDTO orderReqDTO, TableOrder tableOrder);

    OrderCreResDTO creOrder(OrderCreReqDTO orderCreReqDTO, User user);
    OrderUpResDTO upOrderDetail(OrderUpReqDTO orderUpReqDTO, TableOrder tableOrder, User user);



}
