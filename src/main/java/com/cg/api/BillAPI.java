package com.cg.api;

import com.cg.model.dto.bill.BillDTO;
import com.cg.model.dto.bill.BillReqDTO;
import com.cg.model.dto.bill.BillResDTO;
import com.cg.model.dto.order.OrderDTO;
import com.cg.service.bill.IBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bills")
public class BillAPI {

    @Autowired
    private IBillService billService;


    @PostMapping("/{tableId}")
    public ResponseEntity<?> payment(@PathVariable Long tableId){

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
//    @GetMapping("/{orderId}")
//    public ResponseEntity<?> listBillOrderById(@PathVariable Long orderId){
//
//        try{
//          List<OrderDTO> list = billService.findBillOrderDTO(orderId);
//             return new ResponseEntity<>(HttpStatus.OK);
//        }catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//    }
}
