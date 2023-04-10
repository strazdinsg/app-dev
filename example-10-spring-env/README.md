# Environment variable demo in Spring Boot

This project demonstrates how we can inject an environment variable into a field of a Spring Boot
application. The respective class must be annotated with any `@Component` annotation, such as
`@RestController`, `@Service`, etc.

You can pass `--SERVER_FLAG=yourValue` when running the application. This will set the 
`server.flag` property which can be accessed in the classes.

To see the value of some environment variables, access http://localhost:8080 in your browser (or
Postman).

Used in the
course [IDATA2306 Application Development](https://www.ntnu.edu/studies/courses/IDATA2306)