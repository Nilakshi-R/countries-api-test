# Country API Test Automation

This project contains automated tests for the Country API. It validates the API response against specific acceptance criteria using **JUnit Jupiter**.
Tested API is [API](https://restcountries.com/v3.1/independent?status=true)

---

## **Acceptance Criteria**
The following criteria are validated by the tests:
1. The schema is in accordance with the previously agreed schema.
2. The total number of countries is 195.
3. The SASL (South Africa Sign Languages) is in the South African language list.

---

## **Technologies Used**
- **Java**: The programming language used for writing the tests.
- **JUnit 5**: The testing framework for writing and running unit tests.
- **Maven**: For dependency management and building the project.

---

## **Prerequisites**
Before running the tests, ensure you have the following installed:
- **Java 8 or higher**
- **Maven 3.x**

---

## **Setup**
1. Clone the repository:
   git clone [https://github.com/Nilakshi-R/countries-api-test](https://github.com/Nilakshi-R/countries-api-test)
2. cd countries-api-test

---

## **Run the Tests**
- Run the tests using Maven:
  ```
  mvn test
  ```
- The test results will be displayed in the console. If all tests pass, you will see:
  ```
  [INFO] Tests run: 3, Failures: 2, Errors: 0, Skipped: 0
  ```
