# Frontend + Backend example

Spring Boot is typically used as a server application - the backend. However, it is totally possible to combine both the
backend and frontend in a single Spring Boot project. The easiest way is to simply store your frontend files
inside `src/main/resources/static`. These files will be automatically accessible in the browser. For example, file
stored in `src/main/resources/static/index.html` will be accessible at URL `http://localhost:8080/index.html` and also
at `http://localhost:8080/`, because `index.html` is one of the "default files" served by web servers.
File `src/main/resources/static/styles/main.css` will be accessible in the browser at the
URL `http://localhost:8080/styles/main.css`, and so on.

One thing you probably want to do to separate frontend and backend: set a root URL for the api, for example, that all
backend REST calls are located under `/api`. Turns out it is not that easy,
see [This StackOverflow thread](https://stackoverflow.com/questions/32927937/how-to-set-base-url-for-rest-in-spring-boot)

One option is to set the following line in `application.properties`
file:
`spring.data.rest.base-path=/api`

And then for your `@RestController` classes you can set `@RequestMapping("${spring.data.rest.base-path}")`

# Demo

To see how this project works, run it and navigate to `http://localhost:8080`. An HTML site will be opened there, which
sends an HTTP GET request to `/api/hello` using Javascript. The result of the API request is displayed on the page.

# ThymeLeaf templates

This example uses static HTML file. However, the ThymeLeaf template engine allows you to do some server-side rendering -
you can define controllers for specific URLs; these controllers can load necessary data and inject the data in the
templates. In this manner you create a `monolith` application without a separation of backend and frontend.

Check out [this tutorial](https://spring.io/guides/gs/serving-web-content/) to know more about using ThymeLeaf templates.