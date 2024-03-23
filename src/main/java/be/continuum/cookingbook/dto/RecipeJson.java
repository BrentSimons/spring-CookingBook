package be.continuum.cookingbook.dto;

import be.continuum.cookingbook.validation.YearValidator;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.time.Year;
import java.util.List;

public record RecipeJson(
        String uuid,
        @NotBlank(message = "The name of a recipe is mandatory")
        String name,
        @NotBlank(message = "The description of a recipe is mandatory")
        String description,
        @Min(value = 1, message = "The total calories of a recipe must be at least 1")
        @Max(value = 4000, message = "The total calories of a recipe must be at most 4000")
        int totalCalories,
        @YearValidator(minimum = 1900)
        Year yearOfPublication,
        GenreJson genreJson,
        List<IngredientJson> ingredients) {
}
