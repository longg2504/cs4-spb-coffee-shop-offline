package com.cg.api;

import com.cg.model.dto.bill.BillDTO;
import com.cg.model.dto.bill.BillReqDTO;
import com.cg.model.dto.bill.BillResDTO;
import com.cg.model.dto.order.OrderDTO;
import com.cg.service.bill.IBillService;
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
@RequestMapping("/api/bills")
public class BillAPI {

    @Autowired
    private IBillService billService;

     @Autowired
    private ValidateUtils validateUtils;

    @Autowired
    private AppUtils appUtils;


    @PostMapping("/{tableId}")
    public ResponseEntity<?> payment(@PathVariable("tableId") String tableIdStr){

        if (!validateUtils.isNumberValid(tableIdStr)) {
            Map<String, String> data = new HashMap<>();
            data.put("message", "Mã bàn không hợp lệ");
            return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
        }

        Long tableId = Long.parseLong(tableIdStr);

        BillResDTO billResDTO = billService.createBill(tableId);

        return new ResponseEntity<>(billResDTO,HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<?>> getFindALlBill(){

        try{
            List<BillDTO> listBill = billService.findAllBillDTO();


            if (listBill.isEmpty()){
                 return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(listBill, HttpStatus.OK);

        }catch (Exception e){
             return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
