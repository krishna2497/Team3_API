package com.sdpm.team3.model;

import jakarta.persistence.*;

@Entity
@Table(name = "ServiceEmployee") // Ensure the table name matches your actual database table name
public class ServiceEmployee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "EmployeeName")
    private String employeeName;

    @Column(name = "Employee_type")
    private String employeeType;

    @Column(name = "PhoneNumber")
    private String phoneNumber;

    @Column(name = "Vendor_Id")
    private Integer vendorId; // Assuming it's a foreign key referencing a Vendor entity

    // Constructors
    public ServiceEmployee() {
    }

    public ServiceEmployee(Integer id, String employeeName, String employeeType, String phoneNumber, Integer vendorId) {
        this.id = id;
        this.employeeName = employeeName;
        this.employeeType = employeeType;
        this.phoneNumber = phoneNumber;
        this.vendorId = vendorId;
    }

    // Getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(String employeeType) {
        this.employeeType = employeeType;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getVendorId() {
        return vendorId;
    }

    public void setVendorId(Integer vendorId) {
        this.vendorId = vendorId;
    }

    // toString, hashCode, equals, and other methods can be added as needed
}
