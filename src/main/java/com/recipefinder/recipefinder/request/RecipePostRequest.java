package com.recipefinder.recipefinder.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Collection;
import java.util.List;

public class RecipePostRequest {

    @NotNull
    @NotEmpty
    private String name;
    @NotNull
    @NotEmpty
    private String description;
    @NotNull
    @NotEmpty
    private String instructions;
    @NotNull
    @NotEmpty
    private String foodCategory;

    @NotNull
    private Collection<String> ingredients;

    public RecipePostRequest() {
    }

    public Collection<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Collection<String> ingredients) {
        this.ingredients = ingredients;
    }

    public String getFoodCategory() {
        return foodCategory;
    }

    public void setFoodCategory(String foodCategory) {
        this.foodCategory = foodCategory;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
