package be.continuum.cookingbook.convertor;

import be.continuum.cookingbook.dto.IngredientJson;
import be.continuum.cookingbook.model.Ingredient;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class IngredientConvertor {
    public IngredientJson convert(Ingredient ingredient) {
        IngredientJson ingredientJson = null;

        if (ingredient != null) {
            ingredientJson = new IngredientJson(ingredient.getUuid(), ingredient.getName());
        }

        return ingredientJson;
    }

    public List<IngredientJson> convert(List<Ingredient> ingredientList) {
        List<IngredientJson> ingredientJsonList = null;

        if (ingredientList != null) {
            ingredientJsonList = new ArrayList<>();

            for (Ingredient ingredient : ingredientList) {
                ingredientJsonList.add(convert(ingredient));
            }
        }

        return ingredientJsonList;
    }
}
