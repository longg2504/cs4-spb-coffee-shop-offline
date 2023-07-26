package com.cg.service.orderDetail;

import com.cg.model.OrderDetail;
import com.cg.model.dto.orderDetail.OrderDetailByTableResDTO;
import com.cg.service.IGeneralService;

import java.util.List;
import java.util.Optional;

public interface IOrderDetailService extends IGeneralService<OrderDetail,Long> {

    List<OrderDetailByTableResDTO> getOrderDetailByTableResDTO(Long orderId);


    OrderDetail findByOrderDetailByIdProductAndIdOrder(Long idProduct,Long IdOrder,String note);


}

