package be.continuum.cookingbook.service;

import be.continuum.cookingbook.model.Ingredient;
import be.continuum.cookingbook.repository.IngredientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class IngredientService {
    private final IngredientRepository ingredientRepository;

    public List<Ingredient> find() {
        return ingredientRepository.findAll();
    }

    public Ingredient findByUuid(String uuid) {
        return ingredientRepository.findByUuid(uuid).get();
    }

    public Ingredient create(Ingredient ingredient) {
        ingredient.setId(null);
        ingredient.setUuid(UUID.randomUUID().toString());

        return ingredientRepository.save(ingredient);
    }

    public void delete(String uuid) {
        Ingredient ingredient = ingredientRepository.findByUuid(uuid).get();
        ingredientRepository.delete(ingredient);
    }

    public Ingredient update(String uuid, Ingredient updateIngredient) {
        updateIngredient.setUuid(uuid);

        return ingredientRepository.save(updateIngredient);
    }
}
