package be.continuum.cookingbook.repository;

import be.continuum.cookingbook.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long>{
    Optional<Ingredient> findByUuid(String uuid);
}
