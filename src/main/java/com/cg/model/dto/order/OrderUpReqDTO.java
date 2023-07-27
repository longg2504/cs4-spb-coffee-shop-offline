package com.cg.model.dto.order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderUpReqDTO {
    private Long tableId;
    private Integer quantity;
    private Long productId;
    private String note;

}
