# E-Commerce Automation Testing Framework

This project is an automation testing framework for an E-Commerce web application using Selenium, Java, TestNG, Maven, and Page Object Model (POM).

## Tech Stack

* Java
* Selenium WebDriver
* TestNG
* Maven
* Page Object Model (POM)
* Extent Reports
* JSON Data Reader

## Project Structure

### Main Folder

* `Amazon.Abstractcomponent` → Common reusable methods
* `Amazon.pageObjects` → Page classes like Landing Page, Cart Page, Checkout Page, Order Page
* `Amazon.resources` → Extent Reports and Global Data

### Test Folder

* `Amazon.data` → Test data and JSON reader
* `Amazon.Testcomponents` → Base Test, Retry logic, Listeners
* `Amazon.tests` → Actual test cases

## Features

* Page Object Model design
* Data-driven testing using JSON
* Retry mechanism for failed test cases
* Screenshot capture on failure
* Extent Report generation
* TestNG XML execution support
* Reusable methods using abstract components

## Test Cases Covered

* Submit Order Test
* Error Validation Test
* Standalone Test
* Purchase Order Flow
* Login Validation
* Cart and Checkout Validation

## TestNG XML Files

* `testng.xml`
* `purchaseOrder.xml`
* `ErrorValidation.xml`

## How to Run the Project

1. Clone the repository
2. Open the project in Eclipse
3. Update Maven dependencies
4. Run `testng.xml` file
5. View reports inside the `reports` folder

## Maven Command

```bash
mvn test
```

## Author

Ritik Yadav
SDET | Selenium | Java | TestNG | API Testing
