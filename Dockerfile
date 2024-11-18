# To launch job can use the line below
# docker run --name=diploma -p 8080:8080 diploma
#-----------------------------------------------
FROM maven:3.9.6-eclipse-temurin-11-alpine AS builder
COPY pom.xml /app/pom.xml
COPY src /app/src
RUN ls -aFr /app
RUN mvn -B -f /app/pom.xml package
FROM openjdk:11-ea-24
WORKDIR /opt/app
COPY --from=builder /app/target/*.jar /opt/app/*.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/opt/app/*.jar"]
