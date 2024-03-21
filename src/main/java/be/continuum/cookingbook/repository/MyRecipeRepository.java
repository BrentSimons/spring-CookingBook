package be.continuum.cookingbook.repository;

import be.continuum.cookingbook.model.Recipe;

import java.util.List;

public interface MyRecipeRepository {

    List<Recipe> findByNameLikeAndYearOfPublication(String name, int yearOfPublication);

}