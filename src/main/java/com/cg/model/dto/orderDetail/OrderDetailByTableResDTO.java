package com.cg.model.dto.orderDetail;


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
public class OrderDetailByTableResDTO {

    private Long orderDetailId;
    private Long productId;
    private String title;
    private BigDecimal price;
    private Integer quantity;
    private BigDecimal amount;
    private String unit;
    private String note;


}
