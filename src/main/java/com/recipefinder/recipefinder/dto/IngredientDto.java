package com.recipefinder.recipefinder.dto;

public class IngredientDto {
    private Long id;
    private String name;

    public IngredientDto() {
    }

    public IngredientDto(String name, Long id) {
        this.name = name;
        this.id = id;
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
}
