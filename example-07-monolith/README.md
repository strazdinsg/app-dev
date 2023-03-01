# Variations of application architectures - from monolith to fully decoupled

This folder contains projects where each of them is a different variation of the same application.
The examples cover a range of architectures: from a fully server-rendered monolith to dully
decoupled application.

The logic is the same in all the projects: a library website with multiple sections:

* A "Home page" (dashboard) shows some common information and a listing of selected books.
* "Books" page shows a list of available books.
* An "About us" section shows some statistics and a map.

The subdirectories implement his application in different architectural styles.

The first three solutions are monolithic - a single Spring Boot application includes everything:

* [00-static](00-static)- A static HTML+CSS website, with static data, no real database. Can be used
  just for demonstration purposes and as a source of template files for the more dynamic projects.
* [01-monolith](01-monolith) - A monolithic Spring-Boot application serving static, server-rendered
  HTML pages. All the data is selected on the server and injected directly before returning the HTTP
  response. Thymeleaf templates are used.
* [02-html-fragments](02-html-fragments) A static site is loaded first, then Javascript handles
  clicks, requests content from the server, gets pre-rendered content as static HTML chunks. These
  chunks are then placed inside the existing DOM.
* [03-json-data](03-json-data) A static site is loaded first, with some pre-rendered data. It then
  requests data from the backend in JSON format, processes the JSON responses and updates the DOM. 

The remaining two projects are decoupled - a separate frontend and backend application:

* [04-decoupled-js](04-decoupled-js) A fully decoupled solution where the backend serves
  only data in JSON format (REST API) and the frontend application is a separate multi-page
  application, with vanilla Javascript.
* [05-decoupled-react](05-decoupled-react) The same fully decoupled solution, but using
  React JS instead of vanilla Javascript.

The decoupled applications can reside on different servers (backend on one server and frontend 
on another). Also, the backend can be consumed using other apps (such as Flutter apps for mobile).

Used in the
courses [IDATA2306 Application Development](https://www.ntnu.edu/studies/courses/IDATA2306)
and [IDATA2301 Web Technologies](https://www.ntnu.edu/studies/courses/IDATA2301)
at [NTNU](https://ntnu.edu)