# Portfolio3 Project
    -Student: Mohammad Ayoubi
    -Mat_Nr. 5122005

## Overview

Portfolio3 is a Spring Boot application designed to manage universities and their associated modules. The project
provides RESTful APIs to perform CRUD operations on universities and modules, utilizing Spring HATEOAS for
hypermedia-driven responses.

## Features

- CRUD operations for universities
- CRUD operations for modules within universities
- Integration with HATEOAS for hypermedia links
- Comprehensive REST client tests

## Prerequisites

- Java 17 or higher
- Maven 3.6.3 or higher
- A running instance of a MariaDB database

## Setup

### Database Setup

1. Ensure you have a MariaDB instance running.
2. Create a database named `portfolio3`.
3. Update your `application.properties` file with the correct database connection details.

### starting the application

- first make sure you run the app Run.java or in the root directory via terminal type:` mvn spring-boot:run`
- to make a jar file for docker type` mvn clean install `
- Running the app via Docker type  `docker-compose up --build `

### Application Properties

Create an `application.properties` file in `src/main/resources/` and add the following:

## API Endpoints

University Endpoints 

    Create University: POST /api/universities
    Get All Universities: GET /api/universities
    Get University by ID: GET /api/universities/{id}
    Update University: PUT /api/universities/{id}
    Delete University: DELETE /api/universities/{id}
    example of Pagination and filtering: /api/universities?page=5&size=10&sort=name,desc
Module Endpoints

    Create Module: POST /api/universities/{universityId}/modules
    Get All Modules: GET /api/universities/{universityId}/modules
    Get Module by ID: GET /api/universities/{universityId}/modules/{moduleId}
    Update Module: PUT /api/universities/{universityId}/modules/{moduleId}
    Delete Module: DELETE /api/universities/{universityId}/modules/{moduleId}

```properties
spring.datasource.url=jdbc:mariadb://localhost:3306/portfolio3
spring.datasource.username=your-username
spring.datasource.password=your-password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.h2.console.enabled=true

