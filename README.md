# Challenge: Create a SpringBoot API to register users

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

## Funcionalities

## Create a new User

`POST /user`

curl --location 'localhost:8080/user-register-service/api/users' \
--header 'Content-Type: application/json' \
--data-raw '{
"name": "Eli Leite de Brito Filho",
"email": "elileite9@gmail.com",
"birthDate": "22/07/1994",
"address": "Rua Barão de São Francisco 186",
"abilities": ["A", "B", "C"]

}'

### Response
HTTP/1.1 201 Created