# Let's use Amazon Corretto as the base image. It includes a performance-optimized version of OpenJDK
FROM amazoncorretto:17.0.2

# Copy the JAR file to /app.jar
COPY target/*.jar app.jar

# When the container starts, we will launch command `java -jar /app.jar`
ENTRYPOINT ["java","-jar","/app.jar"]

# To build the image, run the following command in the terminal:
#   docker build -t nontnu/docker-demo

# To run the docker container:
#   docker run -p 8080:8080 --name spring-docker-demo  nontnu/docker-demo

# To run it in the background, add the "detach option": -d
#   docker run -p 8080:8080 -d --name spring-docker-demo  nontnu/docker-demo
