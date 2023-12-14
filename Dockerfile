FROM eclipse-temurin:17-jdk-focal
 
WORKDIR /app
 
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline
 
COPY src ./src
 
CMD ["./mvnw", "spring-boot:run"]