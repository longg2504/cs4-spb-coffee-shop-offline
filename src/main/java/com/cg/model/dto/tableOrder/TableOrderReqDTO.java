package com.cg.model.dto.tableOrder;

import com.cg.model.TableOrder;
import com.cg.model.enums.ETableStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TableOrderReqDTO implements Validator {

    private String title;
    private ETableStatus status;


    public TableOrder toTableOrderReqDTO() {
        return new TableOrder()
                .setTitle(title)
                .setStatus(ETableStatus.EMPTY);
    }

    public TableOrder toTableOrder(Long tableOrderId) {
        return new TableOrder()
                .setId(tableOrderId)
                .setTitle(title)
                .setStatus(ETableStatus.EMPTY)
                ;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return TableOrderReqDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        TableOrderReqDTO tableOrderReqDTO = (TableOrderReqDTO) target;

        String title = tableOrderReqDTO.title;

        if (title.length() > 30) {
            errors.rejectValue("title", "title.length", "Tên bàn không vượt quá 30 kí tự ");
        }
    }
}
