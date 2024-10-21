# ms-prices

## Overview

This small project implements a price management microservice using hexagonal architecture with
Spring Boot. The service provides a REST API to query product prices based on brand and date.

Requirements:

- JDK 21
- Maven 3.8.4

```shell
mvn clean install && mvn spring-boot:run -pl boot
```

This command starts the Spring Boot microservice with an in-memory H2 database, along with Swagger
UI for easier testing. You can access the Swagger UI at the
following [URL](http://localhost:8080/swagger-ui/index.html#/price/getPrice)

For example, a valid request URL request with 200 response:

```shell
http://localhost:8080/api/v1/price?applyDate=2020-06-14T21:00:00.000Z&productId=35455&brandId=1
```

## Design

### Hexagonal Architecture

The hexagonal architecture (or ports and adapters) was chosen for this project because it promotes a
clear separation of concerns. This architecture allows us to isolate the core business logic from
external dependencies, making the application more maintainable and adaptable to changes in
technology.

The structure of the project is as follows:

- **api-rest**: This is the _input adapter_ that handles incoming HTTP requests and converts them
  into
  a format suitable for the application layer.
- **application**: This module contains the _service layer_, where the business logic resides. It
  coordinates interactions between the domain and other components.
- **domain**: This module encapsulates the _core_ business logic and _domain_ entities, ensuring
  that
  the application behavior is independent of external factors.
- **infrastructure**: This _output adapter_ handles interactions with external systems (in this case
  there is only H2 db)
- **boot**: This module contains the application configuration and serves as the entry point for
  launching the application.

By using hexagonal architecture, we adhere to the principles of SOLID design, promoting single
responsibility, open-closed, and dependency inversion principles.

### Multi-Module

Maven multimodule provides better organization, separation of concerns, and maintainability, while
naturally enforcing good practices such as the SOLID principles. This structure can lead to more
flexible, testable, and easily understandable codebases—key features for sustainable software
development.

### API design

OpenAPI standard was used to design the REST API, employing the openapi-generator-maven-plugin to
generate the necessary code. This approach offers several benefits:

- Ensures consistency and accuracy in API design by using a standardized format that
  promotes clear documentation and easier collaboration among team members.
- the automatic code generation significantly speeds up development time by reducing manual coding
  errors
  and boilerplate code, allowing developers to focus on implementing business logic.
- Generated code adheres to best practices and can be easily maintained and extended.

### Patterns

In this project, we utilized several patterns:

- **Repository Pattern**: This pattern is used for data access, providing a clear separation between
  the application logic and data access logic. It encapsulates the logic required to access data
  sources.

- **Adapter Pattern**: This pattern is employed to transform data between the domain layer and
  external layers, such as when interacting with the database or external services.

- **Object Mother**: This pattern simplifies the creation of
  test objects, allowing us to manage the complexity of test data and maintain clarity in our test
  cases.

### Testing

#### Unit Testing

Unit tests have been conducted using JUnit 5 and Mockito specifically in the service layer. ->
`FindPriceServiceTest`

#### Integration Testing

Integration tests were implemented to ensure that the application components work together as
expected. One of the integration tests was parameterized using `@CsvSource`, allowing us to validate
the REST endpoint with multiple sets of input data. This approach enhances the test coverage and
ensures that various scenarios are adequately tested. -> `PriceControllerIntegrationTest.java`

### Conclusion

This microservice exemplifies a robust and maintainable design using hexagonal architecture and
established design patterns. The structured approach not only aids in clarity and organization but
also supports future scalability and adaptability.