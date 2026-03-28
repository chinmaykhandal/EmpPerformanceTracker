# Employee Performance Tracker API

## Run Locally

```bash
mvn spring-boot:run
```

App runs at:
`http://localhost:8080`

## H2 Console

`http://localhost:8080/h2-console`

Use:

* JDBC URL: `jdbc:h2:mem:testdb`
* Username: `sa`
* Password: *(leave empty)*

## Notes

* Uses in-memory H2 database
* Schema + sample data auto-load from `schema.sql`
* No external setup required
