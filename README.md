# Drone Communication Task

[![Java](https://img.shields.io/badge/Java-17-blue?logo=java&style=flat)](https://www.java.com/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6.1.1-brightgreen?logo=spring&style=flat)](https://spring.io/projects/spring-boot)
[![MySQL](https://img.shields.io/badge/MySQL-8.0.34-blue?logo=mysql&style=flat)](https://www.mysql.com/)
[![Docker](https://img.shields.io/badge/Docker-latest-blue?logo=docker&style=flat)](https://www.docker.com/)

## Table of Contents

- [Introduction](#introduction)
- [Prerequisites](#prerequisites)
- [Features](#features)
- [Schema](#schema)
- [API Documentation](#api-documentation)
- [Setup](#setup)

## Introduction

There is a major new technology that is destined to be a disruptive force in the field of transportation: **the drone**. Just as the mobile phone allowed developing countries to leapfrog older technologies for personal communication, the drone has the potential to leapfrog traditional transportation infrastructure.
Useful drone functions include the delivery of small items that are (urgently) needed in locations with difficult access.

## Prerequisites

- A text editor (e.g. IntelliJ IDEA Ultimate `recommended`, Eclipse)
- A platform for testing APIs like [Postman](https://www.postman.com/downloads/)

## Features

1. Register a Drone.
2. Load Drone with Medication Items.
3. Check Loaded Medication Items.
4. Check Available Drones for Loading.
5. Check the Drone Battery Level.

## Schema

![schema](https://github.com/MuhammedMagdyy/API-Documentation/assets/60513866/70abd25a-151c-42d0-8aac-8e4c1a2a0f90)

## API Documentation
### Postman

Explore and test the API using the provided Postman collection. Click the "Run in Postman" button below to import the collection into your workspace collections.

**Don't forget to choose the `Dron-Task` environment from `No Environment` located in the top-right corner**

[<img src="https://run.pstmn.io/button.svg" alt="Run In Postman" style="width: 128px; height: 32px;">](https://app.getpostman.com/run-collection/10107969-81173bb4-353f-4f85-8100-faa82e5bce6b?action=collection%2Ffork&source=rip_markdown&collection-url=entityId%3D10107969-81173bb4-353f-4f85-8100-faa82e5bce6b%26entityType%3Dcollection%26workspaceId%3Da89d13d1-ccf8-4dbe-8d4d-f8a6b1c4c2dc#?env%5BDrone-Task%5D=W3sia2V5IjoiQkFTRV9VUkwiLCJ2YWx1ZSI6IiIsImVuYWJsZWQiOnRydWUsInR5cGUiOiJkZWZhdWx0In1d)

### Swagger

Use the Swagger UI to explore the available endpoints, view request/response examples, and even test the API interactively.

1. Run the application.
2. Open your web browser and go to `http://localhost:{PORT}/swagger-ui.html` to view the Swagger documentation.
   
**Replace `PORT` with the actual port number if your application uses a different one.**

## Setup

To run the Spring Boot application locally, make sure you follow these steps:

1. Clone the repository
```bash
https://github.com/MuhammedMagdyy/elmenus-technical-task.git
```
2. Change the project's directory
```bash
cd elmenus-technical-task
```
3. The application uses MySQL as its database. Make sure to set up your MySQL database. **Feel free to use your data source**. Here are my configurations in the `application.properties` file
```bash
# Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/{database name}
spring.datasource.username=
spring.datasource.password=

# Hibernate Configuration
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect

# Logging Configuration
logging.level.org.springframework.web=DEBUG
```
4. Press run and let the magic happen! 🤩
