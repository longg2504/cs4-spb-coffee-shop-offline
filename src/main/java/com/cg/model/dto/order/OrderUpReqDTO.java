package com.cg.model.dto.order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderUpReqDTO implements Validator {
    private String tableId;
    private String quantity;
    private String productId;
    private String note;

    @Override
    public boolean supports(Class<?> clazz) {
        return OrderUpReqDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        OrderUpReqDTO orderUpReqDTO = (OrderUpReqDTO) target;
        String tableIdStr = orderUpReqDTO.tableId;
        String quantityStr = orderUpReqDTO.quantity;
        String productIdStr = orderUpReqDTO.productId;
        String note = orderUpReqDTO.note;

        if (note.length() >= 100){
            errors.rejectValue("note","note.length","Ghi chú không được vượt quá 100 kí tự ");
        }

        if(productIdStr.isEmpty()){
              errors.rejectValue("productId","productId.null","Mã sản phẩm không được bỏ trống");
        }

        if(!productIdStr.matches("^-?\\d+$")){
             errors.rejectValue("productId","productId.string","Mã sản phẩm không được nhập chữ");
        }else {
            Long productId = Long.valueOf(productIdStr);
            if (productId < 0L) {
                errors.rejectValue("productId", "productId.min", "Mã sản phẩm phải lơn hơn 0");
            }
        }

        if (quantityStr.isEmpty()){
            errors.rejectValue("quantity","quantity.null","Số lượng không được bỏ trống");

        }
        if(!quantityStr.matches("^-?\\d+$")){
             errors.rejectValue("quantity","quantity.string","Số lượng không được nhập chữ");
        }else {
            Long quantity = Long.valueOf(quantityStr);
            if (quantity <= 0 || quantity > 30) {
                errors.rejectValue("quantity", "quantity.minAndMax", "Số lượng phải từ 1 đến 30");
            }
        }

        if (tableIdStr.isEmpty()) {
            errors.rejectValue("tableId", "tableId.null", "Mã bàn không được bỏ trống");
        }
        if (!tableIdStr.matches("^-?\\d+$")) {
            errors.rejectValue("tableId", "tableId.string", "Mã bàn không được nhập chữ");
        } else {
            Long tableId = Long.parseLong(tableIdStr);
            if (tableId <= 0) {
                errors.rejectValue("tableId", "tableId.minus", "Mã bàn không được nhỏ hơn bằng 0");
            }
        }
    }
}
