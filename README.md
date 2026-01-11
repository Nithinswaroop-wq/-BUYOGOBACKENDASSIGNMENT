Buyogo Backend Assignment
1. Architecture

This project is a Spring Boot backend application that processes machine events in batches and provides statistics APIs.

Main layers:

Controller layer: Handles HTTP requests.

Service layer: Contains business logic.

DTO layer: Holds request and response objects.

In-memory store: Uses ConcurrentHashMap to store processed events.

Flow:
Client → Controller → Service → In-memory store → Response

2. Dedupe / Update Logic

Each event has an eventId.

Logic:

If eventId is new
→ Accept and store the event.

If eventId already exists:

If payload is exactly the same
→ Treat as duplicate (dedupe).

If payload is different
→ Treat as update and replace old record.

Payload comparison includes:

eventTime

machineId

durationMs

defectCount

Winning record:

The latest received event always overwrites the old one in case of update.

3. Thread Safety

Thread safety is achieved using:

ConcurrentHashMap for storing events.

All writes and reads happen through thread-safe collections.

No shared mutable state outside the map.

Why this is safe:

Multiple requests can update the map at the same time.

ConcurrentHashMap prevents race conditions.

Counters are maintained per request, so no global counter conflicts.

4. Data Model

In-memory structure:

ConcurrentHashMap<String, Eventsdto>
Key: eventId
Value: full event object

Main DTOs:

Eventsdto → incoming event data

Responsedto → batch processing result

Filtersdto → stats query input

filtersResponsedto → stats response

Topdefectsdto → top defect lines output

No database is used. All data is kept in memory.

5. Performance Strategy

To process 1000 events in under 1 second:

Used in-memory storage instead of database.

Used simple loops instead of heavy streams.

Used ConcurrentHashMap for O(1) access.

Validation and dedupe happen in a single pass.

No external API calls during processing.

This keeps latency very low.

6. Edge Cases and Assumptions

Handled cases:

durationMs < 0 or > 6 hours → rejected.

eventTime more than 15 minutes in future → rejected.

defectCount = -1 → stored but ignored in defect calculations.

Duplicate events → deduped.

Same eventId with new payload → updated.

Assumptions:

Data is small enough to fit in memory.

Application restarts will clear data.

EventId uniquely identifies an event.

System time is trusted for validation.

7. Setup and Run Instructions

Requirements:

Java 17+

Maven

IntelliJ IDEA (recommended)

Steps:

Clone the repository.

Open project in IntelliJ.

Run BuyogoBackendAssignmentApplication.

Application starts at:
http://localhost:8080

Main APIs:

POST /events/batch

POST /events/stats

POST /events/topdefectlines

8. What I Would Improve With More Time

Add database support (PostgreSQL).

Add unit tests and integration tests.

Add proper logging.

Add Swagger for API documentation.

Add pagination for large datasets.

Improve error handling and validation messages.

Add caching layer for heavy stats queries.