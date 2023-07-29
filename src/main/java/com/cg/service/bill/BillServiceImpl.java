package com.cg.service.bill;

import com.cg.exception.DataInputException;
import com.cg.model.Bill;
import com.cg.model.Order;
import com.cg.model.TableOrder;
import com.cg.model.dto.bill.BillDTO;
import com.cg.model.dto.bill.BillResDTO;
import com.cg.model.enums.ETableStatus;
import com.cg.repository.BillRepository;
import com.cg.repository.OrderRepository;
import com.cg.repository.TableOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
//@Transactional
public class BillServiceImpl implements IBillService {

    @Autowired
    private BillRepository billRepository;
    @Autowired
    private TableOrderRepository tableOrderRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Override
    public List<Bill> findAll() {
        return null;
    }

    @Override
    public Optional<Bill> findById(Long id) {
        return billRepository.findById(id);
    }

    @Override
    public Bill save(Bill bill) {
        return billRepository.save(bill);
    }

    @Override
    public void delete(Bill bill) {

    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public BillResDTO createBill(Long tableId) {

        Order order = orderRepository.findByTableId(tableId).get();
        if (order.getPaid() == true) {
            throw new DataInputException("Bàn này đã thanh toán");
        }
        order.setPaid(true);
        TableOrder tableOrder = tableOrderRepository.findById(tableId).get();
        if (tableOrder.getStatus() == ETableStatus.EMPTY) {
            throw new DataInputException("Bàn không đặt không thể thanh toán");
        }
        tableOrder.setStatus(ETableStatus.EMPTY);

        Bill bill = new Bill();
        bill.setTotalAmount(order.getTotalAmount());
        bill.setOrder(order);
        billRepository.save(bill);
        BillResDTO billResDTO = bill.toBillResDTO();
        return billResDTO;
    }

    @Override
    public List<BillDTO> findAllBillDTO() {
        return billRepository.findAllBillDTO();
    }

//    @Override
//    public List<OrderDTO> findBillOrderDTO(Long orderId) {
//        return billRepository.findBillOrderDTO(orderId);
//    }

}
