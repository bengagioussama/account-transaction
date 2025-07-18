# Étape 1 : build
FROM maven:3.9.4-eclipse-temurin-17 AS build
WORKDIR /build

COPY . .
RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests -Dfile.encoding=UTF-8

# Étape 2 : image finale
FROM openjdk:17
WORKDIR /app

COPY --from=build /build/target/*.jar app.jar

CMD ["java", "-jar", "app.jar"]
