package com.cg.service.order;

import com.cg.exception.DataInputException;
import com.cg.model.*;
import com.cg.model.dto.order.OrderReqDTO;
import com.cg.model.dto.order.OrderResDTO;
import com.cg.model.enums.EStatus;
import com.cg.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class OrderServiceImpl implements IOrderService{

    @Autowired
    private TableOrderRepository tableOrderRepository;
    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Override
    public List<Order> findAll() {
        return null;
    }

    @Override
    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public Order save(Order order) {
        return null;
    }

    @Override
    public void delete(Order order) {

    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public OrderResDTO createOrder(OrderReqDTO orderReqDTO) {
        TableOrder tableOrder = tableOrderRepository.findById(orderReqDTO.getTableOrder().getId()).get();

        if (tableOrder.getStatus().equals(EStatus.ROLE_OUT_OF_STOCK)){
            throw new DataInputException("bàn đang hoạt động");
        }
        Staff staff = staffRepository.findById(orderReqDTO.getStaff().getId()).get();


        Order order = null;
        if(orderReqDTO.getIdOrder() != null){
           order =  orderRepository.findById(orderReqDTO.getIdOrder()).get();
        }
        if (order == null){
            order = new Order();
        }
        tableOrder.setStatus(EStatus.ROLE_OUT_OF_STOCK);
        tableOrder = tableOrderRepository.save(tableOrder);

        order.setTableOrder(tableOrder);
        order.setStaff(staff);
        order = orderRepository.save(order);


        Long idProduct = orderReqDTO.getOrderDetail().getProduct().getId();
        Integer quantity = orderReqDTO.getOrderDetail().getQuantity();
        Product product = productRepository.findById(idProduct).get();



        OrderDetail orderDetail = orderDetailRepository.findByOrderDetailByIdProductAndIdOrder(product.getId(),order.getId(), orderReqDTO.getOrderDetail().getNote());
        if (orderDetail == null) {
            orderDetail = new OrderDetail();
            orderDetail.setQuantity(0);
        }

        Integer quantityNew = quantity + orderDetail.getQuantity() ;
        orderDetail.setProduct(product);
        orderDetail.setQuantity(quantityNew);
        orderDetail.setNote(orderReqDTO.getOrderDetail().getNote());
        orderDetail.setOrder(order);
        BigDecimal amount = new BigDecimal(quantityNew).multiply(product.getPrice());
        orderDetail.setAmount(amount);
        orderDetail.setPrice(product.getPrice());


        orderDetailRepository.save(orderDetail);

        List<OrderDetail> orderDetails = List.of(orderDetail);
        order.setOrderDetails(orderDetails);

        OrderResDTO orderResDTO = order.toOrderResDTO();

        return orderResDTO;
    }

    @Override
    public OrderResDTO createOrderDetail(OrderReqDTO orderReqDTO, Long idOrder) {
        TableOrder tableOrder = tableOrderRepository.findById(orderReqDTO.getTableOrder().getId()).get();
        if(tableOrder == null){
            throw new DataInputException("Bàn ko tồn tại");
        }
        Staff staff = staffRepository.findById(orderReqDTO.getStaff().getId()).get();
        if(staff == null){
            throw new DataInputException("Nhân viên ko tồn tại");
        }

        Order order = orderRepository.findById(idOrder).get();
        Product product = productRepository.findById(orderReqDTO.getOrderDetail().getProduct().getId()).get();
        OrderDetail orderDetail = orderDetailRepository.findByOrderDetailByIdProductAndIdOrder(product.getId(),idOrder, orderReqDTO.getOrderDetail().getNote());
        if(orderDetail !=null){
            throw new DataInputException(("Món không đã có sẵn trong bill"));
        }
        orderDetail = new OrderDetail();
        orderDetail.setProduct(product);
        orderDetail.setQuantity(orderReqDTO.getOrderDetail().getQuantity());
        orderDetail.setNote(orderReqDTO.getOrderDetail().getNote());
        orderDetail.setPrice(product.getPrice());
        orderDetail.setOrder(order);

        BigDecimal amount = new BigDecimal(orderReqDTO.getOrderDetail().getQuantity()).multiply(product.getPrice());
        orderDetail.setAmount(amount);

        orderDetailRepository.save(orderDetail);

        BigDecimal totalAmount = orderDetailRepository.findByOrderByIdSumAmount(idOrder);
        order.setTotalAmount(totalAmount.add(amount));
        order = orderRepository.save(order);



        OrderResDTO orderResDTO = order.toOrderResDTO();
        return orderResDTO;
    }

    @Override
    public OrderResDTO updateOrderDetail(OrderReqDTO orderReqDTO, Long idOrder) {
        TableOrder tableOrder = tableOrderRepository.findById(orderReqDTO.getTableOrder().getId()).get();
        if(tableOrder == null){
            throw new DataInputException("Bàn ko tồn tại");
        }
        Staff staff = staffRepository.findById(orderReqDTO.getStaff().getId()).get();
        if(staff == null){
            throw new DataInputException("Nhân viên ko tồn tại");
        }

        Order order = orderRepository.findById(idOrder).get();
        Product product = productRepository.findById(orderReqDTO.getOrderDetail().getProduct().getId()).get();

        OrderDetail orderDetail = orderDetailRepository.findById(orderReqDTO.getOrderDetail().getOrderDetailId()).get();
        if(orderDetail == null){
            throw new DataInputException(("Cập nhật món không hợp lệ"));
        }else{
            orderDetail.setQuantity(orderReqDTO.getOrderDetail().getQuantity());
            orderDetail.setNote(orderReqDTO.getOrderDetail().getNote());
            orderDetail.setPrice(orderReqDTO.getOrderDetail().getPrice());
            orderDetail.setOrder(order);
            orderDetail.setProduct(product);

            BigDecimal amount = new BigDecimal(orderReqDTO.getOrderDetail().getQuantity()).multiply(product.getPrice());
            orderDetail.setAmount(amount);

            orderDetailRepository.save(orderDetail);
        }


        BigDecimal totalAmount = orderDetailRepository.findByOrderByIdSumAmount(idOrder);
        order.setTotalAmount(totalAmount);
        order = orderRepository.save(order);

        OrderResDTO orderResDTO = order.toOrderResDTO();
        return orderResDTO;
    }

    @Override
    public OrderResDTO deleteByIdOrder(Long orderId, Long orderDetailId) {

        deleteOrderDetailById(orderDetailId);
        Order order = orderRepository.findById(orderId).get();
        OrderResDTO orderResDTO = order.toOrderResDTO();
        return orderResDTO;
    }

    public void deleteOrderDetailById(Long orderDetailId){
         orderDetailRepository.deleteById(orderDetailId);
    }


}
