package com.cg.model;


import com.cg.model.dto.order.OrderResDTO;
import com.cg.model.dto.orderDetail.OrderDetailDTO;
import com.cg.model.dto.staff.StaffDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "staff_id",referencedColumnName = "id",nullable = false)
    private Staff staff;

    @OneToOne
    @JoinColumn(name = "table_order_id",referencedColumnName = "id",nullable = false)
    private TableOrder tableOrder;

    @Column(name = "total_amount")
    private BigDecimal totalAmount;

    @OneToMany(mappedBy = "order")
    @JsonIgnore
    private List<OrderDetail> orderDetails;



    public OrderResDTO toOrderResDTO(){
        List<OrderDetailDTO> orderDetailDTOS = new ArrayList<>();
        for(int i=0;i<this.getOrderDetails().size();i++){
            OrderDetailDTO orderDetailDTO = this.getOrderDetails().get(i).toOrderDetailDTO();
            orderDetailDTOS.add(orderDetailDTO);
        }
        return new OrderResDTO()
                .setId(id)
                .setStaff(staff.toStaffDTO())
                .setTableOrder(tableOrder.toTableOrderDTO())
                .setOrderDetails(orderDetailDTOS)
                ;
    }
}
