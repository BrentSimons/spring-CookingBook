package be.continuum.cookingbook.convertor;

import be.continuum.cookingbook.dto.RecipeJson;
import be.continuum.cookingbook.model.Recipe;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class RecipeConverter {

    private final GenreConvertor genreConvertor;
    private final IngredientConvertor ingredientConvertor;
    public RecipeJson convert(Recipe recipe) {
        RecipeJson recipeJson = null;

        if (recipe != null) {
            recipeJson = new RecipeJson(recipe.getUuid(),
                    recipe.getName(),
                    recipe.getDescription(),
                    recipe.getTotalCalories(),
                    recipe.getYearOfPublication(),
                    genreConvertor.convert(recipe.getGenre()),
                    ingredientConvertor.convert(recipe.getIngredients()));
        }

        return recipeJson;
    }

    public List<RecipeJson> convert(List<Recipe> recipeList) {
        List<RecipeJson> recipeJsonList = null;

        if (recipeList != null) {
            recipeJsonList = new ArrayList<>();

            for (Recipe recipe : recipeList) {
                recipeJsonList.add(convert(recipe));
            }
        }

        return recipeJsonList;
    }
}




