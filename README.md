# Ridem

A RESTful API for tracking gym workouts and monitoring progressive overload over time, built with Java, Spring Boot, and PostgreSQL.

## Tech Stack

* Java 21
* Spring Boot 3.5
* PostgreSQL 16
* Docker
* Maven
* JUnit (in progress)

## API Endpoints

### Users
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | /users | Retrieve all users |
| POST | /users | Create a new user |
| GET | /users/{id} | Retrieve a user by ID |
| PUT | /users/{id} | Update a user by ID |
| DELETE | /users/{id} | Delete a user by ID |

## Data Model

The API is built around the following entities and relationships:

- A **User** has many **Workout Templates**
- A **Workout Template** has many **Template Exercises**, each referencing a specific **Exercise**
- A **User** has many **Workout Sessions**
- A **Workout Session** references a **Workout Template** (the plan followed that day)
- A **Workout Session** has many **Session Exercises**, each referencing a specific **Exercise**
- A **Session Exercise** has many **Sets** (reps and weight recorded)
- **Personal Best** is not stored — it is calculated at runtime by querying the highest weight set for a given exercise