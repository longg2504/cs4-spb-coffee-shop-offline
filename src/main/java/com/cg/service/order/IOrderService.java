package com.cg.service.order;

import com.cg.model.Order;
import com.cg.model.dto.order.OrderReqDTO;
import com.cg.model.dto.order.OrderResDTO;
import com.cg.service.IGeneralService;

public interface IOrderService extends IGeneralService<Order,Long> {

    OrderResDTO createOrder(OrderReqDTO orderReqDTO);

    OrderResDTO createOrderDetail(OrderReqDTO orderReqDTO, Long idOrder);

    OrderResDTO updateOrderDetail(OrderReqDTO orderReqDTO, Long id);

    OrderResDTO deleteByIdOrder(Long orderId,Long orderDetailId);
}
