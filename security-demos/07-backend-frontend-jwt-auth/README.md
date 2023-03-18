# Security example #7: Spring Boot Backend + Plain HTML + Javascript frontend, with JWT authentication

This is the 7th example in the series on Spring Security.

It implements the same functionality as [Example #6](../06-monolith-session-auth), but has the
following differences:

1. Backend and frontend are separated. Backend is a Spring Boot application which provides REST API
   only. Frontend is a plain HTML + CSS + Javascript application, which sends Ajax requests.
2. JSON Web Tokens (JWT) used instead of sessions

Used in the
course [IDATA2306 Application development](https://www.ntnu.edu/studies/courses/IDATA2306)
at [NTNU](https://www.ntnu.edu/) campus [Aalesund](https://www.ntnu.edu/alesund).
