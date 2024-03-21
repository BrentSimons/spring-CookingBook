package be.continuum.cookingbook.controller;

import be.continuum.cookingbook.convertor.RecipeConverter;
import be.continuum.cookingbook.dto.RecipeJson;
import be.continuum.cookingbook.service.RecipeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RecipeControllerTest {

    @Mock
    public RecipeService recipeService;

    @Mock
    public RecipeConverter recipeConverter;

    @InjectMocks
    public RecipeController recipeController;

    @Test
    void getRecipe() {
        // Given
        List<RecipeJson> recipeList = List.of();

        when(recipeService.find()).thenReturn(List.of());

        // When
        List<RecipeJson> result = recipeController.getRecipe();

        // Then
        assertThat(result)
                .isNotNull()
                .isEqualTo(recipeList);
    }
}