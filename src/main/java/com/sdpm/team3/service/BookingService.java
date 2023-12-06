package com.sdpm.team3.service;


import com.sdpm.team3.model.Booking;
import com.sdpm.team3.repository.BookingDetailsWithCount;
import com.sdpm.team3.repository.BookingRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public List<BookingDetailsWithCount> getBookingDetailsWithCounts() {
        // Get the counts grouped by serviceId and patientId
        List<Object[]> counts = bookingRepository.findBookingCountsGroupedByServiceAndPatient();

        // Create a list to hold the final results
        List<BookingDetailsWithCount> bookingDetailsWithCounts = new ArrayList<>();

        // For each serviceId and patientId, fetch the bookings and attach the count
        for (Object[] countData : counts) {
            Integer serviceId = (Integer) countData[0];
            Integer patientId = (Integer) countData[1];
            Long count = (Long) countData[2];

            // Fetch the bookings for each serviceId and patientId
            List<Booking> bookings = bookingRepository.findByServiceIdAndPatientId(serviceId, patientId);

            // Create a new BookingDetailsWithCount object and add it to the results list
            bookingDetailsWithCounts.add(new BookingDetailsWithCount(bookings, count, serviceId, patientId));
        }

        return bookingDetailsWithCounts;
    }
}
