package com.cg.model.dto.bill;

import com.cg.model.Bill;
import com.cg.model.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BillReqDTO {
    private BigDecimal totalAmonut;
//    private Order orderId;

    public Bill toBill(Order orderId) {
        return new Bill()
                .setId(null)
                .setOrder(orderId)
                .setTotalAmount(totalAmonut)
                ;
    }


}
