package com.cg.api;

import com.cg.exception.DataInputException;
import com.cg.model.TableOrder;
import com.cg.model.dto.tableOrder.TableOrderDTO;
import com.cg.model.dto.tableOrder.TableOrderReqDTO;
import com.cg.model.dto.tableOrder.TableOrderResDTO;
import com.cg.service.tableOrder.ITableOrderService;
import com.cg.utils.AppUtils;
import com.cg.utils.ValidateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/tableOrders")
public class TableOrderAPI {

    @Autowired
    private ITableOrderService tableOrderService;

    @Autowired
    private ValidateUtils validateUtils;

    @Autowired
    private AppUtils appUtils;
    @GetMapping
    public ResponseEntity<?> getAllTableOrder(){
        List<TableOrderDTO> tableOrderDTO = tableOrderService.findAllTableOrderDTO();

        return new ResponseEntity<>(tableOrderDTO,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createTable(@RequestBody TableOrderReqDTO tableOrderReqDTO){

        TableOrderResDTO tableOrderResDTO = tableOrderService.createTableOrder(tableOrderReqDTO);

        return new ResponseEntity<>(tableOrderResDTO,HttpStatus.CREATED);
    }

    @PatchMapping("/{orderTableId}")
    public ResponseEntity<?> update(@PathVariable("orderTableId") String orderTableIdStr,@RequestBody TableOrderReqDTO tableOrderReqDTO){
        if (!validateUtils.isNumberValid(orderTableIdStr)) {
            Map<String, String> data = new HashMap<>();
            data.put("message", "Mã danh mục không hợp lệ");
            return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
        }
        Long orderTableId = Long.parseLong(orderTableIdStr);
        tableOrderService.findById(orderTableId).orElseThrow(() -> {
           throw new DataInputException("Mã danh mục không tồn tại");
        });

        TableOrderResDTO tableOrderResDTO = tableOrderService.updateTableOrder(orderTableId,tableOrderReqDTO);
        return new ResponseEntity<>(tableOrderResDTO,HttpStatus.OK);
    }
}
