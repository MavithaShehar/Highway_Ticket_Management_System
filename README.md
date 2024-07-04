# Highway Ticket Management System

## Project Description

Efficient highway ticket management is essential for smooth traffic flow and compliance with regulations. Our objective is to develop a robust, microservice-based backend application for handling ticket issuance, vehicle registration, user management, and payment processing without relying on external payment gateways. Using Spring Boot and Spring Cloud technologies, including Eureka for service discovery, a Config Server for centralized configuration management, and a Gateway for intelligent routing, the application will ensure scalability, resilience, and seamless integration of services. Comprehensive testing with Postman will validate each microservice's functionality and reliability.

## Features

- **Service Registry and Discovery**: Dynamic service registration and discovery using Eureka.
- **Configuration Management**: Centralized configuration management with Spring Cloud Config Server.
- **API Gateway**: Intelligent routing of client requests to appropriate microservices using Spring Cloud Gateway.

## Microservices

- **Ticket Service**: Manages the ticket lifecycle, including creation, status updates, and retrieval of ticket information.
- **Vehicle Service**: Handles vehicle operations such as registration, updates, and retrieval of vehicle details.
- **User Service**: Manages user and owner information, including registration, profile updates, and credential verification.
- **Payment Service**: Facilitates secure internal payment processing, validating payment details and updating ticket statuses upon successful payment confirmation.

## System Requirements

- **Java**: 11 or higher
- **Maven**: 3.6.0 or higher
- **Spring Boot**: 2.5.x
- **Spring Cloud**: 2020.x
- **MySQL**: 8.0 or higher

## Technologies Used

- Spring Boot
- Spring Cloud (Eureka, Config Server, Gateway)
- Spring Data JPA
- Hibernate
- MySQL
- Postman (for API testing)

## Postman Usage

### User

1. **POST - Save User**
    - Endpoint: `http://localhost:8080/api/v1/user`
    - Body:
      ```json
      {
       "userId": "U001",
       "userName": "ken",
       "password": "117",
       "email": "ken.doe@example.com",
       "phone": "+1234567890",
       "address": "123 Main St, Springfield, USA"
      }
      ```

2. **PUT - Update User**
    - Endpoint: `http://localhost:8080/api/v1/user`
    - Body:
      ```json
      {
       "userId": "U001",
       "userName": "ken",
       "password": "117",
       "email": "ken.doe@example.com",
       "phone": "+1234567890",
       "address": "Colombo, Sri Lanka"
      }
      ```

3. **GET - Get User**
    - Endpoint: `http://localhost:8080/api/v1/user/U001`

### Vehicle

1. **POST - Save Vehicle**
    - Endpoint: `http://localhost:8080/api/v1/vehicle`
    - Body:
      ```json
      {
       "vehicleNo": "CB-1990",
       "vehicleType": "car",
       "vehicleName": "Toyota"
      }
      ```

2. **PUT - Update Vehicle**
    - Endpoint: `http://localhost:8080/api/v1/vehicle`
    - Body:
      ```json
      {
       "vehicleNo": "CB-1990",
       "vehicleType": "car",
       "vehicleName": "Honda"
      }
      ```

3. **GET - Get Vehicle**
    - Endpoint: `http://localhost:8080/api/v1/vehicle/CB-1990`

### Ticket

1. **POST - Save Ticket**
    - Endpoint: `http://localhost:8080/api/v1/ticket`
    - Body:
      ```json
      {
       "ticketNo": "TICKET006",
       "date": "2024-06-30T15:30:00.000Z",
       "price": 120.50,
       "fromWhere": "Sri",
       "toWhere": "Los Angeles",
       "vehicleNo": "CB-190",
       "userId": "U001"
      }
      ```

2. **PUT - Update Ticket**
    - Endpoint: `http://localhost:8080/api/v1/ticket`
    - Body:
      ```json
      {
       "ticketNo": "TICKET006",
       "date": "2024-06-30T15:30:00.000Z",
       "price": 120.50,
       "fromWhere": "Sri",
       "toWhere": "Los Angeles",
       "vehicleNo": "CB-190",
       "userId": "U001"
      }
      ```

3. **GET - Get Ticket**
    - Endpoint: `http://localhost:8080/api/v1/ticket/TICKET006`

### Payment

1. **POST - Save Payment**
    - Endpoint: `http://localhost:8080/api/v1/payment`
    - Body:
      ```json
      {
       "paymentNo": "PAY0012",
       "date": "2024-07-02T14:48:00.000Z",
       "given_Amount": 500.00,
       "ticketNo": "TICKET006",
       "userId": "U001",
       "vehicleNo": "CB-1990"
      }
      ```

## API Documentation

The Postman collection JSON file is included in the main directory of the repository. This collection contains all API endpoints with detailed requests and responses.

[Postman Documentation Link](https://documenter.getpostman.com/view/30897079/2sA3dxEXmE)

