package com.example.sprotte.entity;

import com.example.sprotte.constants.DatabaseConstants;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = DatabaseConstants.RECIPE_TABLE)
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = DatabaseConstants.ID_RECIPE)
    private Long id;

    @Column(name = DatabaseConstants.RECIPE_DESCRIPTION)
    private String description;

    // Uni-Directional
    @ManyToMany(fetch=FetchType.LAZY, cascade= {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name=DatabaseConstants.RECIPE_HAS_INGREDIENTS,
            joinColumns=@JoinColumn(name=DatabaseConstants.ID_RECIPE, insertable = false, updatable = true),
            inverseJoinColumns=@JoinColumn(name=DatabaseConstants.ID_INGREDIENT, insertable = false, updatable = true))
    private List<Ingredient> ingredients = new ArrayList<Ingredient>();

    public Recipe() {

    }

    public Recipe(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}
