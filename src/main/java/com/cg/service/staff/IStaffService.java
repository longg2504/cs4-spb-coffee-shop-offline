package com.cg.service.staff;

import com.cg.model.Staff;
import com.cg.model.User;
import com.cg.service.IGeneralService;

import java.util.Optional;

public interface IStaffService extends IGeneralService<Staff,Long> {
    Optional<Staff> findByUserAndDeletedIsFalse(User user);
}
