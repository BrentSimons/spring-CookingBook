package be.continuum.cookingbook.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;

@OpenAPIDefinition(
        info = @Info(
                title = "CookingBook",
                version = "1.0",
                description = "REST API - The CookingBook Service"
        ),
        tags = {
                @Tag(name = "Recipes"),
                @Tag(name = "Ingredients")
        }
)
public class OpenApiDocumentation {
}
