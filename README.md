# API Endpoints

## Vendors

- **GET /api/vendors/{id}**
  - Retrieves a vendor by ID.

## Skills

- **GET /api/skills/{id}**
  - Retrieves a skill by ID.

## Services

- **GET /api/servicesOffered**
  - Retrieves a list of all services.

- **POST /api/services**
  - Adds a new service.

## Booking

- **GET /api/booking/details**
  - Retrieves booking details with counts.

- **POST /api/bookings/add**
  - Adds a new booking.

## Employee Skills

- **POST /api/employee_skills/add**
  - Adds a new employee skill.

## Service Employee

- **POST /api/service_employees/add**
  - Adds a new service employee.

## Progress Notes

- **GET /api/progress-notes/patient/{patientId}**
  - Retrieves progress notes by patient ID.

- **POST /api/progress_notes/add**
  - Adds a new progress note.

## Other Endpoints (Commented Out)

- **GET /api/booking/{bookingId}**
  - Retrieves progress notes by booking ID (currently commented).

## Additional Notes

- The base path for all endpoints: `/api`

---

**Note:** This documentation provides a summary of the endpoints in the `APIController` class. Ensure that the actual implementation and usage are consistent with this documentation.
