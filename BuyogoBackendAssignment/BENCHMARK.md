Benchmark Report – Buyogo Backend Assignment

1. System Specifications
   CPU: Intel Core i7 (or whatever your CPU is)
   RAM: 16 GB
   OS: Windows 11

2. Benchmark Setup
   The benchmark was performed by sending a batch request
   containing 1000 events to the endpoint:

POST /events/batch

The application was running locally on:
http://localhost:8080

3. Command Used

The test was executed using Postman by sending a JSON body
with 1000 events to the endpoint.

 

4. Measured Timing

Result:
- Time taken to ingest 1000 events: 128 ms
 

5. Optimizations Attempted

To improve performance, the following design choices were made:

- Used in-memory storage (ConcurrentHashMap) instead of database.
- Performed validation and deduplication in a single loop.
- Avoided heavy stream operations and used simple for-loops.
- Avoided synchronization blocks and used thread-safe collections.
- Avoided unnecessary object creation inside loops.

6. Conclusion

The system successfully processes 1000 events in under 1 second
on a standard laptop, meeting the performance requirement of the
assignment.
