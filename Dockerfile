FROM openjdk:17-jdk-slim

COPY target/OracleAssessment-1.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
