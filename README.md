# FileHandler
This application this project helps in managing data imported from excel where it stores them to the in-memory database before committed to the database. This allows users to have to understand their data before their committed to the database.


## Access application online

 [FileHandler](https://rssb-code-cha.herokuapp.com/)

## Access Application locally

    ## Before Getting Started Install

- `mvn 3.x.x`
- `JDK 11`
- `REDIS 6.x.x`
- `PostGreSQl 13.x.x`


    ## Getting started

Note: Make sure redis is running, and you have configured it.

```sh
$ git clone git@github.com:niomwungeri-fabrice/rssb-code-challenge.git
$ cd rssb-code-challenge
$ mvn spring-boot:run
```
    ## After cloning repository

- `Create a .env file and copy/paste the environment variables from the sample_env.txt file that's already existent in the root project directory.`
- `Update application.properties file with local database credentials and redis`

## API Documentation

[![Run in Postman](https://run.pstmn.io/button.svg)](https://documenter.getpostman.com/view/11352687/Tzz5tyQA)

## Available Functionalities

    ## Required EndPoints

| EndPoint                     | Functionality             |
| ---------------------------- | ------------------------- |
| `POST /v1/upload`               | An endpoint to receive a file of 50,000 users.       |
| `GET /v1/redis/records?pageNumber=?&pageSize=?`              | An endpoint to display the uploaded list to `redis` with its validation failure, you can provide `pageNumber` and `pageSize`  as optional parameters but the max on page is 20, you can increase as you like for each record.        |
| `POST /v1/records/commit`     | An endpoint to be called in other to commit the list uploaded in a SQL database. |



    ## Bonus EndPoints
| EndPoint                     | Functionality             |
| ---------------------------- | ------------------------- |
| `POST /v1/create`               | Create an Account to have access login and get token.         |
| `POST /v1/login`              | Authenticate your self to get access to the rest of endpoints as there protected by `JWT`         |
| `GET /v1/db/records?pageNumber=1&pageSize=19`     | An endpoint to display the committed to `postgres db` list with its validation failure, you can provide `pageNumber` and `pageSize`  as optional parameters but the max on page is 20, you can increase as you like for each record. |
| `POST /v1/db/records/commit/free` | An endpoint to commits error-free records |
| `DELETE /v1/db/records` | Delete all records from `postgres` database |
| `DELETE /v1/redis/records`  |  Clear all records from `redis` database |

    ## Project built with:

- [`Java`](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) The Spring Framework is an application framework and inversion of control container for the Java platform. The framework's core features can be used by any Java application, but there are extensions for building web applications on top of the Java EE platform.
- [`Spring Boot`](https://spring.io/projects/spring-boot) The Spring Framework is an application framework and inversion of control container for the Java platform. The framework's core features can be used by any Java application, but there are extensions for building web applications on top of the Java EE platform.
- [`Hibernate`](https://hibernate.org/) Hibernate ORM is an object–relational mapping tool for the Java programming language. It provides a framework for mapping an object-oriented domain model to a relational database.
- [`PostgreSql`](https://www.postgresql.org/) PostgreSQL, also known as Postgres, is a free and open-source relational database management system emphasizing extensibility and SQL compliance. 
- [`Redis`](https://redis.io/) Redis is an in-memory data structure store, used as a distributed, in-memory key–value database, cache and message broker, with optional durability. Redis supports different kinds of abstract data structures, such as strings, lists, maps, sets, sorted sets, HyperLogLogs, bitmaps, streams, and spatial indices.
- [`JWT`](https://jwt.io/) JSON Web Token is a proposed Internet standard for creating data with optional signature and/or optional encryption whose payload holds JSON that asserts some number of claims. The tokens are signed either using a private secret or a public/private key.

## Reference:
- `RSSB`
