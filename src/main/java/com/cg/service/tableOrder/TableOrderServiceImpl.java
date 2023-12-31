package com.cg.service.tableOrder;

import com.cg.model.TableOrder;
import com.cg.model.dto.tableOrder.TableOrderDTO;
import com.cg.model.dto.tableOrder.TableOrderReqDTO;
import com.cg.model.dto.tableOrder.TableOrderResDTO;
import com.cg.repository.TableOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class TableOrderServiceImpl implements ITableOrderService{
    @Autowired
    private TableOrderRepository tableOrderRepository;
    @Override
    public List<TableOrder> findAll() {
        return tableOrderRepository.findAll();
    }

    @Override
    public Optional<TableOrder> findById(Long id) {
        return tableOrderRepository.findById(id);
    }

    @Override
    public TableOrder save(TableOrder tableOrder) {
        return null;
    }

    @Override
    public void delete(TableOrder tableOrder) {

    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public List<TableOrderDTO> findAllTableOrderDTO() {
        return tableOrderRepository.findAllTableOrderDTO();
    }

    @Override
    public TableOrderResDTO createTableOrder(TableOrderReqDTO tableOrderReqDTO) {
        TableOrder tableOrder = tableOrderReqDTO.toTableOrderReqDTO();
        tableOrderRepository.save(tableOrder);

        TableOrderResDTO tableOrderResDTO = tableOrder.toCreateTableOrderResDTO();

        return tableOrderResDTO;
    }

    @Override
    public TableOrderResDTO updateTableOrder(Long tableOrderId, TableOrderReqDTO tableOrderReqDTO) {
        TableOrder tableOrder = tableOrderReqDTO.toTableOrder(tableOrderId);
        tableOrderRepository.save(tableOrder);
        TableOrderResDTO tableOrderResDTO = tableOrder.toUpdateTableOrderResDTO(tableOrderId);
        return tableOrderResDTO;
    }

    @Override
    public List<TableOrderDTO> findAllTablesWithoutSenderId(Long tableId) {
        return tableOrderRepository.findAllTablesWithoutSenderId(tableId);
    }
}
