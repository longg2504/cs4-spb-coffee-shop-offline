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
public class OrderUpChangeToTableReqDTO implements Validator {
    private String tableIdEmpty;
    private String tableIdBusy;

    @Override
    public boolean supports(Class<?> clazz) {
        return OrderUpChangeToTableReqDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        OrderUpChangeToTableReqDTO orderUpChangeToTableReqDTO = (OrderUpChangeToTableReqDTO) target;

        String tableIdEmptyStr = orderUpChangeToTableReqDTO.tableIdEmpty;
        String tableIdBusyStr = orderUpChangeToTableReqDTO.tableIdBusy;

        if (tableIdBusyStr.equals(tableIdEmptyStr)){
            errors.rejectValue("tableIdEmpty", "tableIdEmpty.coincide", "Bàn chuyển không được trùng với bàn nhận");
            return;
        }

        if (tableIdEmptyStr.isEmpty()) {
            errors.rejectValue("tableIdEmpty", "tableIdEmpty.null", "Mã bàn không được bỏ trống");
        }
        if (!tableIdEmptyStr.matches("^-?\\d+$")) {
            errors.rejectValue("tableIdEmpty", "tableIdEmpty.string", "Mã bàn không được nhập chữ");
        } else {
            Long tableIdEmpty = Long.parseLong(tableIdEmptyStr);
            if (tableIdEmpty <= 0) {
                errors.rejectValue("tableIdEmpty", "tableIdEmpty.minus", "Mã bàn không được nhỏ hơn bằng 0");
            }
        }

        if (tableIdBusyStr.isEmpty()) {
            errors.rejectValue("tableIdBusy", "tableIdBusy.null", "Mã bàn không được bỏ trống");
        }
        if (!tableIdBusyStr.matches("^-?\\d+$")) {
            errors.rejectValue("tableIdBusy", "tableIdBusy.string", "Mã bàn không được nhập chữ");
        } else {
            Long tableIdBusy = Long.parseLong(tableIdBusyStr);
            if (tableIdBusy <= 0) {
                errors.rejectValue("tableIdBusy", "tableIdBusy.minus", "Mã bàn không được nhỏ hơn bằng 0");
            }
        }

    }
}
