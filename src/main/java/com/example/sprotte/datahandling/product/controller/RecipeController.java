package com.example.sprotte.datahandling.product.controller;

import com.example.sprotte.datahandling.product.service.RecipeService;
import com.example.sprotte.dto.product.SaveNewRecipeDto;
import com.example.sprotte.dto.product.UpdateRecipeDto;
import com.example.sprotte.entity.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recipe")
public class RecipeController {

    @Autowired
    RecipeService recipeService;

    @GetMapping("/getRecipes")
    List<Recipe> getRecipes(){
        return recipeService.getRecipes();
    }

    @PostMapping("/saveRecipe")
    Recipe saveRecipe(@RequestBody SaveNewRecipeDto dto) {
        return recipeService.saveRecipe(dto);
    }

    @GetMapping("/findRecipeById/{id}")
    Recipe findRecipeById(@PathVariable("id") Long recipeId) {
        return recipeService.findRecipeById(recipeId);
    }

    @PutMapping("/updateRecipe")
    Recipe updateRecipe(@RequestBody UpdateRecipeDto dto) {
        return recipeService.updateRecipe(dto);
    }

    @PutMapping("/updateRecipeName/{id}/{name}")
    Recipe updateRecipeName(@PathVariable("id") Long recipeId, @PathVariable("name") String recipeName) {
        return recipeService.updateRecipeName(recipeId, recipeName);
    }

    @DeleteMapping("/deleteRecipeById/{id}")
    String deleteRecipeById(@PathVariable("id") Long recipeId) {
        return recipeService.deleteRecipeById(recipeId);
    }

    @PutMapping("/addIngredientToRecipe/{recipeId}/{ingredientId}")
    Recipe addIngredientToRecipe(@PathVariable("recipeId") Long recipeId, @PathVariable("ingredientId") Long ingredientId) {
        return recipeService.addIngredientToRecipe(recipeId, ingredientId);
    }

    @PutMapping("/removeIngredientFromRecipe/{recipeId}/{ingredientId}")
    Recipe removeIngredientFromRecipe(@PathVariable("recipeId") Long recipeId, @PathVariable("ingredientId") Long ingredientId) {
        return recipeService.removeIngredientFromRecipe(recipeId, ingredientId);
    }
}
