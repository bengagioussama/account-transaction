FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY . /app
RUN chmod +x ./mvnw
RUN ./mvnw clean package -DskipTests
EXPOSE 8888
CMD ["./mvnw", "spring-boot:run"] 