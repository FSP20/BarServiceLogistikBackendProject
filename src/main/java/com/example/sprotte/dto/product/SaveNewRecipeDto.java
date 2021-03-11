package com.example.sprotte.dto.product;

import java.util.List;

public class SaveNewRecipeDto {

    private String description;
    private List<Long> ingredientListId;

    public SaveNewRecipeDto(String description, List<Long> ingredientListId) {
        this.description = description;
        this.ingredientListId = ingredientListId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Long> getIngredientListId() {
        return ingredientListId;
    }

    public void setIngredientListId(List<Long> ingredientListId) {
        this.ingredientListId = ingredientListId;
    }
}
