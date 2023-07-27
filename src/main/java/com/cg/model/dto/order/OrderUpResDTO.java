package com.cg.model.dto.order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class OrderUpResDTO {
    private Long tableId;
    private Integer quantity;
    private Long productId;
    private String note;
    private String userName;
}
