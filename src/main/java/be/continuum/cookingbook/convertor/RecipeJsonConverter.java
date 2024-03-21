package be.continuum.cookingbook.convertor;

import be.continuum.cookingbook.dto.RecipeJson;
import be.continuum.cookingbook.model.Recipe;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RecipeJsonConverter {
    private final GenreJsonConvertor genreJsonConvertor;
    private final IngredientJsonConvertor ingredientJsonConvertor;

    public Recipe convert(RecipeJson recipeJson) {
        Recipe recipe = null;

        if (recipeJson != null) {
            recipe = new Recipe(null, recipeJson.uuid(), recipeJson.name(), recipeJson.description(), recipeJson.totalCalories(), recipeJson.yearOfPublication(), genreJsonConvertor.convert(recipeJson.genreJson()),  ingredientJsonConvertor.convert(recipeJson.ingredients()));
        }

        return recipe;
    }
}
