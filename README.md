# CookingBook

CookingBook is an exercise I made during a 4-day springboot training during my internship. 
The project serves as an API for managing and sharing cooking recipes. 
It is built using Spring Boot and uses MySQL as the database.
The focus of the project was to learn about Spring Boot and RESTful APIs.
But also how to write clean, consistent code and how to use best practices.
I value this exercise because it showed me how to write more proper code, how to more efficiently use the Spring Boot framework.

## Technologies Used

- Java 17
- Spring Boot 3.2.3
- Maven
- MySQL
- Lombok
- Apache Commons Lang
- Springdoc OpenAPI
- Flyway

### Installing

1. Clone the repository
```bash
git clone https://github.com/BrentSimons/CookingBook.git
```

2. Navigate to the project directory
```bash
cd CookingBook
```

3. Build the project
```bash
mvn clean install
```

4. Run the application
```bash
mvn spring-boot:run
```

The application will start running at `http://localhost:8090`.

#### Swagger

This project uses Springdoc OpenAPI to generate an API documentation. You can access the Swagger UI at `http://localhost:8090/swagger-ui.html`.

## Configuration

The application's configuration is located in `src/main/resources/application.yaml`. Here you can change the server port, database connection details, and other Spring Boot settings.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details.

