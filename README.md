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
## Services

### Inventory Service
---------------------------------------------------------------------------------------------------------

**Brands Controller**

Method	| Path	| Description	| User authenticated	| Role
------- | ----- | ----------- |:-------------:| :-------------:|
GET	| /api/brands	| Get all brands	|  Yes | User,  Admin |
GET	| /api/brands/id		| Get brand by id	| Yes | User,  Admin |
POST| /api/brands	| Create new brand | Yes | User,  Admin |
PUT	| /api/brands/id	| Update existing brand by id | Yes | User,  Admin |
DELETE | /api/brands/id | Delete brand by id  | Yes | User,  Admin |


**Models Controller**

Method	| Path	| Description	| User authenticated	| Role
------- | ----- | ----------- |:-------------:| :-------------:|
GET	| /api/models	| Get all models	|  Yes | User,  Admin |
GET	| /api/models/id	| Get model by id	| Yes | User,  Admin |
POST| /api/models	| Create new model| Yes | User,  Admin |
PUT	| /api/models/id	| Update existing model by id | Yes | User,  Admin |
DELETE | /api/models/id | Delete model by id  | Yes | User,  Admin |

**Cars Controller**

Method	| Path	| Description	| User authenticated	| Role
------- | ----- | ----------- |:-------------:| :-------------:|
GET	| /api/cars	| Get all car	|  Yes | User and Admin |
GET	| /api/cars/id	| Get car by id	| Yes | User,  Admin |
POST| /api/cars	| Create new car | Yes | User,  Admin |
PUT	| /api/cars/id	| Update existing car | Yes | User,  Admin |
DELETE | /api/cars/id | Delete car by id  | Yes | User,  Admin |
GET	| /api/cars/check-car-available/id	| Check if car is available (OpenFeign)	| No | X |

<br/>

### Filter Service
---------------------------------------------------------------------------------------------------------

Method	| Path	| Description	| User authenticated	| Role
------- | ----- | ----------- |:-------------:| :-------------:|
GET	| /api/filters	| Get all car informations	|  No | X |
GET	| /api/filters/id	| Get car information by id	| No | X |

<br/>

### Maintenance Service
---------------------------------------------------------------------------------------------------------

Method	| Path	| Description	| User authenticated	| Role
------- | ----- | ----------- |:-------------:| :-------------:|
GET	| /api/maintenances	| Get all maintenance informations	|  Yes | User,  Admin |
GET	| /api/maintenances/id	| Get maintenance information by id	| Yes | User,  Admin |
POST| /api/maintenances	| Create new maintenance | Yes | User,  Admin |
PUT	| /api/maintenances/id	| Update existing maintenance | Yes | User,  Admin |
PUT	| /api/maintenances/returnMaintenance/id	| Return cars from maintenance | Yes | User,  Admin |
DELETE | /api/maintenances/id | Delete maintenance by id  | Yes | User,  Admin |

<br/>

### Payment Service
---------------------------------------------------------------------------------------------------------

Method	| Path	| Description	| User authenticated	| Role
------- | ----- | ----------- |:-------------:| :-------------:|
GET	| /api/payments	| Get all payment informations	|  Yes | User,  Admin |
GET	| /api/payments/id	| Get payment information by id	| Yes | User,  Admin |
POST| /api/payments	| Create new payment | Yes | User,  Admin |
PUT	| /api/payments/id | Update existing payment by id | Yes | User,  Admin |
POST | /api/payments/check-payment-process	| Payment processing control (OpenFeign) | No | X |
DELETE | /api/payments/id | Delete payment by id  | Yes | User,  Admin |

<br/>

### Rental Service
---------------------------------------------------------------------------------------------------------

Method	| Path	| Description	| User authenticated	| Role
------- | ----- | ----------- |:-------------:| :-------------:|
GET	| /api/rentals	| Get all rental informations	|  Yes | User,  Admin |
GET	| /api/rentals/id	| Get rental information by id	| Yes | User,  Admin |
POST| /api/rentals	| Create new rental | Yes | User,  Admin |
PUT	| /api/rentals/id | Update existing rental by id | Yes | User,  Admin |
DELETE | /api/rentals/id | Delete rentals by id  | Yes | User,  Admin |

<br/>

### Invoice Service
---------------------------------------------------------------------------------------------------------

Method	| Path	| Description	| User authenticated	| Role
------- | ----- | ----------- |:-------------:| :-------------:|
GET	| /api/invoices	| Get all invoice informations	|  Yes | User,  Admin |
GET	| /api/invoices/id	| Get invoice information by id	| Yes | User,  Admin |
POST| /api/invoices	| Create new invoice | Yes | User,  Admin |
PUT	| /api/invoices/id | Update existing invoice by id | Yes | User,  Admin |
DELETE | /api/invoices/id | Delete invoice by id  | Yes | User,  Admin 
