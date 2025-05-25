# RestAssuredProject for Booker API Automation Framework

This project provides an API automation framework for testing the [RestAssuredProject](https://restful-booker.herokuapp.com/) using Java, Rest Assured, TestNG, and Maven. It focuses on end-to-end testing of key booking functionalities.

## Table of Contents

- [Features](#features)
- [Prerequisites](#prerequisites)
- [Setup Instructions](#setup-instructions)
- [Running Tests](#running-tests)
- [Viewing Allure Reports](#viewing-allure-reports)
- [Key Design Principles](#key-design-principles)
- [Endpoints Covered](#endpoints-covered)
- [Authentication](#authentication)
- [Test Data](#test-data)

## Features

-   **Comprehensive API Testing:** Covers GET, POST, PUT, DELETE operations for booking management.
-   **REST Assured:** Utilizes the industry-standard library for simplified HTTP request creation and response validation.
-   **TestNG:** Powerful test framework for structuring, executing, and reporting tests.
-   **POJO (Plain Old Java Objects):** Type-safe handling of JSON request/response bodies using Java classes.
-   **Data-Driven Testing (via Payload):** Generates realistic and random test data using JavaFaker.
-   **Configuration Management:** Externalizes API base URL and authentication credentials in `config.properties`.
-   **Allure Reports:** Generates rich, interactive, and detailed test execution reports.
-   **  https://glistening-semolina-68948c.netlify.app/ (see Allure Report here) **  
-   **Maven:** Project management and build automation tool.
-   **Clean Code Practices:** Emphasizes readability, reusability, and maintainability.

## Prerequisites

Before you begin, ensure you have the following installed:

-   **Java Development Kit (JDK) 11 or higher:** [Download JDK](https://www.oracle.com/java/technologies/downloads/)
-   **Apache Maven 3.6.0 or higher:** [Download Maven](https://maven.apache.org/download.cgi)
-   **Allure Commandline (Optional, for viewing reports):** [Installation Guide](https://docs.qameta.io/allure/latest/#_getting_started)
-   **An IDE (e.g., IntelliJ IDEA, Eclipse):** Recommended for easier development and running tests.

## Setup Instructions

1.  **Clone the repository:**
    ```bash
    git clone <your-repo-url>
    cd RestAssuredProject
    ```
2.  **Import into your IDE:**
    * **IntelliJ IDEA:** Open -> navigate to `RestAssuredProject` directory -> Open as Maven project.
    * **Eclipse:** File -> Import -> Maven -> Existing Maven Projects -> Browse to `RestAssuredProject` -> Finish.
3.  **Update Maven Dependencies:** Your IDE should automatically download dependencies. If not, refresh Maven project or run `mvn clean install` in the terminal.

## Running Tests

Tests are configured to run via TestNG and Maven.

1.  **Open your terminal or command prompt.**
2.  **Navigate to the root directory of the project** (`RestAssuredProject`).
3.  **Run all tests using Maven:**
    ```bash
    mvn clean test
    ```
    This command will clean the project, compile the code, and execute the tests defined in `testng.xml`. Allure results will be generated in the `target/allure-results` directory.

## Viewing Allure Reports

After running `mvn clean test`, you can generate and view the interactive Allure report:

1.  **Ensure Allure Commandline is installed** and configured in your system's PATH.
2.  **From the project root directory**, execute:
    ```bash
    allure serve target/allure-results
    ```
    This will open the Allure report in your default web browser, providing a detailed overview of test execution, including steps, durations, and results.

## Key Design Principles

-   **API Layer (Service Class):** The `RestfulBookerService` class acts as an abstraction layer for API interactions. It contains methods for each API endpoint, making test methods clean and focused solely on assertions.
-   **POJOs for Data Modeling:** Request and response bodies are strongly typed using POJOs (`AuthRequest`, `Booking`, `BookingDates`), enabling type-safe data manipulation and better readability.
-   **Test Data Management:** `TestDataGenerator` provides methods to create dynamic and realistic test data, reducing hardcoding and increasing test robustness.
-   **Configuration Externalization:** API base URL and authentication credentials are read from `config.properties`, allowing for easy environment switching without code changes.
-   **Assertions:** Assertions are made using TestNG's `Assert` class for status codes and detailed response body validation.
-   **E2E Test Flow:** The `E2EBookingTests` demonstrate an end-to-end booking lifecycle (create -> get -> update -> delete), ensuring comprehensive coverage.

## Endpoints Covered

The framework covers the following key endpoints of the RestAssureProject:

-   `GET /booking`: Retrieve all booking IDs.
-   `GET /booking/{id}`: Retrieve details of a specific booking.
-   `POST /booking`: Create a new booking with a dynamically generated payload.
-   `PUT /booking/{id}`: Update an existing booking.
-   `DELETE /booking/{id}`: Delete a booking.
-   `POST /auth`: Generate an authentication token required for PUT/DELETE operations.

## Authentication

Authentication for `PUT` and `DELETE` requests is handled by first calling the `POST /auth` endpoint to obtain a `token`. This token is then passed as a `Cookie` header in subsequent authenticated requests.

## Test Data

Test data for booking creation and updates is dynamically generated using the `JavaFaker` library within the `payloads package - Payjload.java class`. This ensures that each test run uses unique and realistic data.
