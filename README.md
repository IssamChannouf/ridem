# Ridem

A RESTful app for tracking and evaluating the progress in your workout and best performance, built with Spring Boot and PostgreSQL as a portfolio project to demonstrate Java backend development

## Tech Stack

* Java
* Spring Boot
* PostgreSQL
* Docker
* JUnit
* Maven

## Data Model

The API is built around the following entities and relationships:

- A **User** has many **Workout Templates**
- A **Workout Template** has many **Template Exercises**, each referencing a specific **Exercise**
- A **User** has many **Workout Sessions**
- A **Workout Session** references a **Workout Template** (the plan followed that day)
- A **Workout Session** has many **Session Exercises**, each referencing a specific **Exercise**
- A **Session Exercise** has many **Sets** (reps and weight recorded)
- **Personal Best** is not stored — it is calculated at runtime by querying the highest weight set for a given exercise