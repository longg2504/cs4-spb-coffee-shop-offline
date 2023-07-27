package com.cg.model.dto.order;

import com.cg.model.dto.orderDetail.OrderDetailDTO;
import com.cg.model.dto.tableOrder.TableOrderDTO;
import com.cg.model.dto.staff.StaffDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class OrderResDTO {
    private Long id;
    private StaffDTO staff;
    private TableOrderDTO tableOrder;
    private List<OrderDetailDTO> orderDetails;
    private Boolean paid;

}
