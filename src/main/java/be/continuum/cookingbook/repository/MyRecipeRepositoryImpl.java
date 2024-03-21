package be.continuum.cookingbook.repository;

import be.continuum.cookingbook.model.Recipe;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Year;
import java.util.List;

@Repository
@AllArgsConstructor
public class MyRecipeRepositoryImpl implements MyRecipeRepository {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Recipe> findByNameLikeAndYearOfPublication(String name, int yearOfPublication) {
        return jdbcTemplate.query("select ID, UUID, NAME, DESCRIPTION, TOTAL_CALORIES, YEAR_OF_PUBLICATION "
                + "from RECIPES " + "where NAME like ?1 "
                + "and YEAR_OF_PUBLICATION = ?2", new Object[]{"%" + name + "%", yearOfPublication}, this::recipeMapper);
    }

    private Recipe recipeMapper(ResultSet rs, int rowNum) throws SQLException {
        var recipe = new Recipe();

        recipe.setId(rs.getLong("ID"));
        recipe.setUuid(rs.getString("UUID"));
        recipe.setName(rs.getString("NAME"));
        recipe.setDescription(rs.getString("DESCRIPTION"));
        recipe.setTotalCalories(rs.getInt("TOTAL_CALORIES"));
        recipe.setYearOfPublication(Year.of(rs.getInt("YEAR_OF_PUBLICATION")));

        return recipe;
    }
}
