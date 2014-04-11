Dropwizard Playground
=====================

Java Dropwizard playground for testing and training purpose.

Includes: 
- Dropwizard 0.6.2
- Swagger 1.2

## Setup

1. Install MongoDB: todo
2. Clone repository: git clone git@github.com:leeprovoost/dropwizard-playground.git
3. Build using Maven: mvn package

## How to run

Terminal:
- Start MongoDB: mongod
- Start REST service: java -jar target/dropwizard-playground-0.0.1-SNAPSHOT.jar server configuration.yml

Browser:
- View API listing: http://localhost:3000/api-docs (drill down: http://localhost:3000/api-docs/registration)
- View Swagger UI: http://localhost:3000/swagger-ui/index.html
