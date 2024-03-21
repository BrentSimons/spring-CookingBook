package be.continuum.cookingbook.convertor;

import be.continuum.cookingbook.dto.GenreJson;
import be.continuum.cookingbook.model.Genre;
import org.springframework.stereotype.Component;

@Component
public class GenreConvertor {
    public GenreJson convert(Genre genre) {
        GenreJson genreJson = null;

        if (genre != null) {
            genreJson = GenreJson.valueOf(genre.name());
        }

        return genreJson;
    }
}
