package com.recipefinder.recipefinder.dto;

import com.recipefinder.recipefinder.model.Ingredient;
import jakarta.persistence.OneToMany;

import java.util.Collection;

public class RecipeDto {
    private Long id;
    private String name;
    private String description;
    private String instructions;
    private String foodCategory;
    private Collection<IngredientDto> ingredients;

    public RecipeDto() {
    }

    public RecipeDto(Long id, String name, String description, String instructions, String foodCategory, Collection<IngredientDto> ingredients) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.instructions = instructions;
        this.foodCategory = foodCategory;
        this.ingredients = ingredients;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getFoodCategory() {
        return foodCategory;
    }

    public void setFoodCategory(String foodCategory) {
        this.foodCategory = foodCategory;
    }

    public Collection<IngredientDto> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Collection<IngredientDto> ingredients) {
        this.ingredients = ingredients;
    }
}
