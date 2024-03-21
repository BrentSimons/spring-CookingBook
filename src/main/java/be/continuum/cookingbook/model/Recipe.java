package be.continuum.cookingbook.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Year;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "RECIPES")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    public Long id;

    @Column(name = "UUID")
    public String uuid;

    @Column(name = "NAME")
    public String name;

    @Column(name = "DESCRIPTION")
    public String description;

    @Column(name = "TOTAL_CALORIES")
    public int totalCalories;

    @Column(name = "YEAR_OF_PUBLICATION")
    public Year yearOfPublication;

    @Column(name = "GENRE")
    @Enumerated(EnumType.STRING)
    public Genre genre;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "RECIPE_ID")
    public List<Ingredient> ingredients;
}
