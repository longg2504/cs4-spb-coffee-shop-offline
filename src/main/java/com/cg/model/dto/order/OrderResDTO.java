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

//    public OrderResDTO(Long id, Staff staff, TableOrder tableOrder, OrderDetail orderDetail){
//        this.id = id;
//        this.staff = staff.toStaffDTO();
//        this.tableOrder= tableOrder.toTableOrderDTO();
//        this.orderDetails = orderDetail.toOrderDetailDTO();
//    }





}
