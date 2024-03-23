package be.continuum.cookingbook.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;

@OpenAPIDefinition(
        info = @Info(
                title = "CookingBook",
                version = "1.0",
                description = "REST API - The CookingBook Service",
                summary = "CookingBook is an exercise I made during a 4-day springboot training during my internship." +
                        "The project serves as an API for managing and sharing cooking recipes. It is built using Spring Boot and uses MySQL as the database." +
                        " The focus of the project was to learn about Spring Boot and RESTful APIs. " +
                        "But also how to write clean, consistent code and how to use best practices." +
                        "I value this exercise because it showed me how to write more proper code, how to more efficiently use the Spring Boot framework.",
                license = @io.swagger.v3.oas.annotations.info.License(name = "MIT", url = "https://github.com/BrentSimons/spring-CookingBook/blob/main/LICENSE")

        ),
        tags = {
                @Tag(name = "Recipes"),
                @Tag(name = "Ingredients")
        }
)
public class OpenApiDocumentation {
}
