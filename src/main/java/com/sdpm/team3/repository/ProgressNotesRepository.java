package com.sdpm.team3.repository;

import com.sdpm.team3.model.ProgressNotes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProgressNotesRepository extends JpaRepository<ProgressNotes, Integer> {
//    List<ProgressNotes> findByBookingIdIn(List<Integer> bookingIds);



    List<ProgressNotes> findByBookingId(Integer bookingId);
}
