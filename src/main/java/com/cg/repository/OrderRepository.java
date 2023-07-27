package com.cg.repository;

import com.cg.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {


    @Query("SELECT ord FROM Order AS ord WHERE ord.tableOrder.id = :tableId AND ord.paid = false")
    Optional<Order> findByTableId(@Param("tableId") Long tableId);

}
