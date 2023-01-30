# Swagger docs examples

Here you can see projects showing how to add Swagger documentation to a Spring Boot project:

* [`01-old-with-springfox`](01-old-with-springfox) - shows an example of using the Springfox
  library. Springfox is not actively maintained and
  does
  not work wth Spring Boot 3.x (at the moment of writing this document: January 203).
* [`02-minimalistic-springdoc`](02-minimalistic-springdoc) - shows an example of using Springdoc
  library for Swagger. A minimalistic example, only
  including the dependency, no extra configuration.
* [`03-springdoc-with-config`](03-springdoc-with-config) - extends the previous example by adding
  some extra configuration and API custom endpoint[README.md](01-old-with-springfox%2FREADME.md)
  descriptions.

Note: if you watched
[YouTube videos on Swagger+Spring](https://www.youtube.com[README.md](01-old-with-springfox%2FREADME.md)/watch?v=gduKpLW_vdY&t=1022)
from "Java Brains" - these videos use an older version of Spring Boot.

To get it work you have several options:

* Use Spring Boot 2.6 or 2.7 and add the following line in `application.properties`, see
  [this StackOverflow thread](https://stackoverflow.com/questions/70059018/swagger-2-issue-spring-boot)
  for explanation:

```
spring.mvc.pathmatch.matching-strategy=ant-path-matcher
```

* Use Spring Boot version 3.x, but then you need to switch from Springfox library to Springdoc

To access the docs, launch the project and navigate to Doc UI. The URL depends on the solution you
use:

* If you use Springfox with Spring Boot 2.x, the URL
  is [http://localhost:8080/swagger-ui/](http://localhost:8080/swagger-ui/).
* If you use Spring Boot 3.x with Springdoc, the URL
  is [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html).

These examples are used in the
course [IDATA2306 Application Development](https://www.ntnu.edu/studies/courses/IDATA2306)