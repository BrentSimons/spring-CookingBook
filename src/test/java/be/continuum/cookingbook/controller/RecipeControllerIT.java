package be.continuum.cookingbook.controller;

import be.continuum.cookingbook.dto.RecipeJson;
import be.continuum.cookingbook.model.Recipe;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RecipeControllerIT {
    @Autowired
    public TestRestTemplate restTemplate;

    @Test
    void getRecipe() {
        ResponseEntity<List<RecipeJson>> responseEntity = restTemplate.exchange("/recipes", HttpMethod.GET, null, new ParameterizedTypeReference<>() {
        });

        assertThat(responseEntity).isNotNull();
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).hasSize(5);
    }
}
