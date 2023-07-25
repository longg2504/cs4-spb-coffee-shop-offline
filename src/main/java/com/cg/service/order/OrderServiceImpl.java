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
public class OrderServiceImpl implements IOrderService {

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
    public Optional<Order> findByTableId(Long tableId) {
        return orderRepository.findByTableId(tableId);

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

        TableOrder tableOrder = tableOrderRepository.findById(Long.valueOf(orderReqDTO.getTableOrder().getId())).get();
        if (tableOrder.getStatus().equals(EStatus.ROLE_OUT_OF_STOCK)) {
            throw new DataInputException("bàn đang hoạt động");
        }
        Staff staff = staffRepository.findById(Long.valueOf(orderReqDTO.getStaff().getId())).get();


        Order order = null;
        if (orderReqDTO.getIdOrder() != null) {
            order = orderRepository.findById(orderReqDTO.getIdOrder()).get();
        }
        if (order == null) {
            order = new Order();
        }
        tableOrder.setStatus(EStatus.ROLE_OUT_OF_STOCK);
        tableOrder = tableOrderRepository.save(tableOrder);

        order.setTableOrder(tableOrder);
        order.setStaff(staff);
        order.setTotalAmount(BigDecimal.ZERO);
        order.setPaid(false);
        order = orderRepository.save(order);


        String idProduct = orderReqDTO.getOrderDetail().getProduct().getId();
        Integer quantity = Integer.valueOf(orderReqDTO.getOrderDetail().getQuantity());
        Product product = productRepository.findById(Long.valueOf(idProduct)).get();


        OrderDetail orderDetail = orderDetailRepository.findByOrderDetailByIdProductAndIdOrder(product.getId(), order.getId(), orderReqDTO.getOrderDetail().getNote());
        if (orderDetail == null) {
            orderDetail = new OrderDetail();
            orderDetail.setQuantity(0);
            orderDetail.setAmount(BigDecimal.ZERO);
        }

        Integer quantityNew = quantity + orderDetail.getQuantity();
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
        order.setTotalAmount(amount.add(order.getTotalAmount()));


        OrderResDTO orderResDTO = order.toOrderResDTO();

        return orderResDTO;
    }

    @Override
    public OrderResDTO updateOrderDetail(OrderReqDTO orderReqDTO, Long idOrder) {
        TableOrder tableOrder = tableOrderRepository.findById(Long.valueOf(orderReqDTO.getTableOrder().getId())).get();
        if (tableOrder == null) {
            throw new DataInputException("Bàn ko tồn tại");
        }
        Staff staff = staffRepository.findById(Long.valueOf(orderReqDTO.getStaff().getId())).get();
        if (staff == null) {
            throw new DataInputException("Nhân viên ko tồn tại");
        }
        Order order = orderRepository.findById(idOrder).get();
        Product product = productRepository.findById(Long.valueOf(orderReqDTO.getOrderDetail().getProduct().getId())).get();
        OrderDetail orderDetail = orderDetailRepository.findByOrderDetailByIdProductAndIdOrder(product.getId(), idOrder, orderReqDTO.getOrderDetail().getNote());
        if (orderDetail != null) {
            orderDetail.setQuantity(Integer.valueOf(orderReqDTO.getOrderDetail().getQuantity()));
            orderDetail.setNote(orderReqDTO.getOrderDetail().getNote());
            orderDetail.setPrice(product.getPrice());
            orderDetail.setOrder(order);
            orderDetail.setProduct(product);

            BigDecimal amountNew = new BigDecimal(orderReqDTO.getOrderDetail().getQuantity()).multiply(product.getPrice());

            BigDecimal amount = orderDetail.getAmount();

            if (amount.compareTo(amountNew) <= 0 ) {

                order.setTotalAmount(order.getTotalAmount().add(amountNew.subtract(amount)));
            }else {
                order.setTotalAmount(order.getTotalAmount().subtract(amount.subtract(amountNew)));
            }
            orderDetail.setAmount(amountNew);

            orderDetailRepository.save(orderDetail);



            order = orderRepository.save(order);

        } else {
            orderDetail = new OrderDetail();
            orderDetail.setProduct(product);
            orderDetail.setQuantity(Integer.valueOf(orderReqDTO.getOrderDetail().getQuantity()));
            orderDetail.setNote(orderReqDTO.getOrderDetail().getNote());
            orderDetail.setPrice(product.getPrice());
            orderDetail.setOrder(order);

            BigDecimal amount = new BigDecimal(orderReqDTO.getOrderDetail().getQuantity()).multiply(product.getPrice());
            orderDetail.setAmount(amount);

            orderDetailRepository.save(orderDetail);

            order.setTotalAmount(order.getTotalAmount().add(amount));
            order = orderRepository.save(order);
        }


        OrderResDTO orderResDTO = order.toOrderResDTO();
        return orderResDTO;
    }


    @Override
    public OrderResDTO deleteByIdOrder(Long orderId, Long orderDetailId) {
        OrderDetail orderDetail = orderDetailRepository.findById(orderDetailId).get();
        deleteOrderDetailById(orderDetailId);
        Order order = orderRepository.findById(orderId).get();
        order.setTotalAmount(order.getTotalAmount().subtract(orderDetail.getAmount()));
        if (order.getTotalAmount().longValue() == 0l) {
            order.setPaid(true);
            order.getTableOrder().setStatus(EStatus.ROLE_STOCKING);
        }
        OrderResDTO orderResDTO = order.toOrderResDTO();
        return orderResDTO;
    }

    public void deleteOrderDetailById(Long orderDetailId) {
        orderDetailRepository.deleteById(orderDetailId);
    }

}
