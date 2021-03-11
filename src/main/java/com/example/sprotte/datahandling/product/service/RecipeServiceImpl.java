package com.example.sprotte.datahandling.product.service;

import com.example.sprotte.constants.ResponseMessageConstants;
import com.example.sprotte.datahandling.product.repository.IngredientRepository;
import com.example.sprotte.datahandling.product.repository.RecipeRepository;
import com.example.sprotte.dto.product.SaveNewRecipeDto;
import com.example.sprotte.dto.product.UpdateRecipeDto;
import com.example.sprotte.entity.Ingredient;
import com.example.sprotte.entity.Recipe;
import com.example.sprotte.errorhandling.product.IllegalRecipeException;
import com.example.sprotte.errorhandling.product.IngredientNotFoundException;
import com.example.sprotte.errorhandling.product.RecipeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeServiceImpl implements RecipeService{

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    IngredientRepository ingredientRepository;

    @Override
    public List<Recipe> getRecipes() {
        List<Recipe> recipes = recipeRepository.findAll();
        if(recipes.size() == 0)
            throw new RecipeNotFoundException(ResponseMessageConstants.RECIPE_NOT_FOUND);

        return recipes;
    }

    @Override
    public Recipe saveRecipe(SaveNewRecipeDto dto) {
        if(dto == null)
            throw new RuntimeException(ResponseMessageConstants.RECIPE_IS_EMPTY);

        // Proof Recipe Name already exist
        if(dto.getDescription() != null) {
            Recipe recipe = recipeRepository.findByDescription(dto.getDescription());
            if(recipe != null)
                throw new IllegalRecipeException(ResponseMessageConstants.RECIPE_ALREADY_EXIST);
        }

        return recipeRepository.save(mapNewRecipeDtoToRecipe(dto));
    }

    @Override
    public Recipe findRecipeById(Long recipeId) {
        return findById(recipeId);
    }

    @Override
    public Recipe updateRecipe(UpdateRecipeDto dto) {
        if(dto == null)
            throw new RuntimeException(ResponseMessageConstants.RECIPE_IS_EMPTY);

        // Proof Recipe Name already exist
        if(dto.getDescription() != null) {
            Recipe recipe = recipeRepository.findByDescription(dto.getDescription());
            if(recipe != null)
                throw new IllegalRecipeException(ResponseMessageConstants.RECIPE_ALREADY_EXIST);
        }

        return recipeRepository.save(mapUpdateRecipeDtoToRecipe(dto));
    }

    @Override
    public Recipe updateRecipeName(Long recipeId, String recipeName) {
        Recipe recipe = findById(recipeId);

        recipe.setDescription(recipeName);

        return recipeRepository.save(recipe);
    }

    @Override
    public String deleteRecipeById(Long recipeId) {
        findById(recipeId);

        recipeRepository.deleteById(recipeId);

        return ResponseMessageConstants.RECIPE_SUCCESSFULLY_DELETE;
    }

    @Override
    public Recipe addIngredientToRecipe(Long recipeId, Long ingredientId) {
        Recipe recipe = findById(recipeId);

        Ingredient ingredient = findIngredientById(ingredientId);

        if(!recipe.getIngredients().contains(ingredient)){
            recipe.getIngredients().add(ingredient);
        }

        return recipeRepository.save(recipe);
    }

    @Override
    public Recipe removeIngredientFromRecipe(Long recipeId, Long ingredientId) {
        Recipe recipe = findById(recipeId);

        Ingredient ingredient = findIngredientById(ingredientId);

        // Update Recipe
        recipe.getIngredients().removeIf(tempIngredient -> tempIngredient.getId() == ingredient.getId());

        return recipeRepository.save(recipe);
    }

    public Recipe findById(Long recipeId) {
        Recipe recipe = recipeRepository.findById(recipeId).orElse(null);
        if (recipe == null)
            throw new RecipeNotFoundException(ResponseMessageConstants.RECIPE_NOT_FOUND);

        return recipe;
    }

    public Ingredient findIngredientById(Long ingredientId) {
        Ingredient ingredient = ingredientRepository.findById(ingredientId).orElse(null);
        if (ingredient == null)
            throw new IngredientNotFoundException(ResponseMessageConstants.INGREDIENT_NOT_FOUND);

        return ingredient;
    }

    public Recipe mapNewRecipeDtoToRecipe(SaveNewRecipeDto dto) {
        Recipe recipe = new Recipe();

        recipe.setDescription(dto.getDescription());

        for (Long ingredientId : dto.getIngredientListId()) {
            Ingredient ingredient = findIngredientById(ingredientId);
            recipe.getIngredients().add(ingredient);
        }

        return recipe;
    }

    public Recipe mapUpdateRecipeDtoToRecipe(UpdateRecipeDto dto) {
        Recipe recipe = findById(dto.getRecipeId());

        if(dto.getDescription() != null)
            recipe.setDescription(dto.getDescription());

        recipe.getIngredients().clear();
        for (Long ingredientId : dto.getIngredientIdList()) {
            Ingredient ingredient = findIngredientById(ingredientId);
            recipe.getIngredients().add(ingredient);
        }

        return recipe;
    }
}
