package com.sdpm.team3.repository;

import com.sdpm.team3.model.ServiceEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceEmployeeRepository extends JpaRepository<ServiceEmployee, Integer> {
    // You can define custom queries here if needed, for example:
    List<ServiceEmployee> findByEmployeeIdIn(List<Integer> employeeIds);
}
