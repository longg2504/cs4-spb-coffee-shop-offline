package com.cg.model.dto.order;

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
public class OrderCreResDTO {
    private Long tableId;
    private BigDecimal totalAmount;

    private List<OrderDetailCreResDTO> orderDetails;
}
