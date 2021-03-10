package com.example.sprotte.datahandling.product.controller;

import com.example.sprotte.datahandling.product.service.IngredientService;
import com.example.sprotte.dto.product.SaveIngredientDto;
import com.example.sprotte.entity.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ingredient")
public class IngredientController {

    @Autowired
    IngredientService ingredientService;

    @GetMapping("/getIngredients")
    List<Ingredient> getIngredients() {
        return ingredientService.getIngredients();
    }

    @PostMapping("/saveIngredient")
    Ingredient saveIngredient(@RequestBody SaveIngredientDto dto) {
        return ingredientService.saveIngredient(dto);
    }

    @GetMapping("/findIngredientById/{id}")
    Ingredient findIngredientById(@PathVariable("id") Long ingredientId) {
        return ingredientService.findIngredientById(ingredientId);
    }

    @PutMapping("/updateIngredient")
    Ingredient updateIngredient(@RequestBody SaveIngredientDto dto) {
        return ingredientService.updateIngredient(dto);
    }

    @PutMapping("/updateIngredientQuantity/{id}/{quantity}")
    Ingredient updateIngredientQuantity(@PathVariable("id") Long ingredientId, @PathVariable("quantity") double quantity) {
        return ingredientService.updateIngredientQuantity(ingredientId, quantity);
    }

    @PutMapping("/updateIngredientUnitOfMeasure/{id}/{unitOfMeasure}")
    Ingredient updateIngredientUnitOfMeasure(@PathVariable("id") Long ingredientId, @PathVariable("unitOfMeasure") String unitOfMeasure) {
        return ingredientService.updateIngredientUnitOfMeasure(ingredientId, unitOfMeasure);
    }

    @PutMapping("/updateProductInIngredient/{ingredientId}/{productId}")
    Ingredient updateProductInIngredient(@PathVariable("ingredientId") Long ingredientId,
                                         @PathVariable("productId") Long productId) {
        return ingredientService.updateProductInIngredient(ingredientId, productId);
    }

    @DeleteMapping("/deleteIngredientById/{id}")
    String deleteIngredientById(@PathVariable("id") Long ingredientId) {
        return ingredientService.deleteIngredientById(ingredientId);
    }
}
