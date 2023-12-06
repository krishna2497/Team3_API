package com.sdpm.team3.model;

import jakarta.persistence.*;

@Entity
public class EmployeeSkills {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "employee_id")
    private Integer employeeId;

    @Column(name = "skill_id")
    private Integer skillId;

    // Constructors
    public EmployeeSkills() {
    }

    public EmployeeSkills(Integer employeeId, Integer skillId) {
        this.employeeId = employeeId;
        this.skillId = skillId;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getSkillId() {
        return skillId;
    }

    public void setSkillId(Integer skillId) {
        this.skillId = skillId;
    }

    // toString, equals, hashCode, etc. can be added as needed
}
