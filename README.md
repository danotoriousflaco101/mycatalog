(Phase 1: Local Development)

The mycatalog project is currently in the local development and testing phase (completed before the first commit in GitHub). Below are the main technologies and libraries used so far:


Java 21: Primary programming language.

Spring Boot 3.5.3: Framework for rapid Java application development, including:

spring-boot-starter-data-jpa: For data persistence with JPA.

spring-boot-starter-web: For developing web applications and REST APIs.

spring-boot-starter-validation: For data validation.

spring-boot-devtools: For automatic hot-reloading during development.

H2 Database: In-memory database used for local development and testing.

Lombok: Library to reduce boilerplate code.

Springdoc OpenAPI Starter WebMVC UI 2.8.9: For automatic OpenAPI documentation generation (Swagger UI).

Includes forced commons-lang3:3.18.0 dependency to mitigate vulnerabilities.

Testing
spring-boot-starter-test: For testing Spring Boot applications.

Development Tools
Maven: Project and dependency management tool.

Insomnia: Used for local API testing.

Phase 2 and 3 are on progress. The scope of this project is to have mycatalog tracker live on my website and properly functioning (storing data on a DataBase)