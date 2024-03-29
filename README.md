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

### Authentication Service
---------------------------------------------------------------------------------------------------------
The Rent-a-Car Microservices Application uses Spring Security for secure user authentication. It utilizes JSON Web Tokens (JWT) and verifies them using a JWK Set URI.

```spring:
   security:
     oauth2:
       resourceserver:
         jwt:
           jwk-set-uri: ${JWKS_URL}
```
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

<br/>

## Eureka Service Discovery

- The Eureka Server plays a crucial role in our microservices architecture by enabling smooth communication between microservices.
- It acts as a vital component, acting as a directory for our services, allowing them to register and discover each other dynamically.
- With the help of OpenFeign, microservices can easily interact with one another, leveraging the Eureka Server's ability to discover services.
- Additionally, the API Gateway utilizes the Eureka Server to find and direct requests to the correct microservices.
- By utilizing the Eureka Server, our microservices can easily find and communicate with one another without hardcoding any specific URLs.
- This combined functionality ensures smooth and efficient communication, contributing to the resilience and scalability of our system.
- You can check eureka dashboard with the current instances registered with Eureka on : http://localhost:8761

<img src="https://camo.githubusercontent.com/56c1b92389855e9bd28ec9c47bd0d0ff9b96ab025fd7863501dbf136502b1847/68747470733a2f2f756361726563646e2e636f6d2f35303831633736342d663561632d346362372d626430382d3666393032643835303037312f" width="500"  />

<br/>

## Spring Cloud Gateway
The API gateway is the front door of our microservices architecture. It handles all the requests from the client and routes them to the right service.

- **Routes requests to microservices based on defined rules**: Spring Cloud Gateway efficiently routes requests to the corresponding microservices using predefined routing rules, ensuring proper distribution of incoming traffic.
- **Provides filtering, load balancing, and security features**: Spring Cloud Gateway offers built-in capabilities for request filtering, load balancing, and security enforcement, allowing customization and optimization of request handling.
- **Integrates with authentication providers for security**: Spring Cloud Gateway seamlessly integrates with authentication providers, enabling secure access control to microservices by enforcing authentication and authorization rules.
- **Supports dynamic service registration and discovery**: Spring Cloud Gateway supports dynamic service registration and discovery, simplifying the management and scaling of microservices by automatically detecting and routing requests to registered services.

<img src="https://miro.medium.com/v2/resize:fit:828/format:webp/1*q4JzKYvQCJewGHyiMrO_og.png" width="500"  />



## Config Server
The config server of the Rent A Car application runs on port 8888 and provides configuration properties from a git repository at https://github.com/eamre/config-server-t
- **Centralized Configuration Management**: The Config Server consolidates configuration management for all microservices, providing a single place to manage configurations and avoiding duplication across services.
- **Dynamic Configuration Updates**: The Config Server allows real-time configuration updates without service restarts, enabling easy and seamless changes to configurations and reducing downtime.
- **Version Control and History**: The Config Server integrates with version control systems like Git, offering versioning and history tracking for configurations, ensuring a record of changes, and enabling rollbacks when necessary.
- **Integration with Spring Cloud**: The Config Server seamlessly integrates with Spring Cloud, facilitating the adoption of cloud-native patterns and practices. It works smoothly with other Spring Cloud components like service discovery, API gateway, and distributed tracing, enhancing the overall functionality of the microservices architecture.

<img src="https://miro.medium.com/v2/resize:fit:636/1*obwqAslb4nI0S9nVzdI4PA.jpeg" width="500"  />

<br/>

## Docker
**What is Docker?**
Docker is an open-source platform for creating, deploying, and managing containers. It enables developers to package applications with their dependencies in isolated environments known as containers, making it easier to deliver applications consistently and efficiently.

**How Docker Containers Work?**
Docker containers are lightweight and portable environments that isolate and run applications with all their dependencies. Each container operates a single process, allowing multiple applications to be managed on a single host machine.

**Benefits of Docker**

- **Consistent and Isolated Environment:** Docker provides predictable and scalable environments, boosting productivity and reducing debugging time.
- **Cost-effective:** Docker accelerates deployment and resource allocation, saving time and resources.
- **Portability:** Docker ensures that containerized applications run smoothly on any system with Docker.
- **Scalability:** Docker allows easy scaling of containers based on application demands, managing workloads efficiently.

<img src= "https://civo-com-assets.ams3.digitaloceanspaces.com/content_images/2258.blog.JPG?1678958065" width="380"  />

### Docker Compose

Docker Compose is a tool that helps you to run and manage multiple Docker containers together. It lets you define a single configuration file for your application's containers, and then run or manage them from a single command.

A Docker Compose file is a YML file that contains the definition of your application's containers. The file specifies the name, image, and port mapping for each container, as well as any other configuration options that you need.

Docker Compose is a powerful tool that can help you to run and manage complex applications with multiple Docker containers.

**The Docker Compose file consists of the following sections:**

- Version: Specifies which version of compose the compose file uses. Each version may contain its own features and new improvements.
- Services: Contains a list of containers that should be launched by Docker Compose.
- Volumes: Allows you to manage data storage for containers. It ensures important data is persisted across container restarts and can be shared between containers or with the host machine, making it convenient for managing critical information in your microservices application.
- Networks: Defines network connections between containers.
- Environment: Environment variables are settings that affect the operation of the container. For example, an environment variable can be a database connection URL to be used for the container.
  
**Some Docker Compose Commands**
```
docker-compose up -d - This command starts all of the containers defined in your Docker Compose file.
docker-compose down - This command stops and removes all of the containers defined in your Docker Compose file.
docker-compose build - This command builds all of the images defined in your Docker Compose file.
docker-compose ps - This command lists all of the running containers defined in your Docker Compose file.
docker-compose logs - This command shows the logs for all of the running containers defined in your Docker Compose file.
```
<br/>

## Kafka KRaft
**Why a message broker**
- Message broker is a service that allows you to send and receive messages or notifications.
- The purpose is to make an asynchronous communication between microservices.
- In case we have an outage of notification microservice, we can send requests asynchronously to the message broker, until the notification microservice is up again.

**What is Kafka Kraft used for?**
- Kafka Kraft is a tool that extends the capabilities of Apache Kafka, a distributed event streaming platform.
- It is used to simplify and enhance the process of building scalable and reliable data streaming applications.
- Kafka Kraft provides additional features and functionalities that make working with Kafka more efficient and effective.
- It optimizes performance to handle high data throughput and low-latency requirements, making it suitable for real-time streaming applications.

<img src="https://miro.medium.com/v2/resize:fit:1042/0*ubsIB5P8egtyni9g.png" width="500"  />

**Setting Up Kafka with Docker Compose**
- Install Docker Desktop: Ensure you have Docker Desktop installed on your machine. If you don't have it installed, follow the Docker Desktop installation instructions for your operating system.
- Start Kafka: Open your terminal or command prompt and navigate to the directory containing the Docker Compose file. Run the following command to start the Keycloak container:
```
docker-compose up -d kafka
```
- After the kafka container run, you can use the kafka in the project.
  
<br/>

## Keycloak
Keycloak is an important part of our microservices architecture, helping us manage user identity and access. It simplifies user authentication, access control, and single sign-on (SSO) for our applications. By using Keycloak, we can centrally handle user identities, making it easier to authenticate and authorize users in our microservices.

**Features and Benefits:**

- User Authentication: Keycloak supports various secure ways to access our services, including username/password, social login, and multi-factor authentication.

- Role-Based Access Control: We can set specific access rules based on user roles, allowing or denying access to certain microservices or parts of the application.

- Single Sign-On (SSO): With Keycloak's SSO feature, users only need to sign in once to access multiple services, saving time and improving security.

- Integration with Spring Security: Keycloak works well with Spring Security, making it simpler to set up and manage user authentication and access in our microservices.

- Centralized User Management: Keycloak provides an easy-to-use interface to manage user accounts and permissions from a single place.

<img src="https://walkingtree.tech/wp-content/uploads/2021/11/Untitled-presentation.png" width="500" />

**Setting Up Keycloak with Docker Compose**
- Install Docker Desktop: Ensure you have Docker Desktop installed on your machine. If you don't have it installed, follow the Docker Desktop installation instructions for your operating system.
  
- Start Keycloak: Open your terminal or command prompt and navigate to the directory containing the Docker Compose file. Run the following command to start the Keycloak container:
```
docker-compose up -d keycloak
```
- Access Keycloak Interface: Open your web browser and navigate to http://localhost:8080/auth. You will be redirected to the Keycloak login page.
  
- Log in to Keycloak: Log in using the admin username and password specified in the Docker Compose file or its environment variables. For example, the default username and password could be "admin" for both.
  
- Create Realm: Once logged in, you can create a new realm for your project. A realm is a security domain where your users, roles, and clients are defined. Click on the "Create realm" button to create a new realm.
  
- Manage Clients and Users: Within the realm, you can manage clients (applications that use Keycloak for authentication) and users. Create clients for your microservices and add users to the realm as needed.
  
- Configure Clients: For each client, you can configure settings like redirect URIs, client scopes, and protocol mappers to tailor the authentication flow to your microservices' needs.
  
- Save Changes: Make sure to save your configurations and settings within Keycloak.

<img src="https://www.keycloak.org/resources/images/guides/add-user.png" width="600" />

<br/>

## Prometheus
Prometheus is a powerful open-source monitoring and alerting toolkit that we've integrated into our microservices architecture. It provides comprehensive metrics collection and allows us to monitor the performance and health of our services effectively. With Prometheus, we can set up custom metrics and visualize the collected data using Grafana dashboards.

To enable metrics gathering in our Spring Boot applications, we leverage two key dependencies:
```
<dependency>
   <groupId>io.micrometer</groupId>
   <artifactId>micrometer-registry-prometheus</artifactId>
   <version>${micrometer.version}</version>
</dependency>
<dependency>
   <groupId>org.springframework.boot</groupId>
   <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```
**spring-boot-starter-actuator:** Spring Boot starter module that provides production-ready features for monitoring and managing our applications. It includes various endpoints, such as /actuator/metrics, that expose application metrics collected by Micrometer. Additionally, the Actuator provides health checks and other useful information about our application.

**micrometer-registry-prometheus:** The micrometer-registry-prometheus dependency is a part of Micrometer, a metrics instrumentation library used in Spring Boot applications. It allows us to export application metrics in a format that Prometheus can scrape and store. 

### Configuring Prometheus
A file named prometheus.yml is used to configure Prometheus. In this file, you can specify from which sources Prometheus should receive metric data, how often it should receive it, what rules it should apply, and what settings it should use.

prometheus.yml file contains the following sections:

- **global:** Specifies the general settings of Prometheus. For example, scrape_interval specifies the interval at which Prometheus will receive metric data, evaluation_interval specifies the interval at which Prometheus will evaluate rules.

- **rule_files:** Specifies the list of rule files that Prometheus will apply. Rule files allow Prometheus to perform calculations on metric data and send warning notifications.

- **scrape_configs:** Specifies a list of sources from which Prometheus will retrieve metric data. For each source, a job_name, a scrape_interval, a metrics_path and one or more targets are specified. Job_name specifies the name of the source, scrape_interval specifies the interval at which to retrieve metrics data from the source, metrics_path specifies the path where the source delivers its metrics data, and target specifies the address of the source.

```
global:
  scrape_interval: 15s # Default is every 1 minute.
  evaluation_interval: 15s # Evaluate rules every 15 seconds. The default is every 1 minute.

rule_files:
# - "first_rules.yml"
# - "second_rules.yml"

scrape_configs:
  - job_name: 'filter-service'
    scrape_interval: 2s
    metrics_path: '/FILTER-SERVICE/actuator/prometheus'
    static_configs:
      - targets: [ '192.168.186.28:9010' ]
  - job_name: 'inventory-service'
    scrape_interval: 2s
    metrics_path: '/INVENTORY-SERVICE/actuator/prometheus'
    static_configs:
      - targets: [ '192.168.186.28:9010' ]
```
For example, in this code, two resources are defined in the prometheus.yml file: filter-service and inventory-service. Both are Spring Boot applications running at 192.168.186.28:9010 and serving metric data in the path /actuator/prometheus. Prometheus will receive metric data from these sources every 2 seconds.

<img src="https://i.ibb.co/T1ftJYn/act.png" width="600" />

## Grafana
Grafana is a powerful open source monitoring and visualisation tool that we use in our microservice architecture. It allows us to query, visualise and alert on our application metrics collected by Prometheus. Grafana's intuitive and customisable dashboards allow us to gain real-time information about the health and performance of our services.

### Key Features

- **Dashboard Visualization:** Grafana provides a user-friendly interface to create custom dashboards that visualize metrics in real-time. We can design graphs, charts, and tables to monitor various aspects of our microservices, facilitating quick decision-making and troubleshooting. After adding your data source, you can make use of the ready-made panels offered by Grafana or create your own panels.

- **Alerting and Notifications:** Grafana offers flexible alerting rules that allow us to set up alerts based on specific thresholds or conditions. We receive notifications via email, Slack, or other communication channels when critical metrics breach predefined thresholds.

- **Data Source Integration:** Grafana integrates seamlessly with various data sources, and in our case, it fetches data from Prometheus. This integration enables us to explore, analyze, and graph metrics collected from multiple microservices in a centralized location.

- **Plugin Support:** Grafana's extensible nature allows us to extend its capabilities through plugins. We can explore and install various community-contributed plugins to enhance the monitoring experience and meet specific requirements.

<img src="https://www.linkpicture.com/q/ds_19.png" width="800" />

<img src="https://www.linkpicture.com/q/grafana.png" width="800" />

## Zipkin
Zipkin is a distributed tracing system that we've integrated into our microservices architecture. It helps us track and analyze the flow of requests across various microservices, providing valuable insights into the performance and behavior of our distributed system. By tracing the journey of a request as it passes through different services, we can identify bottlenecks, diagnose issues, and optimize the overall system.

To enable observation and tracing capabilities in our microservices architecture, we need to include the following dependencies:

```
<dependency>
   <groupId>io.micrometer</groupId>
   <artifactId>micrometer-observation</artifactId>
</dependency>
<dependency>
   <groupId>io.micrometer</groupId>
   <artifactId>micrometer-tracing-bridge-brave</artifactId>
</dependency>
<dependency>
   <groupId>io.zipkin.reporter2</groupId>
   <artifactId>zipkin-reporter-brave</artifactId>
</dependency>
```
**micrometer-observation:** This library provides essential observability features for our application, enabling the collection and reporting of metrics and statistics about our system's behavior and performance.

**micrometer-tracing-bridge-brave:** This dependency acts as a bridge between Micrometer and Brave, a distributed tracing library. It allows us to integrate distributed tracing with our Micrometer-based metrics, enabling us to observe the flow of requests across microservices.

**zipkin-reporter-brave:** This dependency provides the necessary components for reporting traces to the Zipkin server. It works in conjunction with the micrometer-tracing-bridge-brave to send tracing data to the Zipkin endpoint.

### Key Features

- **Request Tracing:** Zipkin captures the timing and sequence of requests and their corresponding responses as they flow through the microservices. This allows us to visualize the entire request flow and identify the path taken by each request.

- **Latency Analysis:** With Zipkin, we can measure the latency of each service in the request chain. This helps us identify services that might be causing delays and optimize their performance.

- **Dependency Mapping:** Zipkin creates a dependency map of all our microservices based on request traces. This mapping provides a clear picture of the interactions between services, helping us understand the dependencies within our system.

### Zipkin Configuration
Configure your application's application.yml file with the provided settings. Upon running your microservices, the metrics endpoint will be accessible to Prometheus for data scraping, and traces will be sent to the specified Zipkin endpoint for distributed tracing.

```
management:
  endpoint:
    metrics:
      enabled: true
    prometheus:
      enabled: true
  endpoints:
    web:
      exposure:
        include: "*"
  tracing:
    sampling:
      probability: 1.0
    zipkin:
      tracing:
        endpoint: http://{your ip address}/api/v2/spans
```

**management.endpoint.metrics.enabled:** This property indicates whether the /actuator/metrics endpoint is enabled. This endpoint presents your application's metrics data. is set to true.

**management.endpoint.prometheus.enabled:** This property indicates whether the /actuator/prometheus endpoint is enabled. This endpoint presents metric data in a format understandable by Prometheus. is set to true.

**management.endpoints.web.exposure.include:** This property specifies the list of management endpoints accessible via the web. It is set to "*", which means that all endpoints are accessible.

**management.tracing.sampling.probability:** This property specifies the sampling rate of tracing data. It takes a value between 0 and 1. It is set to 1.0, which means that all requests are traced.

**management.tracing.zipkin.tracing.endpoint:** This property specifies the address of the Zipkin server. The Zipkin server is a service through which your application collects and stores tracing data.

<img src="https://www.linkpicture.com/q/zipki.png" width="800" />

<img src="https://www.linkpicture.com/q/zipkin2.png" width="800" />
