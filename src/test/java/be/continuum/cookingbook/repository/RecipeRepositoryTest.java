package be.continuum.cookingbook.repository;

import be.continuum.cookingbook.model.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class RecipeRepositoryTest {

    @Mock
    private RecipeRepository recipeRepository;

    void testFindByUuid() {
        // Given
        Recipe recipe = new Recipe();
        recipe.setUuid("test-uuid");

        // When
        when(recipeRepository.findByUuid("test-uuid")).thenReturn(Optional.of(recipe));

        // Then
        Optional<Recipe> foundRecipe = recipeRepository.findByUuid("test-uuid");

        assertTrue(foundRecipe.isPresent());
        assertEquals("test-uuid", foundRecipe.get().getUuid());
    }

}