package com.cg.model.dto.order;

import com.cg.model.ProductAvatar;
import com.cg.model.dto.productAvatar.ProductAvatarResDTO;
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
public class OrderDetailProductUpResDTO {

    private Long id;
    private Long productId;
    private String title;
    private BigDecimal price;
    private Long quantity;
    private BigDecimal amount;
    private String note;
    private ProductAvatarResDTO avatar;

    public OrderDetailProductUpResDTO(Long id, Long productId, String title, BigDecimal price, Long quantity, BigDecimal amount, String note, ProductAvatar avatar) {
        this.id = id;
        this.productId = productId;
        this.title = title;
        this.price = price;
        this.quantity = quantity;
        this.amount = amount;
        this.note = note;
        this.avatar = avatar.toProductAvatarDTO();
    }
}
