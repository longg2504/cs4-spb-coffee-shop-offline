package com.cg.service.staff;

import com.cg.model.Staff;
import com.cg.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class StaffServiceImpl implements IStaffService{

    @Autowired
    private StaffRepository staffRepository;
    @Override
    public List<Staff> findAll() {
        return null;
    }

    @Override
    public Optional<Staff> findById(Long id) {
        return staffRepository.findById(id) ;
    }

    @Override
    public Staff save(Staff staff) {
        return null;
    }

    @Override
    public void delete(Staff staff) {

    }

    @Override
    public void deleteById(Long id) {

    }
}
