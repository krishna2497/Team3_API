package com.sdpm.team3.controller;

import com.sdpm.team3.model.*;
import com.sdpm.team3.repository.*;
import com.sdpm.team3.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
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

    private  final EmployeeSkillsRepository employeeSkillsRepository;

    private  final ServiceEmployeeRepository serviceEmployeeRepository;






    // Constructor injection is recommended for mandatory dependencies
    @Autowired
    public APIController(VendorRepository vendorRepository, SkillRepository skillRepository, ServicesRepository servicesRepository, BookingRepository bookingRepository, ProgressNotesRepository progressNotesRepository, BookingService bookingService, EmployeeSkillsRepository employeeSkillsRepository1, ServiceEmployeeRepository serviceEmployeeRepository) {
        this.vendorRepository = vendorRepository;

        this.skillRepository = skillRepository;

        this.servicesRepository = servicesRepository;
        this.bookingRepository = bookingRepository;
        this.progressNotesRepository = progressNotesRepository;
        this.bookingService = bookingService;
        this.employeeSkillsRepository = employeeSkillsRepository1;
        this.serviceEmployeeRepository = serviceEmployeeRepository;
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


//    @GetMapping("/booking/{bookingId}")// (internal api)
//    public ResponseEntity<List<ProgressNotes>> getProgressNotesByBookingId(@PathVariable Integer bookingId) {
//        List<ProgressNotes> progressNotes = progressNotesRepository.findByBookingId(bookingId);
//        return ResponseEntity.ok(progressNotes);
//    }


    @GetMapping("/bookings/all")
    public ResponseEntity<List<Booking>> getAllBookings() {
        List<Booking> bookings = bookingRepository.findAll(); // Assuming you have a booking repository
        return ResponseEntity.ok(bookings);
    }



    @PutMapping("/bookings/edit/{id}")
    public ResponseEntity<Booking> updateBooking(@PathVariable Integer id, @RequestBody Booking updatedBooking) {
        // Check if the booking with the given ID exists in the database
        Optional<Booking> existingBookingOptional = bookingRepository.findById(id);

        if (existingBookingOptional.isPresent()) {
            // The booking exists, so we can update it
            Booking existingBooking = existingBookingOptional.get();

            // Update the fields of the existing booking with the values from the updatedBooking object
            // You can add validation and logic here to ensure that only valid updates are performed
            existingBooking.setServiceId(updatedBooking.getServiceId());
            existingBooking.setEmployeeId(updatedBooking.getEmployeeId());
            existingBooking.setStartTime(updatedBooking.getStartTime());
            existingBooking.setEndTime(updatedBooking.getEndTime());
            existingBooking.setPatientId(updatedBooking.getPatientId());
            existingBooking.setStatus(updatedBooking.getStatus());
            existingBooking.setRemarks(updatedBooking.getRemarks());

            // Save the updated booking to the database
            Booking updated = bookingRepository.save(existingBooking);

            return ResponseEntity.ok(updated);
        } else {
            // The booking with the given ID was not found
            return ResponseEntity.notFound().build();
        }
    }




    @PostMapping("/bookings/add")
    public ResponseEntity<Booking> addBooking(@RequestBody Booking booking) {
        Booking savedBooking = bookingRepository.save(booking);
        return new ResponseEntity<>(savedBooking, HttpStatus.CREATED);
    }


    @PostMapping("/employee_skills/add")
    public ResponseEntity<EmployeeSkills> addEmployeeSkill(@RequestBody EmployeeSkills employeeSkills) {
        EmployeeSkills savedEmployeeSkill = employeeSkillsRepository.save(employeeSkills);
        return new ResponseEntity<>(savedEmployeeSkill, HttpStatus.CREATED);
    }
    @PostMapping("/progress_notes/add")
    public ResponseEntity<ProgressNotes> addProgressNote(@RequestBody ProgressNotes progressNotes) {
        ProgressNotes savedProgressNote = progressNotesRepository.save(progressNotes);
        return new ResponseEntity<>(savedProgressNote, HttpStatus.CREATED);
    }



    @PostMapping("/service_employees/add")
    public ResponseEntity<ServiceEmployee> addServiceEmployee(@RequestBody ServiceEmployee serviceEmployee) {
        ServiceEmployee savedServiceEmployee = serviceEmployeeRepository.save(serviceEmployee);
        return new ResponseEntity<>(savedServiceEmployee, HttpStatus.CREATED);
    }


    @PostMapping("/services")
    public ResponseEntity<Services> addService(@RequestBody Services service) {
        // Save the received service object using the repository
        Services savedService = servicesRepository.save(service);

        // Return the saved service object with a 201 (Created) status
        return new ResponseEntity<>(savedService, HttpStatus.CREATED);
    }

    @PostMapping("/skills/add")
    public ResponseEntity<Skills> addSkill(@RequestBody Skills skill) {
        Skills savedSkill = skillRepository.save(skill);
        return new ResponseEntity<>(savedSkill, HttpStatus.CREATED);
    }

    @PostMapping("/vendors/add")
    public ResponseEntity<Vendor> addVendor(@RequestBody Vendor vendor) {
        Vendor savedVendor = vendorRepository.save(vendor);
        return new ResponseEntity<>(savedVendor, HttpStatus.CREATED);
    }









}
