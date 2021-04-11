Rest API Tests

This project is implemented using restAssured,cucumber,junit and java.

The project structure consists of

TestCountriesAPI.feature - Scenarios are listed in this file using gherkin

CountriesStep - Step definitons methods for feature files in included in this file

Countries - Consists of API calls for capital and country API
            Includes methods for validating the schemas
            Includes getter methods to get the extracted json response
Test Runner - This file used to run the tests and feature file path is given in this file

Json Schema - The schemas for validating API response is stored under resources folder

Running the test
```
 - mvn test
     OR
 - Right click TestRunner and run it 
```
