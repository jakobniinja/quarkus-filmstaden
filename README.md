# quarkus-filmstaden

A REST API for a simple movie streaming platform built with Quarkus. Connection to PostgreSQL database.  Users can register,
authenticated users can follow other users, add and manage movies in their collections as well as rate and leave a review for a movie.


## Run in dev mode:

mvn quarkus:dev


## Technologies used:

- Java
- Quarkus
- JPA/Hibernate
- PostgreSQL
- Mapstruct
- H2 database used for testing
- JUnit
- Mockito
- Rest-assured

## Lessons:
mapstruct autogenerate all interface implementations


### TODO
- Introduce NoTicketsAvailableException

- Introduce cancelTicket
- Introduce movieOfTheWeek
