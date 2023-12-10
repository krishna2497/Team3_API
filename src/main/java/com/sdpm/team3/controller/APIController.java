package com.sdpm.team3.controller;

import com.sdpm.team3.model.*;
import com.sdpm.team3.repository.*;
import com.sdpm.team3.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class APIController {

    private final VendorRepository vendorRepository;
    private final SkillRepository skillRepository;
    private final ServicesRepository servicesRepository;
    private final BookingRepository bookingRepository;
    private final ProgressNotesRepository progressNotesRepository;
    private final BookingService bookingService;
    private final EmployeeSkillsRepository employeeSkillsRepository;
    private final ServiceEmployeeRepository serviceEmployeeRepository;

    @Autowired
    public APIController(VendorRepository vendorRepository, SkillRepository skillRepository, ServicesRepository servicesRepository, BookingRepository bookingRepository, ProgressNotesRepository progressNotesRepository, BookingService bookingService, EmployeeSkillsRepository employeeSkillsRepository, ServiceEmployeeRepository serviceEmployeeRepository) {
        this.vendorRepository = vendorRepository;
        this.skillRepository = skillRepository;
        this.servicesRepository = servicesRepository;
        this.bookingRepository = bookingRepository;
        this.progressNotesRepository = progressNotesRepository;
        this.bookingService = bookingService;
        this.employeeSkillsRepository = employeeSkillsRepository;
        this.serviceEmployeeRepository = serviceEmployeeRepository;
    }

    @GetMapping("/vendors")
    public ResponseEntity<List<Vendor>> getAllVendors() {
        List<Vendor> vendors = vendorRepository.findAll();
        return ResponseEntity.ok(vendors);
    }

    @GetMapping("/skills")
    public ResponseEntity<List<Skills>> getAllSkills() {
        List<Skills> skills = skillRepository.findAll();
        return ResponseEntity.ok(skills);
    }

    @GetMapping("/services")
    public ResponseEntity<List<Services>> getAllServices() {
        List<Services> services = servicesRepository.findAll();
        return ResponseEntity.ok(services);
    }

    @GetMapping("/bookings")
    public ResponseEntity<List<Booking>> getAllBookings() {
        List<Booking> bookings = bookingRepository.findAll();
        return ResponseEntity.ok(bookings);
    }

    @GetMapping("/employee_skills")
    public ResponseEntity<List<EmployeeSkills>> getAllEmployeeSkills() {
        List<EmployeeSkills> employeeSkills = employeeSkillsRepository.findAll();
        return ResponseEntity.ok(employeeSkills);
    }

    @GetMapping("/service_employees")
    public ResponseEntity<List<ServiceEmployee>> getAllServiceEmployees() {
        List<ServiceEmployee> serviceEmployees = serviceEmployeeRepository.findAll();
        return ResponseEntity.ok(serviceEmployees);
    }

    @GetMapping("/progress-notes/patient/{patientId}")
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

    @GetMapping("/booking/details")
    public ResponseEntity<List<BookingDetailsWithCount>> getBookingDetailsWithCounts() {
        List<BookingDetailsWithCount> bookingDetailsWithCounts = bookingService.getBookingDetailsWithCounts();
        return ResponseEntity.ok(bookingDetailsWithCounts);
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
        Services savedService = servicesRepository.save(service);
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

    //    @GetMapping("/booking/{bookingId}")// (internal api)
//    public ResponseEntity<List<ProgressNotes>> getProgressNotesByBookingId(@PathVariable Integer bookingId) {
//        List<ProgressNotes> progressNotes = progressNotesRepository.findByBookingId(bookingId);
//        return ResponseEntity.ok(progressNotes);
//    }

}
