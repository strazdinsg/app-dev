# Security example #3: JDBC Authentication

This is the 3rd example in the series on Spring Security.

This example shows how we can configure:

1. Authentication (user storage in database) using JDBC, in an Apache Derby H2 In-memory database (
   to use MySQL or other DB, just change the data source settings in
   [application.properties](src/main/resources/application.properties)).
2. We also include all the functionality from the [second example](../02-authorization): different
   authorization levels, login and logout forms.

The code is based on the video
["How to setup JDBC authentication with Spring Security from scratch" by Java Brains](https://youtu.be/LKvrFltAgCQ).

Used in the
course [IDATA2306 Application development](https://www.ntnu.edu/studies/courses/IDATA2306)
at [NTNU](https://www.ntnu.edu/) campus [Aalesund](https://www.ntnu.edu/alesund).


