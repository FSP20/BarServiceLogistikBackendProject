package com.example.sprotte.datahandling.product.service;

import com.example.sprotte.dto.product.SaveNewRecipeDto;
import com.example.sprotte.dto.product.UpdateRecipeDto;
import com.example.sprotte.entity.Recipe;

import java.util.List;

public interface RecipeService {

    List<Recipe> getRecipes();

    Recipe saveRecipe(SaveNewRecipeDto dto);

    Recipe findRecipeById(Long recipeId);

    Recipe updateRecipe(UpdateRecipeDto dto);

    Recipe updateRecipeName(Long recipeId, String recipeName);

    String deleteRecipeById(Long recipeId);

    Recipe addIngredientToRecipe(Long recipeId, Long ingredientId);

    Recipe removeIngredientFromRecipe(Long recipeId, Long ingredientId);
}
