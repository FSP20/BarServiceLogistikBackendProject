package com.example.sprotte.dto.product;

import java.util.List;

public class UpdateRecipeDto {

    private Long recipeId;
    private String description;
    private List<Long> ingredientIdList;

    public UpdateRecipeDto(Long recipeId, String description, List<Long> ingredientIdList) {
        this.recipeId = recipeId;
        this.description = description;
        this.ingredientIdList = ingredientIdList;
    }

    public Long getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Long recipeId) {
        this.recipeId = recipeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Long> getIngredientIdList() {
        return ingredientIdList;
    }

    public void setIngredientIdList(List<Long> ingredientIdList) {
        this.ingredientIdList = ingredientIdList;
    }
}
