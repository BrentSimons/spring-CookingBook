package be.continuum.cookingbook.factory;

import be.continuum.cookingbook.model.Ingredient;

public class IngredientFactory {
    public static Ingredient createIngredient(Long id, String uuid, String name) {
        return Ingredient.builder().id(id).uuid(uuid).name(name).build();
    }
}
