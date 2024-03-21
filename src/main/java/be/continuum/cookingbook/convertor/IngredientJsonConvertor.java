package be.continuum.cookingbook.convertor;

import be.continuum.cookingbook.dto.IngredientJson;
import be.continuum.cookingbook.model.Ingredient;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class IngredientJsonConvertor {
    public Ingredient convert(IngredientJson ingredientJson) {
        Ingredient ingredient = null;

        if (ingredientJson != null) {
            ingredient = new Ingredient();
            ingredient.setUuid(ingredientJson.uuid());
            ingredient.setName(ingredientJson.name());
        }

        return ingredient;
    }

    public List<Ingredient> convert(List<IngredientJson> ingredientJsonList) {
        List<Ingredient> ingredientList = null;

        if (ingredientJsonList != null) {
            ingredientList = new ArrayList<>();

            for (IngredientJson ingredientJson : ingredientJsonList) {
                ingredientList.add(convert(ingredientJson));
            }
        }

        return ingredientList;
    }
}
