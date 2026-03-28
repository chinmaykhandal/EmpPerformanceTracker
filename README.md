# Employee Performance Tracker API

## Requirements

* Java 17+
* Spring Boot 4.0.0
* Maven 3.8+

## Run Locally

```bash
git clone https://github.com/chinmaykhandal/EmpPerformanceTracker.git
cd PerformanceTracker
mvn spring-boot:run
```

App runs at:
`http://localhost:8080`

### Postman Collection (Sample API Requests)

[Performance Tracker APIs.postman_collection.json](https://github.com/user-attachments/files/26323471/Performance.Tracker.APIs.postman_collection.json)

## H2 Console

`http://localhost:8080/h2-console`
Use:

* JDBC URL: `jdbc:h2:mem:testdb`
* Username: `sa`
* Password: *(leave empty)*

### Notes

* Uses in-memory H2 database
* Schema + sample data auto-load from `schema.sql`
* No external setup required

### System Design - Short Write Up

* **500 concurrent managers**

  * Run multiple stateless Spring Boot instances behind a load balancer
  * Tune **HikariCP** for concurrent reads
  * Use DB indexes on:

    * `review_cycle_id`
    * `employee_id`
    * `department`
    * `(review_cycle_id, status)`
  * Use **DB read replicas** in production for report traffic

* **If `/cycles/{id}/summary` slows at 100k+ reviews**

  * Keep aggregation DB-side first
  * Add composite index on `(review_cycle_id, employee_id)`
  * Introduce a precomputed `cycle_summary` table
  * Update it **event-driven on writes**

    * review submitted
    * goal status updated
  * Publish events after write APIs succeed
  * A consumer updates the summary row for that cycle
  * This keeps reads fast and avoids recalculating aggregates repeatedly

* **Caching**

  * Add Redis cache at service layer
  * Cache:

    * `/cycles/{id}/summary`
    * `/employees/{id}/reviews`
    * `/employees?department={dept}&minRating={x}`
  * TTL: **5–15 mins**
  * Invalidate on review/goal write events
 
### Design Decisions and Assumptions

* **Single review per employee per cycle**

  * enforced using unique constraint on `(employee_id, review_cycle_id)`

* **Top performer tie-break**

  * if multiple employees have the same highest average rating, the one returned first by DB sort order is selected
  * can be made deterministic later using secondary sort on `employee_id`

* **Goal status values**

  * only `PENDING`, `COMPLETED`, `MISSED` are allowed
  * stored as enum string

* **Pagination**

  * not added for employee review history assuming manageable review volume per employee


