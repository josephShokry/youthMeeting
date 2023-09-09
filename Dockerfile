# FROM eclipse-temurin:17-jdk-alpine
# VOLUME /tmp
# COPY target/demo-0.0.1-SNAPSHOT.jar app.jar
# EXPOSE 8080
# ENTRYPOINT ["java","-jar","/app.jar"]


# # Build Stage
# FROM maven:3.8.5-openjdk-17 AS build
# WORKDIR /app
# COPY pom.xml .
# RUN mvnw clean install
# COPY src /app/src
# RUN mvnw package
#
# # Final Stage
# FROM eclipse-temurin:17-jdk-alpine
# WORKDIR /app
# COPY --from=build /app/target/demo-0.0.1-SNAPSHOT.jar app.jar
# EXPOSE 8080
# ENTRYPOINT ["java", "-jar", "/app/app.jar"]

FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn clean install -DskipTests
FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY target/demo-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]