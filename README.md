# rent-a-car-microservices
This project is a Rent a Car application created with microservice architecture. 

Technologies such as Java 17, Spring Boot, and Spring Cloud were used while creating the application. 

Between microservices, asynchronous communication with Kafka Kraft and synchronous communication with OpenFeign was provided. 

Application uses docker containers for some of its services, such as mongoDB database, keycloak, kafka kraft, etc. 

In the application, MongoDB(NoSQL) database was used for filter and invoice services, and PostgreSQL(relational) database was used for other services.

For observability and monitoring, the application integrates Prometheus for metrics collection, Grafana for visualization, and Zipkin for distributed tracing. 

API Gateway serves as a central entry point request routing. The Eureka Server enables service discovery, allowing microservices to dynamically register and discover each other.

## Diagram
<img src="https://www.linkpicture.com/q/mimari.drawio-1.png" width="600"  />

## Tools and Technologies
* **Java 17**
* **Spring Boot**
* **Spring Cloud**
* **Netflix Eureka Server**
* **Spring Cloud Gateway**
* **Spring Data JPA**
* **PostgreSQL**
* **MongoDB**
* **Kafka KRaft**
* **Zipkin**
* **Prometheus**
* **Grafana**
* **Docker**
* **Keycloak**
* **Config Server**
* **Spring Cloud Open Feign**
* **Maven**

<!-- | Method	| Path	| Description	| User authenticated	| Role |
------------- | ------------------------- | ------------- | ------------- | ------------- |

| a | a |
-------- | -------- | -->
