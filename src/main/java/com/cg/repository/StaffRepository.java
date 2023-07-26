package com.cg.repository;

import com.cg.model.Staff;
import com.cg.model.dto.tableOrder.TableOrderReqDTO;
import com.cg.model.dto.tableOrder.TableOrderResDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends JpaRepository<Staff,Long> {

}
