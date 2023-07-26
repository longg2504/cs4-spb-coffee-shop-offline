package com.cg.repository;

import com.cg.model.Bill;
import com.cg.model.dto.bill.BillDTO;
import com.cg.model.dto.order.OrderDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Bill,Long> {
    @Query("SELECT NEW com.cg.model.dto.bill.BillDTO (" +
            "b.id," +
            "b.totalAmount, " +
            "b.order " +
            ") " +
            "FROM Bill AS b"
    )
    List<BillDTO> findAllBillDTO();

//    @Query("SELECT NEW com.cg.model.dto.order.OrderDTO (" +
//            "or.id, " +
//            "or.staff, " +
//            "or.tableOrder, " +
//            "or.totalAmount, "+
//            "or.paid " +
//            ")" +
//            "FROM Order AS or " +
//            "WHERE or.id = :orderId"
//    )
//    List<OrderDTO> findBillOrderDTO(@Param("orderId") Long orderId);
}
