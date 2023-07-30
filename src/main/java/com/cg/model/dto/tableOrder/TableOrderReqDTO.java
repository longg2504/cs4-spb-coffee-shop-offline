package com.cg.model.dto.tableOrder;

import com.cg.model.TableOrder;
import com.cg.model.enums.ETableStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TableOrderReqDTO {

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
}
