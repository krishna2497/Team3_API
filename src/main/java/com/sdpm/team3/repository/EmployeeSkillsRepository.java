package com.sdpm.team3.repository;

import com.sdpm.team3.model.EmployeeSkills;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeSkillsRepository extends JpaRepository<EmployeeSkills, Integer> {
    // Custom query methods can be added here if necessary
}
