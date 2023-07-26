package com.cg.service.order;

import com.cg.model.Order;
import com.cg.model.OrderDetail;
import com.cg.model.dto.order.OrderDTO;
import com.cg.model.dto.order.OrderReqDTO;
import com.cg.model.dto.order.OrderResDTO;
import com.cg.service.IGeneralService;

import java.util.List;
import java.util.Optional;

public interface IOrderService extends IGeneralService<Order,Long> {


    Optional<Order> findByTableId(Long tableId);

    OrderResDTO createOrder(OrderReqDTO orderReqDTO);

    OrderResDTO updateOrderDetail(OrderReqDTO orderReqDTO, Long idOrder);


    OrderResDTO deleteByIdOrder(Long orderId,Long orderDetailId);


}
