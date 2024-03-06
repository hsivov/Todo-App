FROM eclipse-temurin:17-alpine
WORKDIR /app
COPY target/todo-app.jar .
CMD ["java", "-jar", "todo-app.jar"]
