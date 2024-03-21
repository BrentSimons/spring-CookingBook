package be.continuum.cookingbook.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record IngredientJson(
        String uuid,
        @NotBlank(message = "The name of an ingredient is mandatory")
        @Size(min = 1, max = 32, message = "The name of an ingredient must be between {min} and {max} characters")
        String name
) {
}
