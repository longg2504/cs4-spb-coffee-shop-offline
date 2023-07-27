package com.cg.model.dto.bill;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class BillResDTO {
    private Long id;
    private BigDecimal totalAmount;
    private Long orderId;
    private Boolean paid;

}
