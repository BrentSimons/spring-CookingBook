package be.continuum.cookingbook.controller;

import be.continuum.cookingbook.convertor.IngredientJsonConvertor;
import be.continuum.cookingbook.convertor.RecipeConverter;
import be.continuum.cookingbook.convertor.RecipeJsonConverter;
import be.continuum.cookingbook.dto.IngredientJson;
import be.continuum.cookingbook.dto.RecipeJson;
import be.continuum.cookingbook.exception.BadRequestException;
import be.continuum.cookingbook.model.Ingredient;
import be.continuum.cookingbook.model.Recipe;
import be.continuum.cookingbook.service.RecipeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/recipes")
public class RecipeController {
    private final RecipeService recipeService;
    private final RecipeConverter recipeConverter;
    private final RecipeJsonConverter recipeJsonConverter;
    private final IngredientJsonConvertor ingredientJsonConvertor;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<RecipeJson> getRecipe() {
        return recipeConverter.convert(recipeService.find());
    }

    @GetMapping("/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public RecipeJson getRecipeByUuid(@PathVariable("uuid") String uuid) {
        return recipeConverter.convert(recipeService.findByUuid(uuid));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RecipeJson createRecipe(@Valid @RequestBody RecipeJson newRecipeJson) {
        Recipe recipe = recipeJsonConverter.convert(newRecipeJson);

        recipe.getIngredients().forEach(ingredient -> {
            ingredient.setUuid(java.util.UUID.randomUUID().toString());
        });

        return recipeConverter.convert(recipeService.create(recipe));
    }

    @PutMapping("/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public RecipeJson updateRecipe(@PathVariable("uuid") String uuid,@Valid @RequestBody RecipeJson updateRecipeJson) {
        Recipe updateRecipe = recipeJsonConverter.convert(updateRecipeJson);
        return recipeConverter.convert(recipeService.update(uuid, updateRecipe));
    }

    @DeleteMapping("/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRecipe(@PathVariable("uuid") String uuid) {
        recipeService.delete(uuid);
    }

    @PostMapping("/{recipeUuid}/ingredients")
    @ResponseStatus(HttpStatus.CREATED)
    public RecipeJson addIngredientToRecipe(@PathVariable("recipeUuid") String recipeUuid, @Valid @RequestBody IngredientJson ingredientJson) {
        Ingredient newIngredient = ingredientJsonConvertor.convert(ingredientJson);

        return recipeConverter.convert(recipeService.addIngredientToRecipe(recipeUuid, newIngredient));
    }

    @DeleteMapping("/{recipeUuid}/ingredients/{ingredientUuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public RecipeJson removeIngredientFromRecipe(@PathVariable("recipeUuid") String recipeUuid, @PathVariable("ingredientUuid") String ingredientUuid) {
        return recipeConverter.convert(recipeService.removeIngredientFromRecipe(recipeUuid, ingredientUuid));
    }

    // Better solution would be to use criteria API for this.
    // Also, this all should be moved to the service but for the purposes of this exercise it's out of scope.
    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public List<RecipeJson> searchRecipe(@RequestParam(value = "name", required = false) String name, @RequestParam(value = "year", required = false) Integer yearFilter, @RequestParam(value = "yearEnd", required = false) Integer yearEnd) {
        StringUtils.isNotBlank(name);
        if (yearFilter != null && yearEnd != null && StringUtils.isNotBlank(name)) {
            throw new BadRequestException("Invalid parameter combination: Can't have year and yearEnd with name name.");
        }
        if (yearFilter == null && yearEnd != null) {
            throw new BadRequestException("Invalid parameter combination: Can't have yearEnd and name.");
        }
        return searchRecipes(name, yearFilter, yearEnd);
    }

    private List<RecipeJson> searchRecipes(String name, Integer yearFilter, Integer yearEnd) {
        if (name != null && yearFilter != null && yearEnd == null) {
            System.out.println("e");
            return recipeConverter.convert(recipeService.findByNameLikeAndYearOfPublication(name, yearFilter));
        } else if (yearFilter != null && yearEnd != null) {
            return recipeConverter.convert(recipeService.findByYearBetween(yearFilter, yearEnd));
        } else if (name != null) {
            return recipeConverter.convert(recipeService.findByName(name));
        } else if (yearFilter != null) {
            return recipeConverter.convert(recipeService.findByYearOfPublication(yearFilter));
        }
        return recipeConverter.convert(recipeService.find());
    }
}
