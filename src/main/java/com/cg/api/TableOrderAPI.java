package com.cg.api;

import com.cg.model.TableOrder;
import com.cg.model.dto.tableOrder.TableOrderDTO;
import com.cg.service.tableOrder.ITableOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tableOrders")
public class TableOrderAPI {

    @Autowired
    private ITableOrderService tableOrderService;
    @GetMapping
    public ResponseEntity<?> getAllTableOrder(){
        List<TableOrderDTO> tableOrderDTO = tableOrderService.findAllTableOrderDTO();

        return new ResponseEntity<>(tableOrderDTO,HttpStatus.OK);
    }

}
