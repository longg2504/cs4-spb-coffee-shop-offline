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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    @GetMapping("/{tableId}")
    public ResponseEntity<?> getById(@PathVariable("tableId") String tableIdStr){

        if (!validateUtils.isNumberValid(tableIdStr)) {
            throw new DataInputException("Mã bàn không hợp lệ");
        }
        Long tableId = Long.parseLong(tableIdStr);

        Optional<TableOrder> tableOrderOptional = tableOrderService.findById(tableId);

        if(tableOrderOptional.isEmpty()){
            Map<String,String> data = new HashMap<>();
            data.put("message" , "Bàn không tồn tại");
            return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(tableOrderOptional.get(), HttpStatus.OK);
    }

    @GetMapping("/tables-without-tableSend/{tableId}")
    public ResponseEntity<?> getAllTablesWithoutSender(@PathVariable("tableId") String tableIdStr) {

        if (!validateUtils.isNumberValid(tableIdStr)) {
            throw new DataInputException("Mã bàn không hợp lệ");
        }
        Long tableId = Long.parseLong(tableIdStr);

        List<TableOrderDTO> tableSelect = tableOrderService.findAllTablesWithoutSenderId(tableId);

        return new ResponseEntity<>(tableSelect, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createTable(@RequestBody TableOrderReqDTO tableOrderReqDTO, BindingResult bindingResult){

        new TableOrderReqDTO().validate(tableOrderReqDTO,bindingResult);
        if (bindingResult.hasFieldErrors()){
            return appUtils.mapErrorToResponse(bindingResult);
        }

        TableOrderResDTO tableOrderResDTO = tableOrderService.createTableOrder(tableOrderReqDTO);

        return new ResponseEntity<>(tableOrderResDTO,HttpStatus.CREATED);
    }

    @PatchMapping("/{orderTableId}")
    public ResponseEntity<?> update(@PathVariable("orderTableId") String orderTableIdStr,@RequestBody TableOrderReqDTO tableOrderReqDTO, BindingResult bindingResult){

        new TableOrderReqDTO().validate(tableOrderReqDTO,bindingResult);
        if (bindingResult.hasFieldErrors()){
            return appUtils.mapErrorToResponse(bindingResult);
        }

        if (!validateUtils.isNumberValid(orderTableIdStr)) {
            Map<String, String> data = new HashMap<>();
            data.put("message", "Mã bàn không hợp lệ");
            return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
        }
        Long orderTableId = Long.parseLong(orderTableIdStr);
        tableOrderService.findById(orderTableId).orElseThrow(() -> {
           throw new DataInputException("Mã bàn không tồn tại");
        });

        TableOrderResDTO tableOrderResDTO = tableOrderService.updateTableOrder(orderTableId,tableOrderReqDTO);
        return new ResponseEntity<>(tableOrderResDTO,HttpStatus.OK);
    }


}
