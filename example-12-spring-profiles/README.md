# Test and prod environments

This project demonstrates how we use different environments:

* Tests are run with the `test` environment, variables from `application-test.properties`
* Production is inside Docker, `prod` environment, we pass the environment variables (MySQL
  password) from the `prod.env` file
* Each developer can run the project directly in `dev` environment (from IntelliJ, outside Docker).
  The MySQL URL is different in development.

Note: in real projects you would not store the `prod.env` file in GIT! Here it is stored just for
illustration of the "end result", a complete solution.

To deploy the system with Docker:

* Get the JAR file first, compile it with `mvn package`
* Then run `docker-compose --env-file prod.env up --build`

To see the value of some environment variables, access http://localhost:8080 in your browser (or
Postman).

Used in the
course [IDATA2306 Application Development](https://www.ntnu.edu/studies/courses/IDATA2306)