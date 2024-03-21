package be.continuum.cookingbook.convertor;

import be.continuum.cookingbook.dto.GenreJson;
import be.continuum.cookingbook.model.Genre;
import org.springframework.stereotype.Component;

@Component
public class GenreJsonConvertor {
    public Genre convert(GenreJson genreJson) {
        Genre genre = null;

        if (genreJson != null) {
            genre = Genre.valueOf(genreJson.name());
        }

        return genre;
    }
}
