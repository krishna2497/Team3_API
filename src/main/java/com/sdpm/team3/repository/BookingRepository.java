package com.sdpm.team3.repository;

import com.sdpm.team3.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookingRepository  extends JpaRepository<Booking, Integer> {
    List<Booking> findByPatientId(Integer patientId);


    @Query("SELECT b.serviceId, b.patientId, COUNT(b.bookingId) FROM Booking  b GROUP BY b.serviceId, b.patientId")
    List<Object[]> findBookingCountsGroupedByServiceAndPatient();

    List<Booking> findByServiceIdAndPatientId(Integer serviceId, Integer patientId);
}
