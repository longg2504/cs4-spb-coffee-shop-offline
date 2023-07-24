package com.cg.repository;

import com.cg.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail,Long> {
    @Query("SELECT odt FROM OrderDetail as odt WHERE odt.product.id = :idProduct AND odt.order.id = :idOrder and odt.note LIKE :note" )
    OrderDetail findByOrderDetailByIdProductAndIdOrder(@Param("idProduct") Long idProduct,@Param("idOrder") Long idOrder, @Param("note") String note);

     @Query("SELECT SUM(odt.amount) FROM OrderDetail AS odt WHERE odt.order.id = :orderId")
     BigDecimal findByOrderByIdSumAmount(@Param("orderId") Long orderId);
}
