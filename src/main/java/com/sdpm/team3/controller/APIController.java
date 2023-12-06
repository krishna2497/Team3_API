package com.sdpm.team3.controller;

import com.sdpm.team3.model.ProgressNotes;
import com.sdpm.team3.model.Services;
import com.sdpm.team3.model.Skills;
import com.sdpm.team3.model.Vendor;
import com.sdpm.team3.model.Booking;
import com.sdpm.team3.repository.*;
import com.sdpm.team3.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api") // Base path for all endpoints in this controller
public class APIController {

    private final VendorRepository vendorRepository;



    private final SkillRepository skillRepository;

    private  final ServicesRepository servicesRepository;
    private final BookingRepository bookingRepository;
    private final ProgressNotesRepository progressNotesRepository;

    private final BookingService bookingService;




    // Constructor injection is recommended for mandatory dependencies
    @Autowired
    public APIController(VendorRepository vendorRepository, SkillRepository skillRepository, ServicesRepository servicesRepository, BookingRepository bookingRepository, ProgressNotesRepository progressNotesRepository, BookingService bookingService) {
        this.vendorRepository = vendorRepository;

        this.skillRepository = skillRepository;

        this.servicesRepository = servicesRepository;
        this.bookingRepository = bookingRepository;
        this.progressNotesRepository = progressNotesRepository;
        this.bookingService = bookingService;
    }


    // Get a vendor by ID (test )
    @GetMapping("/vendors/{id}")
    public ResponseEntity<Vendor> getVendorById(@PathVariable int id) {
        return vendorRepository.findById(id)
                .map(ResponseEntity::ok) // If the vendor is found, return 200 OK with the vendor
                .orElseGet(() -> ResponseEntity.notFound().build()); // If not found, return 404 Not Found
    }


    @GetMapping("/skills/{id}") //(test )
    public ResponseEntity<Skills> getSkillById(@PathVariable int id) {
        return skillRepository.findById(id)
                .map(ResponseEntity::ok) // If the skill is found, return 200 OK with the skill
                .orElseGet(() -> ResponseEntity.notFound().build()); // If not found, return 404 Not Found
    }


//API for team 2
   @GetMapping("/servicesOffered")  // (exposed api also sued as internal api )
    public ResponseEntity<List<Services>> getAllServices() {
        List<Services> services = servicesRepository.findAll();
        return ResponseEntity.ok(services); // Return the list of services
    }


    @PostMapping("/services")
    public ResponseEntity<Services> addService(@RequestBody Services service) {
        // Save the received service object using the repository
        Services savedService = servicesRepository.save(service);

        // Return the saved service object with a 201 (Created) status
        return new ResponseEntity<>(savedService, HttpStatus.CREATED);
    }



    @GetMapping("/progress-notes/patient/{patientId}")//(exposed api)
    public ResponseEntity<List<ProgressNotes>> getProgressNotesByPatientId(@PathVariable Integer patientId) {
        List<Integer> bookingIds = bookingRepository.findByPatientId(patientId)
                .stream()
                .map(Booking::getBookingId)
                .collect(Collectors.toList());

        if (bookingIds.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<ProgressNotes> progressNotes = progressNotesRepository.findByBookingIdIn(bookingIds);
        return ResponseEntity.ok(progressNotes);
    }


    @GetMapping("/booking/details") //(Internal APi)
    public ResponseEntity<List<BookingDetailsWithCount>> getBookingDetailsWithCounts() {
        List<BookingDetailsWithCount> bookingDetailsWithCounts = bookingService.getBookingDetailsWithCounts();
        return ResponseEntity.ok(bookingDetailsWithCounts);
    }


    @GetMapping("/booking/{bookingId}")// (internal api)
    public ResponseEntity<List<ProgressNotes>> getProgressNotesByBookingId(@PathVariable Integer bookingId) {
        List<ProgressNotes> progressNotes = progressNotesRepository.findByBookingId(bookingId);
        return ResponseEntity.ok(progressNotes);
    }

    @PostMapping("/progress_notes/add")
    public ResponseEntity<ProgressNotes> addProgressNote(@RequestBody ProgressNotes progressNotes) {
        ProgressNotes savedProgressNote = progressNotesRepository.save(progressNotes);
        return new ResponseEntity<>(savedProgressNote, HttpStatus.CREATED);
    }




}
