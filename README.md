# register-user-service API

## About
This is a SpringBoot API responsible to realize CRUD operation over User Entity. This API uses H2 Database to persist data in memory during program execution.

- SwaggerUI: http://localhost:8080/swagger-ui/index.html
- H2 DataBase console:
http://localhost:8080/h2-console/

## Techs
- Java 17
- H2 Database
- Lombok
- SpringBoot 3
- Swagger 3
- Mockito
- JUnit

## Methods
Requests to the API must follow:
| Method | Description |
|---|---|
| `GET` | Return one or more users. |
| `POST` | Create a new user. |
| `PUT` | Update a user. |
| `DELETE` | Delete a user. |


## Responses
| Code  | Description                   |
|-------|-------------------------------|
| `200` | Request executed successfully |
| `201` | Record created                |
| `204` | Record deleted successfully   |
| `400` | Record contains invalid data  |
| `404` | Searched record not found     |

## Functionalities

## Create User - POST [/users]
Create a new user.
+ Request (application/json)
  + Body

          {
            "name": "Julio Osvaldo Nathan Martins",
            "email": "julio-martins88@gmail.com",
            "birthDate": "25/02/1961",
            "address": "Rua Jairzinho 223",
            "abilities": ["Front-End Developer", "Back-End Developer", "Play Guitar"]
          }

+ Response 201 (CREATED)

## Update User - PUT [/users/{userId}]
Update an user by payload and id.
+ Request
  + Path Param
      UserId: ID of Entity to be changed.

  + Body

             {
               "name": "Fábio Márcio Carlos Melo",
               "email": "fabiomarciomelo@yahoo.com.br",
               "birthDate": "20/01/1952",
               "address": "Rua Tonico Saad",
               "abilities": ["Front-End Developer", "Back-End Developer", "Play Guitar"]
             }

+ Response 200 (OK)

## Find All Users - GET [/users/findAllUsers]
Get all users from H2 DataBase.

+ Response 200 (application/json)
    + Body
  
          {
          "name": "Julio Osvaldo Nathan Martins",
          "email": "julio-martins88@gmail.com",
          "birthDate": "25/02/1961",
          "address": "Rua Jairzinho 223",
          "abilities": ["Front-End Developer", "Back-End Developer", "Play Guitar"]
          } 
## Find User by ID - GET [/users/{userId}]
Get an user by id.
+ Request
    + Path Param
      UserId: ID of user to find.
+ Response 200 (application/json)
    + Body

          {
          "name": "Julio Osvaldo Nathan Martins",
          "email": "julio-martins88@gmail.com",
          "birthDate": "25/02/1961",
          "address": "Rua Jairzinho 223",
          "abilities": ["Front-End Developer", "Back-End Developer", "Play Guitar"]
          } 
## Delete User by ID - DELETE [/users/{userId}]
Delete an user by id.
+ Request
    + Path Param
      UserId: ID of user to delete.
+ Response 204 (NO_CONTENT)