package com.sdpm.team3.dto;

public class SkillEmployeeDTO {
    public Integer getSkillId() {


        return skillId;
    }

    public void setSkillId(Integer skillId) {
        this.skillId = skillId;
    }

    private Integer skillId;

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    private String skillName;
    private Integer employeeId;
    private String employeeName;


}
