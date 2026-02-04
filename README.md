# Booking API

This project implements a REST API to analyze booking requests and determine
the optimal combination of bookings that maximizes profit.

The application exposes two endpoints:

- `/stats` – calculates profit statistics per night

- `/maximize` – returns the optimal combination of bookings

## Prerequisites

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