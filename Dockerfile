FROM eclipse-temurin:21-jdk


WORKDIR /app

COPY target/E-commerce-backend-task-0.0.1-SNAPSHOT.jar E-commerce-backend-task.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "E-commerce-backend-task.jar"]