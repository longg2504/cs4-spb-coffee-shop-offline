package com.cg.service.tableOrder;

import com.cg.model.TableOrder;
import com.cg.model.dto.tableOrder.TableOrderDTO;
import com.cg.model.dto.tableOrder.TableOrderReqDTO;
import com.cg.model.dto.tableOrder.TableOrderResDTO;
import com.cg.service.IGeneralService;

import java.util.List;

public interface ITableOrderService extends IGeneralService<TableOrder,Long> {
    List<TableOrderDTO> findAllTableOrderDTO();
    TableOrderResDTO createTableOrder(TableOrderReqDTO tableOrderReqDTO);
}
