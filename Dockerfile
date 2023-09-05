# FROM eclipse-temurin:17-jdk-alpine
# VOLUME /tmp
# ARG JAR_FILE
# COPY ${JAR_FILE} app.jar
# # EXPOSE 8080
# ENTRYPOINT ["java","-jar","/app.jar"]

FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
# COPY target/demo-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","target/demo-0.0.1-SNAPSHOT.jar"]

# # Use a base Ubuntu image
# FROM ubuntu:latest AS build
#
# # Install Java and Maven
# RUN apt-get update && \
#     apt-get install -y openjdk-17-jdk maven
#
# # Set the working directory
# WORKDIR /app
#
# # Copy the project files
# COPY . .
#
# # Build the Maven project
# RUN mvn clean package
#
# # Define a base image for running the application
# FROM openjdk:17-jdk-slim
#
# # Set the working directory
# WORKDIR /app
#
# # Copy the JAR file from the build stage to the current stage
# COPY --from=build /app/target/demo-0.0.1-SNAPSHOT.jar .
#
# # Expose the port your Spring Boot application listens on (default is 8080)
# EXPOSE 8080
#
# # Define the command to run the application
# CMD ["java", "-jar", "demo-0.0.1-SNAPSHOT.jar"]


# FROM ubuntu:latest AS build
# RUN sudo apt update
# RUN apt install openjdk-17-jdk
# RUN apt install openjdk-17-jre
# RUN apt install maven
# COPY . .
# RUN mvn clean package
# RUN mvn install
#
# # FROM openjdk:17-jdk-slim
# EXPOSE 8080
# CMD ["java", "-jar", "target/demo-0.0.1-SNAPSHOT.jar"]