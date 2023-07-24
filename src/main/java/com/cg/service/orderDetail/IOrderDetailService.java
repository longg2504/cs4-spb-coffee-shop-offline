package com.cg.service.orderDetail;

import com.cg.model.OrderDetail;
import com.cg.service.IGeneralService;

import java.util.Optional;

public interface IOrderDetailService extends IGeneralService<OrderDetail,Long> {
    OrderDetail findByOrderDetailByIdProductAndIdOrder(Long idProduct,Long IdOrder,String note);
}
