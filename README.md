# Music Sharing Platform

This is a backend rest api project for a conceptual music sharing platform that is built with Java,
SpringBoot, Spring Data JDBC & PostgreSQL.

This is created to help me hands-on with my understanding of SpringBoot application archetecitre, REST API designs,
DTOs, databases, dependency injection and service layer patterns

This application allows users to create profiles and share music content. The content is associated with 
a specific user.

## Tech Stack
* Java 
* Spring Boot 
* Spring MVC
* Spring Data JDBC
* PostgreSQL
* Maven
* JUnit 5
* Mockito
* Railway

## Current Features

### Content Management
The api supports storing and retrieving:
* Albums
* Tracks
* Mixes
* W.I.P productions

### User Management
The api supports storing and retrieving users where users publish content.

## Application Architecture
Application follows this architecture flow: 

HTTP Request -> Controller -> Service -> Repository & DTO -> PostgreSQL

### Controller Layer

Controllers have REST endpoints and handle HTTP-specific items:
* Request bodies
* Path Vars
* Response codes

### Service Layer
The services handle the business logic of requests.Some examples:
* Finds and retrives users / content
* Maps database models to DTOs

### Repository Layer
Repositories use Spring Data JDBC to communicate with PostgreSQL.
Spring Data derives several queries automatically from repository method names, such as:
findAllByTitleContains(String keyword) and findAllByUserId(Integer userId)

### DTO Layer
The application uses Data Transfer Objects instead of directly exposing database models through the API.
#### Request DTOs:

CreateContentRequest

UpdateContentRequest

CreateUserRequest

#### Response DTOs:

ContentResponse

UserResponse

This creates a separation between:

API Representation -> DTOs -> Application Logic -> Database Model

For example, clients do not provide database-generated IDs when creating content.

### Rest APIs

#### Users


| Method | Endpoint                    | Description               |
|--------|-----------------------------|---------------------------|
| GET    | /api/users                  | Retrieve all users        |   
| GET    | /api/users/{userId}         | Retrieve a user by ID     |  
| POST   | /api/users                  | Create a User             | 
| GET    | /api/users/{userId}/content | Get content from a user   | 
| POST   | /api/users/{userId}/content | Create content for a user |

#### Content

| Method | Endpoint                      | Description             |
|--------|-------------------------------|-------------------------|
| GET    | /api/content                  | Retrieve all content    |   
| GET    | /api/content/{id}             | Retrieve content by ID  |  
| PUT    | /api/content/{id}             | Update content          | 
| DELETE | /api/content/{id}             | Delete content          | 
| GET    | /api/content/filter/{keyword} | Search content by title |

