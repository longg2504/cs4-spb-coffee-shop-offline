package com.cg.repository;

import com.cg.model.OrderDetail;
import com.cg.model.dto.orderDetail.OrderDetailByTableResDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail,Long> {

    @Query("SELECT NEW com.cg.model.dto.orderDetail.OrderDetailByTableResDTO (" +
                "od.id, " +
                "od.product.id, " +
                "od.product.title, " +
                "od.price, " +
                "od.quantity, " +
                "od.amount, " +
                "od.product.unit," +
                "od.note " +
            ")" +
            "FROM OrderDetail AS od " +
            "WHERE od.order.id = :orderId"
    )
    List<OrderDetailByTableResDTO> getOrderDetailByTableResDTO(@Param("orderId") Long orderId);




    @Query("SELECT odt FROM OrderDetail as odt WHERE odt.product.id = :idProduct AND odt.order.id = :idOrder and odt.note LIKE :note" )
    OrderDetail findByOrderDetailByIdProductAndIdOrder(@Param("idProduct") Long idProduct,@Param("idOrder") Long idOrder, @Param("note") String note);

     @Query("SELECT SUM(odt.amount) FROM OrderDetail AS odt WHERE odt.order.id = :orderId")
     BigDecimal findByOrderByIdSumAmount(@Param("orderId") Long orderId);
}
