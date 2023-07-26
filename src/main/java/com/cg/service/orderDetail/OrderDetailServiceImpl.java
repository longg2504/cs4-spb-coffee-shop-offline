package com.cg.service.orderDetail;

import com.cg.model.OrderDetail;
import com.cg.model.dto.order.OrderResDTO;
import com.cg.model.dto.orderDetail.OrderDetailByTableResDTO;
import com.cg.repository.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class OrderDetailServiceImpl implements IOrderDetailService{

    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Override
    public List<OrderDetail> findAll() {
        return null;
    }

    @Override
    public Optional<OrderDetail> findById(Long id) {
        return orderDetailRepository.findById(id);
    }

    @Override
    public List<OrderDetailByTableResDTO> getOrderDetailByTableResDTO(Long orderId) {

        return orderDetailRepository.getOrderDetailByTableResDTO(orderId);
    }

    @Override
    public OrderDetail save(OrderDetail orderDetail) {
        return null;
    }

    @Override
    public void delete(OrderDetail orderDetail) {

    }

    @Override
    public void deleteById(Long id) {

    }


    @Override
    public OrderDetail findByOrderDetailByIdProductAndIdOrder(Long idProduct,Long idOrder, String note) {
        return orderDetailRepository.findByOrderDetailByIdProductAndIdOrder(idProduct,idOrder, note);
    }

    @Override
    public OrderDetail findByOrderId(Long orderId) {
        return orderDetailRepository.findByOrderId(orderId);
    }

    @Override
    public List<OrderDetail> findListOrderDetailByOrderId(Long orderId) {
        return orderDetailRepository.findListOrderDetailByOrderId(orderId);
    }


}
