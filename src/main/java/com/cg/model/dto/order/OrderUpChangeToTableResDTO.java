package com.cg.model.dto.order;

import com.cg.model.dto.tableOrder.TableOrderResDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderUpChangeToTableResDTO {
    private TableOrderResDTO tableSend;     // EMPTY
    private TableOrderResDTO tableReceive;  // BUSY

}
