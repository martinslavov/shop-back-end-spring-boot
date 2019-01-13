# e-Shop - Back-end

## Description

This project demonstrates a back-end of e-commerce application. This project was generated with [Spring Boot](https://spring.io/projects/spring-boot).

## Prerequisites
- JDK 1.8+
- Maven 3.5+
- MySQL 8+

## Stack
- Spring Boot
- Spring Data JPA
- Spring Data REST
- Spring Security
- Hibernate
- MySQL

## Run
This application is packaged as a war which has Tomcat 8 embedded. No Tomcat or JBoss installation is necessary. You run it using the java -jar command.

Clone this repository
Make sure you are using JDK 1.8 and Maven 3.x
You can build the project and run the tests by running mvn clean package
Once successfully built, you can run the service by one of these two methods:

`java -jar target\api-0.0.1-SNAPSHOT.jar`

or

`mvn clean spring-boot:run`

## Live Demo
### Website
http://moniks.com/
or
http://moniks.linux-sys-adm.com/

### Shop
http://moniks.linux-sys-adm.com/shop/category

### Endpoints

| Method | Url | Decription |
| ------ | --- | ---------- |
| POST    |/api/token/generate-token  | get token |
| GET    |/api/categories| get all categories |
| GET    |/api/categories/active| get all ACTIVE categories |
| GET    |/api/categories/{id}| get categories by id|
| GET    |/api/categories/name/{name}| get categories by name|
| PUT    |/api/categories/{id}/disable-enable| disable/enable category by id|
| UPDATE    |/api/categories/| update category |
| DELETE    |/api/categories/| delete category |
| GET    |/api/products| get all products |
| GET    |/api/products/active| get all ACTIVE products |
| GET    |/api/products{id}| get products by id|
| GET    |/api/products/name/{name}| get products by name|
| GET    |/api/products/{id}/disable-enable| disable/enable products by id|
| UPDATE    |/api/products/| update products |
| DELETE    |/api/products/| delete products |




### Example 
1. First you have ti get token - with the username - `test` you can only read, because the user is not an administrator.
` curl -X POST \
  http://localhost:8080/api/token/generate-token \
  -H 'Cache-Control: no-cache' \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: 7d37cb0a-ce5b-4f5e-a72e-3922211323ad' \
  -d ' { "username": "test", "password": "test" } '`

2. Get all categories
` curl -X GET \
  http://localhost:8080/api/categories \
  -H 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyMSIsInNjb3BlcyI6IlJPTEVfQURNSU4iLCJpYXQiOjE1MzMyOTg0NzYsImV4cCI6MTUzMzMxNjQ3Nn0.TBlND74voK0BZ2U7T4IIV0m6bF-MB3zSPdMZbHgsvvo' \
  -H 'Cache-Control: no-cache' \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: 1077230f-9ff4-4d39-b615-a55bb914c4c0'`

3. Get all active categories
` curl -X GET \
  http://localhost:8080/api/categories/active \
  -H 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyMSIsInNjb3BlcyI6IlJPTEVfQURNSU4iLCJpYXQiOjE1MzMyOTg0NzYsImV4cCI6MTUzMzMxNjQ3Nn0.TBlND74voK0BZ2U7T4IIV0m6bF-MB3zSPdMZbHgsvvo' \
  -H 'Cache-Control: no-cache' \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: 1077230f-9ff4-4d39-b615-a55bb914c4c0'`

4. Get categoriy by id
` curl -X GET \
  http://localhost:8080/api/categories/1 \
  -H 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyMSIsInNjb3BlcyI6IlJPTEVfQURNSU4iLCJpYXQiOjE1MzMyOTg0NzYsImV4cCI6MTUzMzMxNjQ3Nn0.TBlND74voK0BZ2U7T4IIV0m6bF-MB3zSPdMZbHgsvvo' \
  -H 'Cache-Control: no-cache' \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: 94442d14-7ab8-466f-99de-be77709dbb59'`

5. Get categoriy by name
`curl -X GET \
  'http://localhost:8080/api/products/category-name/Cloth%20&%20Linen%20Products' \
  -H 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyMSIsInNjb3BlcyI6IlJPTEVfQURNSU4iLCJpYXQiOjE1MzMyOTg0NzYsImV4cCI6MTUzMzMxNjQ3Nn0.TBlND74voK0BZ2U7T4IIV0m6bF-MB3zSPdMZbHgsvvo' \
  -H 'Cache-Control: no-cache' \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: 6222a30c-36a9-4fe1-868d-bfb18617560a'`

6. Update category
` curl -X PUT \
  http://localhost:8080/api/categories \
  -H 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyMSIsInNjb3BlcyI6IlJPTEVfQURNSU4iLCJpYXQiOjE1MzMyOTg0NzYsImV4cCI6MTUzMzMxNjQ3Nn0.TBlND74voK0BZ2U7T4IIV0m6bF-MB3zSPdMZbHgsvvo' \
  -H 'Cache-Control: no-cache' \
  -H 'Content-Type: application/json' \
  -H 'Postman-Token: ae23c57b-b735-46db-9362-2ceac49d4062' \
  -d ' {
        "id": 1,
        "categoryId": 15,
        "text": {
            "ru": {
                "id": 3,
                "locale": "ru",
                "title": "No RU Text",
                "text": "No RU Text",
                "description": "No RU Text"
            },
            "bg": {
                "id": 2,
                "locale": "bg",
                "title": "No BG Text",
                "text": "No BG Text",
                "description": "No BG Text"
            },
            "en": {
                "id": 1,
                "locale": "en",
                "title": "Cloth & Linen Products",
                "text": "Cloth & Linen Products",
                "description": "Cloth & Linen Products"
            }
        },
        "enabled": false
    }'`

## Authors

* **[Martin Slavov](https://www.linkedin.com/in/slavovmartin)** - *Shop - Back-end Spring Boot REST API*


## License

This project is licensed under the MIT License - see the [LICENSE.md](https://opensource.org/licenses/MIT) file for details
