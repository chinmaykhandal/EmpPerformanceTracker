# Employee Performance Tracker API


### Run Locally
Right click on PerformanceTrackerApplication.java file and run or
```bash
mvn spring-boot:run
```

App runs at:
`http://localhost:8080`

### H2 Console

`http://localhost:8080/h2-console`

Use:

* JDBC URL: `jdbc:h2:mem:emp_performance_tracker`
* Username: `sa`
* Password: *(leave empty)*

### Notes

* Uses in-memory H2 database
* Schema + sample data auto-load from `schema.sql`
* No external setup required

### System Design Notes

* **500 concurrent managers**

  * Run multiple stateless Spring Boot instances behind a load balancer
  * Tune **HikariCP** for concurrent reads
  * Use DB indexes on:

    * `review_cycle_id`
    * `employee_id`
    * `department`
    * `(review_cycle_id, status)`
  * Use **PostgreSQL read replicas** in production for report traffic

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


