# Booking API

## Architecture & Design Decisions

This project was originally developed as a coding challenge, but I approached it as a small real-world service, focusing on clean boundaries, maintainability, and testability.

The application follows a layered architecture inspired by Hexagonal Architecture (Ports and Adapters) and Clean Architecture principles.
The goal was to keep the core domain independent from frameworks and delivery mechanisms, while making the code easy to understand and evolve.

### Architectural overview

The codebase is structured into three main layers:

- `Domain` – core business logic and rules
- `Application` – use cases and business orchestration
- `Infrastructure` – HTTP adapters, DTOs, mappers, and framework-specific concerns

Ports are defined in the domain layer and implemented by application services, while controllers act as adapters that translate HTTP requests into domain concepts.

### Domain layer

The domain contains the core business concepts:
- `BookingCandidate`, representing a booking option evaluated for profitability
- `BookingProfitStats`, a value object representing aggregated profit metrics

The domain:
- enforces business invariants
- encapsulates behavior (e.g. profit calculation, date overlap rules)
- has no dependency on Spring, HTTP, or JSON

This makes the domain easy to test and reusable in other contexts.

### Application layer

The application layer coordinates business flows through use cases and services.
It:
- implements domain ports
- orchestrates domain logic
- applies SOLID principles, particularly Single Responsibility and Dependency Inversion

This layer depends on the domain, but remains independent from delivery mechanisms.

### Infrastructure layer

The infrastructure layer contains all external concerns:
- REST controllers
- request and response DTOs
- explicit mappers between DTOs and domain objects
- exception handling and configuration

Controllers act as adapters, keeping HTTP concerns isolated from the core domain model.

### Testing strategy

The project includes multiple testing levels:
- Unit tests for domain logic and application services
- Controller tests to verify HTTP-to-domain adaptation
- Integration tests using Spring Boot and real HTTP calls, validating the full request/response flow

Tests are aligned with the architecture:
- domain objects are not mocked
- collaborators are mocked only when appropriate
- mapping logic is executed as real code

### Notes on trade-offs

Some design decisions were made intentionally for simplicity and clarity:
- The booking combination algorithm prioritizes readability over optimal performance.
- Validation is enforced in the domain to guarantee invariants.
- Explicit mapping is preferred over automated or reflection-based solutions.

These trade-offs aim to keep the codebase clear, maintainable, and easy to reason about, especially in the context of a small service.

## How to run the application

This project implements a REST API to analyze booking requests and determine
the optimal combination of bookings that maximizes profit.

The application exposes two endpoints:

- `/stats` – calculates profit statistics per night

- `/maximize` – returns the optimal combination of bookings

### Prerequisites

To run the application using Docker, you only need:

Docker (tested with Docker Desktop)

No local Java or Maven installation is required.

### Running the application
1. Build the Docker image

From the root of the project, run:
```bash
docker build -t booking-api .
```
This command builds the application and packages it into a Docker image.

2. Run the container

Once the image is built, start the application with:
```bash
docker run -p 8080:8080 booking-api
```
The API will be available at:
```
http://localhost:8080
```
### Available endpoints

#### POST /maximize

Returns the optimal combination of bookings that maximizes total profit.

#### POST /stats

Calculates profit statistics per night (average, minimum, maximum).

Example requests for both endpoints are included in the Postman collection.

## Testing

Running tests locally (optional)

If you prefer to run tests without Docker, you can execute:
```bash
mvn test
```
Test coverage is measured using JaCoCo.

After running the tests, the coverage report is available at:
```bash
target/site/jacoco/index.html
```
### Postman collection

A Postman collection is included to easily test the API.

Location:
```
postman/Booking-Api.postman_collection.json
```
Import the collection into Postman and run the requests against:
```
http://localhost:8080
```