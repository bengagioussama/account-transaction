# Microservices Project: Account & Transaction System

This project demonstrates a full-featured microservices architecture using Spring Boot, Spring Cloud, Kafka, Eureka, API Gateway, Config Server, Keycloak, MySQL, MongoDB, and Docker Compose.

---

## **Project Structure**

- **account-service**: Manages accounts (MySQL)
- **transaction-service**: Manages transactions (MongoDB)
- **eureka-server**: Service discovery
- **api-gateway**: API Gateway (static & dynamic routing)
- **config-server**: Centralized configuration (GitHub)
- **Kafka & Zookeeper**: Asynchronous messaging
- **Keycloak**: Security (OAuth2/OpenID Connect)
- **MySQL**: Account data
- **MongoDB**: Transaction data

---

## **Prerequisites**

- [Docker](https://www.docker.com/products/docker-desktop) (required)
- [Git](https://git-scm.com/) (recommended)
- (Optional) Java 17+ and Maven (for local development outside Docker)

---

## **Configuration**

1. **Config Repo**: Ensure your [microservices-config](https://github.com/bengagioussama/microservices-config) repo contains config files for each service and profile (e.g., `account-service-prod.yml`).
2. **Update `application.properties`**: The config server is already set up to use your private GitHub repo, username, and token.

---

## **Running the Project**

1. **Clone this repository**
   ```sh
   git clone <your-repo-url>
   cd <project-root>
   ```

2. **Start all services with Docker Compose**
   ```sh
   docker-compose up --build
   ```
   This will build and start all microservices, databases, Kafka, Keycloak, and supporting infrastructure.

3. **Access the services:**
   - Eureka Dashboard: [http://localhost:8761](http://localhost:8761)
   - API Gateway: [http://localhost:8080](http://localhost:8080)
   - Keycloak: [http://localhost:8089](http://localhost:8089)
   - Config Server: [http://localhost:8888](http://localhost:8888)
   - Account Service: [http://localhost:8081](http://localhost:8081)
   - Transaction Service: [http://localhost:8082](http://localhost:8082)

4. **Keycloak Setup:**
   - Go to [http://localhost:8089](http://localhost:8089)
   - Login: `admin` / `admin`
   - Create a realm: `microservices-realm`
   - Create a client for each service (e.g., `account-service`, `transaction-service`, etc.)
   - Create roles and users as needed

5. **Testing Secured Endpoints:**
   - Obtain a JWT token from Keycloak (via `/realms/microservices-realm/protocol/openid-connect/token`)
   - Use the token as a Bearer token in your API requests

6. **Stopping the Project:**
   ```sh
   docker-compose down
   ```

---

## **Notes**
- All services are configured to use the Config Server and Eureka for service discovery.
- Kafka is used for asynchronous communication between services.
- Feign clients can be added for synchronous REST calls.
- Keycloak secures all endpoints except `/actuator/**`.
- MySQL and MongoDB data are persisted in Docker volumes.

---

## **Troubleshooting**
- Ensure Docker Desktop is running before starting the project.
- If you change your GitHub token or config repo, update the Config Server's `application.properties`.
- For local development, you can run individual services with Maven (`./mvnw spring-boot:run`).

---

## **Contact**
For questions or help, open an issue or contact the project maintainer. 