package com.cg.service.order;

import com.cg.model.*;
import com.cg.model.dto.order.*;
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
        return orderRepository.save(order);
    }

    @Override
    public void delete(Order order) {

    }

    @Override
    public void deleteById(Long id) {

    }

//    @Override
//    public OrderResDTO createOrder(OrderReqDTO orderReqDTO) {
//
//        TableOrder tableOrder = tableOrderRepository.findById(Long.valueOf(orderReqDTO.getTableOrder().getId())).get();
//        Staff staff = staffRepository.findById(Long.valueOf(orderReqDTO.getStaff().getId())).get();
////        Order order = null;
////        if (orderReqDTO.getIdOrder() != null) {
////            order = orderRepository.findById(orderReqDTO.getIdOrder()).get();
////        }
////        if (order == null) {
////            order = new Order();
////        }
//        Order order = new Order();
//
//
//        order.setTableOrder(tableOrder);
//        order.setStaff(staff);
//        order.setTotalAmount(BigDecimal.ZERO);
//        order.setPaid(false);
//        order = orderRepository.save(order);
//        tableOrder.setStatus(EStatus.ROLE_OUT_OF_STOCK);
//        tableOrderRepository.save(tableOrder);
//
//        String idProduct = orderReqDTO.getOrderDetail().getProduct().getId();
//        Integer quantity = Integer.valueOf(orderReqDTO.getOrderDetail().getQuantity());
//        Product product = productRepository.findById(Long.valueOf(idProduct)).get();
//
//        OrderDetail orderDetail = orderDetailRepository.findByOrderDetailByIdProductAndIdOrder(product.getId(), order.getId(), orderReqDTO.getOrderDetail().getNote());
//        if (orderDetail == null) {
//            orderDetail = new OrderDetail();
//            orderDetail.setQuantity(0);
//            orderDetail.setAmount(BigDecimal.ZERO);
//        }
//
//        Integer quantityNew = quantity + orderDetail.getQuantity();
//        orderDetail.setProduct(product);
//        orderDetail.setQuantity(quantityNew);
//        orderDetail.setNote(orderReqDTO.getOrderDetail().getNote());
//        orderDetail.setOrder(order);
//        BigDecimal amount = new BigDecimal(quantityNew).multiply(product.getPrice());
//        orderDetail.setAmount(amount);
//        orderDetail.setPrice(product.getPrice());
//
//
//        orderDetailRepository.save(orderDetail);
//
//        List<OrderDetail> orderDetails = List.of(orderDetail);
//        order.setOrderDetails(orderDetails);
//        order.setTotalAmount(amount.add(order.getTotalAmount()));
//
//
//        OrderResDTO orderResDTO = order.toOrderResDTO();
//
//        return orderResDTO;
//    }
//
//    @Override
//    public OrderResDTO updateOrderDetail(OrderReqDTO orderReqDTO, TableOrder tableOrder) {
//
//
//        Order order = orderRepository.findByTableId(Long.valueOf(orderReqDTO.getTableOrder().getId())).get();
//        Product product = productRepository.findById(Long.valueOf(orderReqDTO.getOrderDetail().getProduct().getId())).get();
////        OrderDetail orderDetail = orderDetailRepository.findByOrderId(order.getId());
//        OrderDetail orderDetail = new OrderDetail();
//        List<OrderDetail> orderDetails = orderDetailRepository.findListOrderDetailByOrderId(order.getId());
//
//        for (OrderDetail item : orderDetails) {
//
//            if (item.getProduct().getId().equals(Long.parseLong(orderReqDTO.getOrderDetail().getProduct().getId())) && item.getNote().equals(orderReqDTO.getOrderDetail().getNote())) {
//                item.setQuantity(item.getQuantity() + Integer.parseInt(orderReqDTO.getOrderDetail().getQuantity()));
//                BigDecimal amountNew = item.getPrice().multiply(BigDecimal.valueOf(Integer.parseInt(orderReqDTO.getOrderDetail().getQuantity())));
//                BigDecimal amount = new BigDecimal(item.getQuantity()).multiply(item.getPrice());
//                item.setAmount(amount);
//                orderDetailRepository.save(item);
//                order.setTotalAmount(order.getTotalAmount().add(amountNew));
//                orderRepository.save(order);
//                OrderResDTO orderResDTO = order.toOrderResDTO();
//                return orderResDTO;
//            }
//        }
//        orderDetail.setProduct(product);
//        orderDetail.setQuantity(Integer.valueOf(orderReqDTO.getOrderDetail().getQuantity()));
//        orderDetail.setNote(orderReqDTO.getOrderDetail().getNote());
//        orderDetail.setPrice(product.getPrice());
//        orderDetail.setOrder(order);
//
//        BigDecimal amount = new BigDecimal(orderReqDTO.getOrderDetail().getQuantity()).multiply(product.getPrice());
//        orderDetail.setAmount(amount);
//        orderDetailRepository.save(orderDetail);
//
//        order.setTotalAmount(order.getTotalAmount().add(amount));
//        orderRepository.save(order);
//
//
////        if (orderDetails.isEmpty()) {
////            orderDetail.setQuantity(Integer.valueOf(orderReqDTO.getOrderDetail().getQuantity()));
////            orderDetail.setNote(orderReqDTO.getOrderDetail().getNote());
////            orderDetail.setPrice(product.getPrice());
////            orderDetail.setOrder(order);
////            orderDetail.setProduct(product);
////
////            BigDecimal amountNew = new BigDecimal(orderReqDTO.getOrderDetail().getQuantity()).multiply(product.getPrice());
////
////            BigDecimal amount = orderDetail.getAmount();
////
////            if (amount.compareTo(amountNew) <= 0 ) {
////
////                order.setTotalAmount(order.getTotalAmount().add(amountNew.subtract(amount)));
////            }else {
////                order.setTotalAmount(order.getTotalAmount().subtract(amount.subtract(amountNew)));
////            }
////            orderDetail.setAmount(amountNew);
////
////            orderDetailRepository.save(orderDetail);
////
////
////
////            order = orderRepository.save(order);
////
////        } else {
////            for (OrderDetail item : orderDetails) {
////                if(item.getProduct().getId() == Long.parseLong(orderReqDTO.getOrderDetail().getProduct().getId()) && item.getNote() == orderReqDTO.getOrderDetail().getNote()){
////                    item.setQuantity(item.getQuantity() + Integer.parseInt(orderReqDTO.getOrderDetail().getQuantity()));
////                    BigDecimal amount = new BigDecimal(item.getQuantity()).multiply(item.getPrice());
////                    item.setAmount(amount);
////                    orderDetailRepository.save(item);
////                }
////
////            }
////            orderDetail.setProduct(product);
////            orderDetail.setQuantity(Integer.valueOf(orderReqDTO.getOrderDetail().getQuantity()));
////            orderDetail.setNote(orderReqDTO.getOrderDetail().getNote());
////            orderDetail.setPrice(product.getPrice());
////            orderDetail.setOrder(order);
////
////            BigDecimal amount = new BigDecimal(orderReqDTO.getOrderDetail().getQuantity()).multiply(product.getPrice());
////            orderDetail.setAmount(amount);
////
////            orderDetailRepository.save(orderDetail);
////
////            order.setTotalAmount(order.getTotalAmount().add(amount));
////            order = orderRepository.save(order);
////        }
//
//
//        OrderResDTO orderResDTO = order.toOrderResDTO();
//        return orderResDTO;
//    }


    @Override
    public OrderResDTO deleteByIdOrder(OrderReqDTO orderReqDTO, TableOrder tableOrder) {

        Order order = orderRepository.findByTableId(tableOrder.getId()).get();

        OrderDetail orderDetail = orderDetailRepository.findById(orderReqDTO.getOrderDetail().getOrderDetailId()).get();
        deleteOrderDetailById(orderReqDTO.getOrderDetail().getOrderDetailId());
//        Order order = orderRepository.findById(orderReqDTO.getIdOrder()).get();
        order.setTotalAmount(order.getTotalAmount().subtract(orderDetail.getAmount()));
        if (order.getTotalAmount().longValue() == 0l) {
            order.setPaid(true);
            order.getTableOrder().setStatus(EStatus.ROLE_STOCKING);
        }
        OrderResDTO orderResDTO = order.toOrderResDTO();
        return orderResDTO;
    }

    @Override
    public OrderCreResDTO creOrder(OrderCreReqDTO orderCreReqDTO, User user) {

        Order order = new Order();
        TableOrder tableOrder = tableOrderRepository.findById(orderCreReqDTO.getTableId()).get();
        Optional<Staff> optionalStaff = staffRepository.findByUserAndDeletedIsFalse(user);
        order.setStaff(optionalStaff.get());
        order.setTableOrder(tableOrder);
        order.setTotalAmount(BigDecimal.ZERO);
        order.setPaid(false);

        orderRepository.save(order);
        tableOrder.setStatus(EStatus.ROLE_OUT_OF_STOCK);
        tableOrderRepository.save(tableOrder);

        Product product = productRepository.findById(orderCreReqDTO.getProductId()).get();

        OrderDetail orderDetail = orderDetailRepository.findByOrderDetailByIdProductAndIdOrder(product.getId(), order.getId(), orderCreReqDTO.getNote());
        if (orderDetail == null) {
            orderDetail = new OrderDetail();
            orderDetail.setQuantity(0);
            orderDetail.setAmount(BigDecimal.ZERO);
        }

        Integer quantityNew = orderCreReqDTO.getQuantity() + orderDetail.getQuantity();
        orderDetail.setProduct(product);
        orderDetail.setQuantity(quantityNew);
        orderDetail.setNote(orderCreReqDTO.getNote());
        orderDetail.setOrder(order);
        BigDecimal amount = new BigDecimal(quantityNew).multiply(product.getPrice());
        orderDetail.setAmount(amount);
        orderDetail.setPrice(product.getPrice());

        orderDetailRepository.save(orderDetail);

        List<OrderDetail> orderDetails = List.of(orderDetail);
        order.setOrderDetails(orderDetails);
        order.setTotalAmount(amount.add(order.getTotalAmount()));

        OrderCreResDTO orderCreResDTO = order.toOrderCreResDTO();
        orderCreResDTO.setQuantity(quantityNew);
        orderCreResDTO.setProductId(product.getId());
        orderCreResDTO.setNote(orderDetail.getNote());
        orderCreResDTO.setUserName(order.getStaff().getTitle());

        return orderCreResDTO;
    }

    @Override
    public OrderUpResDTO upOrderDetail(OrderUpReqDTO orderUpReqDTO, TableOrder tableOrder, User user) {
//        tableOrder = tableOrderRepository.findById(orderUpReqDTO.getTableId()).get();
        Order order = orderRepository.findByTableId(tableOrder.getId()).get();

        Product product = productRepository.findById(orderUpReqDTO.getProductId()).get();
        OrderDetail orderDetail = new OrderDetail();
        List<OrderDetail> orderDetails = orderDetailRepository.findListOrderDetailByOrderId(order.getId());

        for (OrderDetail item : orderDetails) {

            if (item.getProduct().getId().equals(orderUpReqDTO.getProductId()) && item.getNote().equals(orderUpReqDTO.getNote())) {
                item.setQuantity(item.getQuantity() + orderUpReqDTO.getQuantity());
                BigDecimal amountNew = item.getPrice().multiply(BigDecimal.valueOf(orderUpReqDTO.getQuantity()));
                BigDecimal amount = new BigDecimal(item.getQuantity()).multiply(item.getPrice());
                item.setAmount(amount);
                orderDetailRepository.save(item);
                order.setTotalAmount(order.getTotalAmount().add(amountNew));
                orderRepository.save(order);
                OrderUpResDTO orderUpResDTO = order.toOrderUpResDTO();
                orderUpResDTO.setQuantity(item.getQuantity());
                orderUpResDTO.setNote(item.getNote());
                orderUpResDTO.setProductId(product.getId());
                orderUpResDTO.setUserName(order.getStaff().getTitle());
                return orderUpResDTO;
            }
        }
        orderDetail.setProduct(product);
        orderDetail.setQuantity(orderUpReqDTO.getQuantity());
        orderDetail.setNote(orderUpReqDTO.getNote());
        orderDetail.setPrice(product.getPrice());
        orderDetail.setOrder(order);

        BigDecimal amount = new BigDecimal(orderUpReqDTO.getQuantity()).multiply(product.getPrice());
        orderDetail.setAmount(amount);
        orderDetailRepository.save(orderDetail);

        order.setTotalAmount(order.getTotalAmount().add(amount));
        orderRepository.save(order);
        OrderUpResDTO orderUpResDTO = order.toOrderUpResDTO();
        orderUpResDTO.setQuantity(orderDetail.getQuantity());
        orderUpResDTO.setNote(orderDetail.getNote());
        orderUpResDTO.setProductId(product.getId());
        orderUpResDTO.setUserName(order.getStaff().getTitle());
        return orderUpResDTO;
    }


    public void deleteOrderDetailById(Long orderDetailId) {
        orderDetailRepository.deleteById(orderDetailId);
    }

}
