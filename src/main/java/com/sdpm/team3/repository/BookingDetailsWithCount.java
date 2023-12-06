package com.sdpm.team3.repository;

import com.sdpm.team3.model.Booking;

import java.util.List;

public class BookingDetailsWithCount {
    private List<Booking> bookings;
    private Long frequency;
    private Integer serviceId;
    private Integer patientId;

    public BookingDetailsWithCount(List<Booking> bookings, Long frequency, Integer serviceId, Integer patientId) {
        this.bookings = bookings;
        this.frequency = frequency;
        this.serviceId = serviceId;
        this.patientId = patientId;
    }

    // Getter for the bookings list
    public List<Booking> getBookings() {
        return bookings;
    }

    // Setter for the bookings list
    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    // Getter for the count
    public Long getFrequency() {
        return frequency;
    }

    // Setter for the count
    public void setFrequency(Long frequency) {
        this.frequency = frequency;
    }

    // Getter for the serviceId
    public Integer getServiceId() {
        return serviceId;
    }

    // Setter for the serviceId
    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    // Getter for the patientId
    public Integer getPatientId() {
        return patientId;
    }

    // Setter for the patientId
    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }
}

