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

### Exercises
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | /exercises | Retrieve all exercises |
| POST | /exercises | Create a new exercise |
| GET | /exercises/{id} | Retrieve an exercise by ID |
| PUT | /exercises/{id} | Update an exercise by ID |
| DELETE | /exercises/{id} | Delete an exercise by ID |

## Data Model

The API is built around the following entities and relationships:

- A **User** has many **Workout Templates**
- A **Workout Template** has many **Template Exercises**, each referencing a specific **Exercise**
- A **User** has many **Workout Sessions**
- A **Workout Session** references a **Workout Template** (the plan followed that day)
- A **Workout Session** has many **Session Exercises**, each referencing a specific **Exercise**
- A **Session Exercise** has many **Sets** (reps and weight recorded)
- **Personal Best** is not stored — it is calculated at runtime by querying the highest weight set for a given exercise

## Setup

1. Install prerequisites: Java 21, Docker and Maven
2. Clone the repo with `git clone https://github.com/IssamChannouf/ridem.git`
3. Change directory to ridem with `cd ridem` 
4. Create `.env`file from `.env.example`
5. Start PostgreSQL with `docker compose up -d`
6. Run the app with `./mvnw spring-boot:run`