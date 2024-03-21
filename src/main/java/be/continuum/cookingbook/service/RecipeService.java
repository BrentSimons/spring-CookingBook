package be.continuum.cookingbook.service;

import be.continuum.cookingbook.model.Genre;
import be.continuum.cookingbook.model.Ingredient;
import be.continuum.cookingbook.model.Recipe;
import be.continuum.cookingbook.repository.IngredientRepository;
import be.continuum.cookingbook.repository.RecipeRepository;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.List;
import java.util.UUID;

import static be.continuum.cookingbook.factory.IngredientFactory.createIngredient;
import static be.continuum.cookingbook.factory.RecipeFactory.createRecipe;

@Service
@AllArgsConstructor
public class RecipeService {
    private final RecipeRepository recipeRepository;
    private final IngredientRepository ingredientRepository;

    // Recipe methods

    public List<Recipe> find() {
        return recipeRepository.findAll();
    }

    public Recipe findByUuid(String uuid) {
        return recipeRepository.findByUuid(uuid).get();
    }

    public Recipe create(@Valid Recipe newRecipe) {
        newRecipe.setId(null);
        newRecipe.setUuid(UUID.randomUUID().toString());

        return recipeRepository.save(newRecipe);
    }

    public void delete(String uuid) {
        Recipe recipe = recipeRepository.findByUuid(uuid).get();
        recipeRepository.delete(recipe);
    }

    public Recipe update(String uuid, @Valid Recipe updateRecipe) {
        Recipe existingRecipe = recipeRepository.findByUuid(uuid).orElseThrow(() -> new IllegalArgumentException("Recipe with UUID " + uuid + " not found"));

        existingRecipe.setName(updateRecipe.getName());
        existingRecipe.setDescription(updateRecipe.getDescription());
        existingRecipe.setTotalCalories(updateRecipe.getTotalCalories());
        existingRecipe.setYearOfPublication(updateRecipe.getYearOfPublication());
        existingRecipe.setGenre(updateRecipe.getGenre());
        existingRecipe.setIngredients(updateRecipe.getIngredients());

        return recipeRepository.save(existingRecipe);
    }

    // Recipe < Ingredient methods

    public Recipe addIngredientToRecipe(String recipeUuid, Ingredient newIngredient) {
        Recipe recipe = recipeRepository.findByUuid(recipeUuid).orElseThrow(() -> new IllegalArgumentException("Recipe with UUID " + recipeUuid + " not found"));

        newIngredient.setId(null);
        newIngredient.setUuid(UUID.randomUUID().toString());

        recipe.getIngredients().add(newIngredient);

        return recipeRepository.save(recipe);
    }

    public Recipe removeIngredientFromRecipe(String recipeUuid, String ingredientUuid) {
        Ingredient ingredient = ingredientRepository.findByUuid(ingredientUuid).orElseThrow(() -> new IllegalArgumentException("Ingredient with UUID " + ingredientUuid + " not found"));

        Recipe recipe = recipeRepository.findByUuid(recipeUuid).orElseThrow(() -> new IllegalArgumentException("Recipe with UUID " + recipeUuid + " not found"));

        ingredientRepository.delete(ingredient);

        return recipe;
    }

    // Filters

    public List<Recipe> findByName(String name) {
        return recipeRepository.findByNameContains(name);
    }

    public List<Recipe> findByYearOfPublication(int yearOfPublication) {
        return recipeRepository.findByYearOfPublication(yearOfPublication);
    }

    public List<Recipe> findByYearBetween(int yearOne, int yearTwo) {
        return recipeRepository.findByYearOfPublicationIsBetween(yearOne, yearTwo);
    }

    public List<Recipe> findByNameLikeAndYearOfPublication(String name, int yearOfPublication) {
        return recipeRepository.findByNameLikeAndYearOfPublication(name, yearOfPublication);
    }
}
