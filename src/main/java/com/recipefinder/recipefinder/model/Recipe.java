package com.recipefinder.recipefinder.model;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String instructions;
    private String foodCategory;

    @ManyToMany()
    @JoinTable(name = "recipe_ingredient",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
    private Collection<Ingredient> ingredients;

    public Recipe() {
    }

    public Recipe(Collection<Ingredient> ingredients, String foodCategory, String instructions, String description, String name) {
        this.ingredients = ingredients;
        this.foodCategory = foodCategory;
        this.instructions = instructions;
        this.description = description;
        this.name = name;
    }

    // Getters and Setters
    public Collection<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Collection<Ingredient> ingredients) {
        this.ingredients = ingredients;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
