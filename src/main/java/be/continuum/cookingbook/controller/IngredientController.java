package be.continuum.cookingbook.controller;

import be.continuum.cookingbook.convertor.IngredientConvertor;
import be.continuum.cookingbook.convertor.IngredientJsonConvertor;
import be.continuum.cookingbook.dto.IngredientJson;
import be.continuum.cookingbook.model.Ingredient;
import be.continuum.cookingbook.service.IngredientService;
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

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<IngredientJson> getIngredients() {
        return ingredientConvertor.convert(ingredientService.find());
    }

    @GetMapping("/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public IngredientJson getIngredientByUuid(@PathVariable("uuid") String uuid) {
        return ingredientConvertor.convert(ingredientService.findByUuid(uuid));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public IngredientJson createIngredient(@RequestBody @Valid IngredientJson newIngredientJson) {
        Ingredient newIngredient = ingredientJsonConvertor.convert(newIngredientJson);

        return ingredientConvertor.convert(ingredientService.create(newIngredient));
    }

    @PutMapping("/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public IngredientJson updateIngredient(@PathVariable("uuid") String uuid, @RequestBody @Valid IngredientJson updateIngredientJson) {
        Ingredient updateIngredient = ingredientJsonConvertor.convert(updateIngredientJson);

        return ingredientConvertor.convert(ingredientService.update(uuid, updateIngredient));
    }

    @DeleteMapping("/{uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteIngredient(@PathVariable("uuid") String uuid) {
        ingredientService.delete(uuid);
    }

}
