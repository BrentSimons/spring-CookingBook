package be.continuum.cookingbook.repository;

import be.continuum.cookingbook.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long>, MyRecipeRepository {

    Optional<Recipe> findByUuid(String uuid);

    List<Recipe> findByNameContains(String name);

    List<Recipe> findByYearOfPublication(int yearOfPublication);

    List<Recipe> findByYearOfPublicationIsBetween(int yearOne, int yearTwo);

}
