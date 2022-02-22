# Environment variable demo in Spring Boot

This project demonstrates how we can inject an environment variable into a field of a Spring Boot application. The
respective class must be annotated with any @Component annotation, such as @RestController, @Service, etc.

You can pass --SERVER_FLAG=yourValue when running the application to set the `server.flag` environment variable.

To see the value of some environment variables, access http://localhost:8080 in your browser (or Postman). 