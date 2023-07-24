package com.cg.model.dto.order;
import com.cg.model.dto.orderDetail.OrderDetailDTO;
import com.cg.model.dto.staff.StaffDTO;
import com.cg.model.dto.tableOrder.TableOrderDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDTO {
    private Long id;
    private StaffDTO staff;
    private TableOrderDTO tableOrder;
    private BigDecimal totalAmount;
    private OrderDetailDTO orderDetail;
}
