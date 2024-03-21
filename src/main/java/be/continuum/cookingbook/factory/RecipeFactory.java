package be.continuum.cookingbook.factory;

import be.continuum.cookingbook.model.Genre;
import be.continuum.cookingbook.model.Recipe;

import java.time.Year;

public class RecipeFactory {
    public static Recipe createRecipe(Long id, String uuid, String name, String description, int totalCalories, Year yearOfPublication, Genre genre) {
        return Recipe.builder().id(id).uuid(uuid).name(name).description(description).totalCalories(totalCalories).yearOfPublication(yearOfPublication).genre(genre).build();
    }
}
