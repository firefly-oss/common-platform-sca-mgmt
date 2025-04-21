# Common Platform SCA Management Service

## Overview
The Common Platform SCA Management Service is a microservice component of the Firefly platform that handles Strong Customer Authentication (SCA) operations. It provides a comprehensive API for managing SCA operations, challenges, attempts, and audit trails, enabling secure customer authentication flows in compliance with regulatory requirements.

## Architecture
This microservice follows a modular architecture with clear separation of concerns:

```
common-platform-sca-mgmt/
├── common-platform-sca-mgmt-core/       # Business logic implementation
├── common-platform-sca-mgmt-interfaces/ # DTOs and interfaces
├── common-platform-sca-mgmt-models/     # Data models and repositories
└── common-platform-sca-mgmt-web/        # REST API controllers and application entry point
```

### Module Descriptions

#### common-platform-sca-mgmt-interfaces
Contains Data Transfer Objects (DTOs) and interfaces that define the contract between the service and its clients. This module includes:
- DTOs for SCA operations, challenges, attempts, and audit records
- Enums for SCA status, operation types, and event types

#### common-platform-sca-mgmt-models
Contains the data models and repositories for database interactions. This module includes:
- Entity classes for SCA operations, challenges, attempts, and audit records
- Spring Data R2DBC repositories for reactive database access
- Flyway database migration scripts

#### common-platform-sca-mgmt-core
Contains the business logic implementation. This module includes:
- Service implementations for SCA operations, challenges, attempts, and audit records
- Mappers for converting between entities and DTOs

#### common-platform-sca-mgmt-web
Contains the REST API controllers and application entry point. This module includes:
- REST controllers for exposing SCA functionality
- Spring Boot application configuration
- OpenAPI/Swagger documentation

## Technology Stack
- Java 21 with Virtual Threads
- Spring Boot 3.x
- Spring WebFlux for reactive programming
- Spring Data R2DBC for reactive database access
- PostgreSQL database
- Flyway for database migrations
- OpenAPI/Swagger for API documentation
- Docker for containerization

## API Endpoints

### SCA Operations
- `GET /api/v1/sca/operations` - List/filter operations
- `POST /api/v1/sca/operations` - Create a new operation
- `GET /api/v1/sca/operations/{operationId}` - Get operation details
- `PUT /api/v1/sca/operations/{operationId}` - Update an operation
- `DELETE /api/v1/sca/operations/{operationId}` - Delete an operation
- `POST /api/v1/sca/operations/{operationId}/trigger` - Trigger SCA for an operation
- `POST /api/v1/sca/operations/{operationId}/validate` - Validate SCA for an operation

### SCA Challenges
- `GET /api/v1/sca/operations/{operationId}/challenges` - List challenges for an operation
- `POST /api/v1/sca/operations/{operationId}/challenges` - Create a new challenge
- `GET /api/v1/sca/operations/{operationId}/challenges/{challengeId}` - Get challenge details
- `PUT /api/v1/sca/operations/{operationId}/challenges/{challengeId}` - Update a challenge
- `DELETE /api/v1/sca/operations/{operationId}/challenges/{challengeId}` - Delete a challenge
- `POST /api/v1/sca/operations/{operationId}/challenges/{challengeId}/validate` - Validate a challenge

## Setup and Configuration

### Prerequisites
- Java 21 or higher
- Maven 3.8 or higher
- PostgreSQL database

### Environment Variables
The application requires the following environment variables:
- `DB_HOST` - Database hostname
- `DB_PORT` - Database port
- `DB_NAME` - Database name
- `DB_USERNAME` - Database username
- `DB_PASSWORD` - Database password
- `DB_SSL_MODE` - Database SSL mode (e.g., "disable", "require")

### Building the Application
```bash
mvn clean package
```

### Running the Application
```bash
java -jar common-platform-sca-mgmt-web/target/common-platform-sca-mgmt.jar
```

### Docker Deployment
The application can be containerized using the provided Dockerfile:

```bash
# Build the Docker image
docker build -t common-platform-sca-mgmt:latest .

# Run the Docker container
docker run -p 8080:8080 \
  -e DB_HOST=<db_host> \
  -e DB_PORT=<db_port> \
  -e DB_NAME=<db_name> \
  -e DB_USERNAME=<db_username> \
  -e DB_PASSWORD=<db_password> \
  -e DB_SSL_MODE=<db_ssl_mode> \
  common-platform-sca-mgmt:latest
```

## API Documentation
When running in development mode, the API documentation is available at:
- Swagger UI: http://localhost:8080/swagger-ui.html
- OpenAPI JSON: http://localhost:8080/v3/api-docs

## Profiles
The application supports multiple Spring profiles:
- `dev` - Development environment with detailed logging
- `testing` - Testing environment with API documentation enabled
- `prod` - Production environment with minimal logging and API documentation disabled

## Health Checks
The application provides health check endpoints:
- `GET /actuator/health` - Overall health status
- `GET /actuator/health/liveness` - Liveness probe
- `GET /actuator/health/readiness` - Readiness probe

## Metrics
The application exposes Prometheus metrics at:
- `GET /actuator/prometheus`

## CI/CD
The application uses GitHub Actions for CI/CD:
- Builds the application JAR
- Runs tests
- Builds and publishes a Docker image to Azure Container Registry