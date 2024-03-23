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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Gets all recipes", tags = {"Recipes"})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Recipe Found"),
            @ApiResponse(responseCode = "404", description = "Recipe Not Found", content = @Content)})
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<RecipeJson> getRecipe() {
        return recipeConverter.convert(recipeService.find());
    }

    @Operation(summary = "Gets a recipe by uuid", tags = {"Recipes"})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Recipe Found"),
            @ApiResponse(responseCode = "404", description = "Recipe Not Found", content = @Content)})
    @GetMapping("/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public RecipeJson getRecipeByUuid(@PathVariable("uuid") String uuid) {
        return recipeConverter.convert(recipeService.findByUuid(uuid));
    }

    @Operation(summary = "Creates a recipe", tags = {"Recipes"})
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Recipe Created"),
        @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)})
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RecipeJson createRecipe(@Valid @RequestBody RecipeJson newRecipeJson) {
        Recipe recipe = recipeJsonConverter.convert(newRecipeJson);

        recipe.getIngredients().forEach(ingredient -> {
            ingredient.setUuid(java.util.UUID.randomUUID().toString());
        });

        return recipeConverter.convert(recipeService.create(recipe));
    }

    @Operation(summary = "Updates a recipe", tags = {"Recipes"})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Recipe Updated"),
        @ApiResponse(responseCode = "404", description = "Recipe Not Found", content = @Content)})
    @PutMapping("/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public RecipeJson updateRecipe(@PathVariable("uuid") String uuid, @Valid @RequestBody RecipeJson updateRecipeJson) {
        Recipe updateRecipe = recipeJsonConverter.convert(updateRecipeJson);
        return recipeConverter.convert(recipeService.update(uuid, updateRecipe));
    }

    @Operation(summary = "Deletes a recipe", tags = {"Recipes"})
    @ApiResponses(value = {@ApiResponse(responseCode = "204", description = "Recipe Deleted"),
        @ApiResponse(responseCode = "404", description = "Recipe Not Found", content = @Content)})
    @DeleteMapping("/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRecipe(@PathVariable("uuid") String uuid) {
        recipeService.delete(uuid);
    }

    @Operation(summary = "Adds an ingredient to a recipe", tags = {"Recipes"})
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Ingredient Added"),
        @ApiResponse(responseCode = "404", description = "Recipe Not Found", content = @Content)})
    @PostMapping("/{recipeUuid}/ingredients")
    @ResponseStatus(HttpStatus.CREATED)
    public RecipeJson addIngredientToRecipe(@PathVariable("recipeUuid") String recipeUuid, @Valid @RequestBody IngredientJson ingredientJson) {
        Ingredient newIngredient = ingredientJsonConvertor.convert(ingredientJson);

        return recipeConverter.convert(recipeService.addIngredientToRecipe(recipeUuid, newIngredient));
    }

    @Operation(summary = "Removes an ingredient from a recipe", tags = {"Recipes"})
    @ApiResponses(value = {@ApiResponse(responseCode = "204", description = "Ingredient Removed"),
            @ApiResponse(responseCode = "404", description = "Ingredient Not Found", content = @Content)})
    @DeleteMapping("/{recipeUuid}/ingredients/{ingredientUuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public RecipeJson removeIngredientFromRecipe(@PathVariable("recipeUuid") String recipeUuid, @PathVariable("ingredientUuid") String ingredientUuid) {
        return recipeConverter.convert(recipeService.removeIngredientFromRecipe(recipeUuid, ingredientUuid));
    }

    // Better solution would be to use criteria API for this.
    // Also, this all should be moved to the service but for the purposes of this exercise it's out of scope.
    @Operation(summary = "Searches for recipes", tags = {"Recipes"})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Recipes Found"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)})
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
//
//@Operation(
//        summary = "Gets all persons", tags = {"Persons", "Niet defined"})
//@ApiResponses(value = {
//        @ApiResponse(responseCode = "200", description = "Person Found"),
//        @ApiResponse(responseCode = "404", description = "Person Not Found",
//                content = @Content),
//        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
//})
//
//@GetMapping
//public List<PersonJson> get() {
//    return personConverter.convert(personService.find());
//}
//
//@GetMapping("/{uuid}")
//public PersonJson getByUuid(@PathVariable("uuid") String uuid) {
//    var person = personService.findByUuid(uuid);
//
//    return new PersonJson(person.getUuid(), person.getFirstName(), person.getLastName());
//}
//
//@PostMapping
//@ResponseStatus(HttpStatus.CREATED)
//public Person create(@RequestBody Person newPerson) {
//    return personService.create(newPerson);
//}
//
//@PutMapping("/{uuid}")
//public PersonJson update(@PathVariable("uuid") @Parameter(description = "Den uuid") String uuid, @RequestBody Person updatePerson) {
//    return personConverter.convert(personService.update(uuid, updatePerson));
//}
//
//@DeleteMapping("/{uuid}")
//@ResponseStatus(HttpStatus.NO_CONTENT)
//public void delete(@PathVariable("uuid") String uuid) {
//    personService.delete(uuid);
//}
//}
//
