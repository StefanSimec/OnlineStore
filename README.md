# Online Store

## About

### Description
Sample project demonstrating RESTful application using SpringBoot. Supports basic CRUD operations for products 
and Order placement and retrieval.

### Project structure
OnlineStore (parent)
* api (API, DTOs)
* data (H2 DB, model, repositories)
* service (SpringBoot application)

## Usage

### Prerequisites

* JDK 11 installed with JAVA_HOME and PATH environment variables set

### Build
```
mvnw clean install
mvnw package spring-boot:repackage -pl service 
```
### Run

* Maven: 
`mvnw clean install spring-boot:run -pl service`  

* Java: 
`java -jar service\target\online-store-service-1.0.0.jar` 

### Run tests
* All test: `mvnw verify`
* Unit tests: `mvnw surefire-report:report -Daggregate=true`
* Integration tests: `mvnw failsafe:integration-test -Pit-tests`

## Future work:

#### Application
* Implement security
* Use transactions
* Broaden test suite
* Support localization
* Improve logging

#### API
* Extend RESTful support to Orders API
* API client generation
* Support paging
* Improve API documentation

#### Persistence
* Properly store order data (order items should be product snapshots - in case product data changes in the future)
* Proper database instead of H2
* Entity model diagrams and source generation
* Extend basic model with additional attributes and tables
* Use Envers auditing

#### Build
* Support Docker build
* Sort out Intellij plugin version inheritance
* Test reports

## License

Apache 2.0 li

## Contact

stefan.simec@gmail.com


