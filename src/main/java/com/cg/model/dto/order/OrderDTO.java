package com.cg.model.dto.order;
import com.cg.model.OrderDetail;
import com.cg.model.Staff;
import com.cg.model.TableOrder;
import com.cg.model.dto.orderDetail.OrderDetailDTO;
import com.cg.model.dto.staff.StaffDTO;
import com.cg.model.dto.tableOrder.TableOrderDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class OrderDTO {
    private Long id;
    private StaffDTO staff;
    private TableOrderDTO tableOrder;
    private BigDecimal totalAmount;

    private OrderDetailDTO orderDetailDTO;
    private Boolean paid;

//    public OrderDTO(Long id, Staff staff, TableOrder tableOrder, BigDecimal totalAmount, Boolean paid) {
//        this.id = id;
//        this.staff = staff.toStaffDTO();
//        this.tableOrder = tableOrder.toTableOrderDTO();
//        this.totalAmount = totalAmount;
//        this.paid = paid;
//    }
}
