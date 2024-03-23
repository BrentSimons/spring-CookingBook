package be.continuum.cookingbook.controller;

import be.continuum.cookingbook.convertor.IngredientConvertor;
import be.continuum.cookingbook.convertor.IngredientJsonConvertor;
import be.continuum.cookingbook.dto.IngredientJson;
import be.continuum.cookingbook.model.Ingredient;
import be.continuum.cookingbook.service.IngredientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/ingredients")
public class IngredientController {
    private final IngredientService ingredientService;
    private final IngredientConvertor ingredientConvertor;
    private final IngredientJsonConvertor ingredientJsonConvertor;

    @Operation(summary = "Gets all ingredients", tags = {"Ingredients"})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Ingredient Found"),
            @ApiResponse(responseCode = "404", description = "Ingredient Not Found")})
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<IngredientJson> getIngredients() {
        return ingredientConvertor.convert(ingredientService.find());
    }

    @Operation(summary = "Gets an ingredient by uuid", tags = {"Ingredients"})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Ingredient Found"),
            @ApiResponse(responseCode = "404", description = "Ingredient Not Found")})
    @GetMapping("/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public IngredientJson getIngredientByUuid(@PathVariable("uuid") String uuid) {
        return ingredientConvertor.convert(ingredientService.findByUuid(uuid));
    }

    @Operation(summary = "Creates an ingredient", tags = {"Ingredients"})
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Ingredient Created"),
            @ApiResponse(responseCode = "400", description = "Bad Request")})
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public IngredientJson createIngredient(@RequestBody @Valid IngredientJson newIngredientJson) {
        Ingredient newIngredient = ingredientJsonConvertor.convert(newIngredientJson);

        return ingredientConvertor.convert(ingredientService.create(newIngredient));
    }

    @Operation(summary = "Updates an ingredient", tags = {"Ingredients"})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Ingredient Updated"),
            @ApiResponse(responseCode = "400", description = "Bad Request")})
    @PutMapping("/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public IngredientJson updateIngredient(@PathVariable("uuid") String uuid, @RequestBody @Valid IngredientJson updateIngredientJson) {
        Ingredient updateIngredient = ingredientJsonConvertor.convert(updateIngredientJson);

        return ingredientConvertor.convert(ingredientService.update(uuid, updateIngredient));
    }

    @Operation(summary = "Deletes an ingredient", tags = {"Ingredients"})
    @ApiResponses(value = {@ApiResponse(responseCode = "204", description = "Ingredient Deleted"),
            @ApiResponse(responseCode = "404", description = "Ingredient Not Found")})
    @DeleteMapping("/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteIngredient(@PathVariable("uuid") String uuid) {
        ingredientService.delete(uuid);
    }

}
