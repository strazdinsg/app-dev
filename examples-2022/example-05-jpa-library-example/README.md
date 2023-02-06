# JPA Example

This project shows how we can combine a REST API with an (in-memory) SQL database. Access to the data is implemented
using the standard JPA approach - with CrudRepository interface. We also define Many-to-one and Many-to-many
relationships.

This example can be useful as a reference when building your applications.

We use in-memory Apache Derby database. The data is cleaned on every restart of the application. We also insert some
dummy data into the db every time.

Used in the course [IDATA2306 Application Development](https://www.ntnu.edu/studies/courses/IDATA2306)