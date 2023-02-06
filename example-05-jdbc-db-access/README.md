# Database access with JDBC

This is an example showing how to access an SQL database (MySQL) using raw JDBC approach. This is
the most custom way of accessing database (compare to JPA), but provides a lot of flexibility and
sometimes can provide the necessary performance that JPA may not offer.

The application assumes that you have created a database with necessary tables. To do that, execute
the SQL statements provided in the file `library-db-schema.sql`.

Used in the
course [IDATA2306 Application development](https://www.ntnu.edu/studies/courses/IDATA2306)
at [NTNU](https://ntnu.edu).
