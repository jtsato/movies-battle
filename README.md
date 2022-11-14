# Movies Battle

## Prerequisites

* JDK 17
* This project uses Lombok, so enable annotation processing in your IDE

## Technology Stack
* Language: [`Java 17`](https://www.java.com/) 
* Compilation: [`Maven`](https://maven.apache.org/)
* Framework: [`SpringBoot`](https://spring.io/projects/spring-boot)
* Database: [`H2`](http://h2database.com/)
* Type-safe queries: [`Querydsl`](http://www.querydsl.com/)
* Entity Graph: [`JPA Entity Graph`](https://cosium.github.io/making-jpa-great-again/)
* Open API Documentation: [`springdoc-openapi`](https://springdoc.org/)
* Java bean mappings: [`MapStruct`](https://mapstruct.org/)
* Boilerplate code reducer: [`Lombok`](https://projectlombok.org/)
* Logging: [`SLF4J`](https://www.slf4j.org/)

* Tests: 
    * Unit Testing: [`JUnit5`](https://junit.org/junit5/docs/current/user-guide/) 
    * Mocking: [`Mockito`](https://site.mockito.org/)
    * Mutation: Testing [`PIT Mutation Testing`](https://pitest.org/)
    * Code Coverage: [`Jacoco`](https://www.jacoco.org)
    * Architecture Testing: [`ArchUnit`](https://www.archunit.org/)

## Running the application locally

```
mvn clean install -Dmaven.test.skip=true
mvn spring-boot:run -Dspring-boot.run.jvmArguments="-Dspring.profiles.active=test" -f ./configuration
```

## Running the tests locally

```
mvn -e clean install verify
```

## Available Endpoints

* Swagger UI: [`http://localhost:8081/swagger-ui.html`](http://localhost:8081/swagger-ui.html)
* H2 Console: [`http://localhost:8081/h2-console`](http://localhost:8081/h2-console)

## Usecases

### 1. Start the game
```
curl -X POST "http://localhost:8081/v1/games" -H "accept: */*" -H "Accept-Language: pt_BR" -H "PlayerId: 0" -d ""
```

### 2. End the game
```
curl -X PATCH "http://localhost:8081/v1/games" -H "accept: */*" -H "Accept-Language: pt_BR" -H "PlayerId: 0"
```

### 3. Request a quiz
```
curl -X POST "http://localhost:8081/v1/quizzes" -H "accept: */*" -H "Accept-Language: pt_BR" -H "PlayerId: 0" -d ""
```

### 4. Answer the quiz
```
curl -X POST "http://localhost:8081/v1/bets" -H "accept: */*" -H "Accept-Language: pt_BR" -H "PlayerId: 0" -H "Content-Type: application/json" -d "{\"optionId\":\"tt2576852\"}"
```

### 5. Get the ranking
```
curl -X GET "http://localhost:8081/v1/rankings" -H "accept: */*" -H "Accept-Language: pt_BR"
```

## Solution Structure

##### Core: Entities
* Represent your domain object
* Apply only logic that is applicable in general to the whole entity

##### Core: Use Cases
* Represent your business actions, it’s what you can do with the application. Expect one use case for each business action.
* Pure business logic
* Define interfaces for the data that they need in order to apply some logic. One or more providers will implement the interface, but the use case doesn’t know where the data is coming from.
* The use case doesn't know who triggered it and how the results are going to be presented.
* Throws business exceptions.

##### Providers
* Retrieve and store data from and to a number of sources (database, network devices, file system, 3rd parties, etc.)
* Implement the interfaces defined by the use case
* Use whatever framework is most appropriate (they are going to be isolated here anyway).
* Note: if using an ORM for database access, here you'd have another set of objects in order to represent the mapping to the tables (don't use the core entities as they might be very different).

##### Entrypoints
* They are ways of interacting with the application, and typically involve a delivery mechanism (e.g. REST APIs, scheduled jobs, GUI, other systems).
* Trigger a use case and convert the result to the appropriate format for the delivery mechanism
* A GUI would use MVC (or MVP) in here; the controller would trigger a use case

##### Configuration
* Wires everything together.
* Frameworks (e.g. for dependency injection) are isolated here
* Has the "dirty details" like Main class, web server configuration, datasource configuration, etc.

## Testing Strategy
##### Unit Tests
* for TDD (a.k.a. Tests first, to drive design).
* Cover every little detail, aim for 100% coverage.
* “Dev to dev” documentation: What should this class do?
* Test individual classes in isolation, very fast.

##### Integration Tests
* Test integration with slow parts (http, database, etc.)
* “Dev” documentation: Does this work as expected?
* Test one layer in isolation (e.g. only rest endpoint, or only data provider). Slow
* Use whatever library makes it easy

## Resources
##### Blogs & Articles
* The Clean Architecture https://blog.8thlight.com/uncle-bob/2012/08/13/the-clean-architecture.html
* Screaming Architecture http://blog.8thlight.com/uncle-bob/2011/09/30/Screaming-Architecture.html
* NODB https://blog.8thlight.com/uncle-bob/2012/05/15/NODB.html
* Hexagonal Architecture http://alistair.cockburn.us/Hexagonal+architecture

##### Videos & Presentations
* Clean Architecture https://www.youtube.com/results?search_query=clean+architecture
* Hexagonal Architecture https://www.youtube.com/results?search_query=hexagonal+architecture
* Clean Code https://www.youtube.com/results?search_query=clean+code
