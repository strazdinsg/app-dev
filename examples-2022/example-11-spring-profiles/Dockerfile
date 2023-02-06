# Let's use Amazon Corretto as the base image. It includes a performance-optimized version of OpenJDK
FROM amazoncorretto:17.0.2

# Copy the JAR file to /app.jar
COPY target/*.jar app.jar

# When the container starts, we will launch command `java -jar /app.jar`
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=${SPRING_PROFILE}","/app.jar"]

