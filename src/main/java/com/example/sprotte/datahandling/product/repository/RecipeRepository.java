package com.example.sprotte.datahandling.product.repository;

import com.example.sprotte.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    Recipe findByDescription(String recipeDescription);
}
