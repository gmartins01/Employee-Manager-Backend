FROM maven:3.6.3-openjdk-11-slim AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY src ./src
RUN mvn package -DskipTests


FROM openjdk:11
VOLUME /tmp
EXPOSE 8080
COPY --from=build /app/target/employee-manager.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]