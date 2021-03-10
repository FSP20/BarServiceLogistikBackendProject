package com.example.sprotte.datahandling.product.service;

import com.example.sprotte.dto.product.SaveIngredientDto;
import com.example.sprotte.entity.Ingredient;

import java.util.List;

public interface IngredientService {

    List<Ingredient> getIngredients();

    Ingredient saveIngredient(SaveIngredientDto dto);

    Ingredient findIngredientById(Long ingredientId);

    Ingredient updateIngredient(SaveIngredientDto dto);

    Ingredient updateIngredientQuantity(Long ingredientId, double quantity);

    Ingredient updateIngredientUnitOfMeasure(Long ingredientId, String unitOfMeasure);

    Ingredient updateProductInIngredient(Long ingredientId, Long productId);

    String deleteIngredientById(Long ingredientId);
}
