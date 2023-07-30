package com.cg.repository;

import com.cg.model.Order;
import com.cg.model.TableOrder;
import com.cg.model.dto.order.OrderDTO;
import com.cg.model.dto.order.OrderResDTO;
import com.cg.model.enums.ETableStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {


    @Query("SELECT ord FROM Order AS ord WHERE ord.tableOrder.id = :tableId AND ord.paid = false")
    Optional<Order> findByTableId(@Param("tableId") Long tableId);


    List<Order> findByTableOrderAndPaid(TableOrder tableOrder, Boolean paid);


    @Query("SELECT SUM(od.amount) FROM OrderDetail AS od WHERE od.order.id = :orderId")
    BigDecimal getOrderTotalAmount(@Param("orderId") Long orderId);


}
