package com.cg.model.dto.bill;

import com.cg.model.Order;
import com.cg.model.dto.order.OrderDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BillDTO {

    private Long id;
    private BigDecimal totalAmount;
    private OrderDTO order;

    public BillDTO(Long id, BigDecimal totalAmount, Order order) {
        this.id = id;
        this.totalAmount = totalAmount;
        this.order = order.toOrderDTO();
    }
}

