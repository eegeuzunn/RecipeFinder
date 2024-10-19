package com.recipefinder.recipefinder.dto.converter;

import com.recipefinder.recipefinder.dto.IngredientDto;
import com.recipefinder.recipefinder.dto.RecipeDto;
import com.recipefinder.recipefinder.model.Recipe;
import com.recipefinder.recipefinder.request.RecipePostRequest;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class RecipeConverter {

    private final IngredientConverter ingredientConverter;

    public RecipeConverter(IngredientConverter ingredientConverter) {
        this.ingredientConverter = ingredientConverter;
    }

    public RecipeDto convertToDto(Recipe recipe) {
        return new RecipeDto(recipe.getId(),
                recipe.getName(),
                recipe.getDescription(),
                recipe.getInstructions(),
                recipe.getFoodCategory(),
                recipe.getIngredients().stream()
                        .map(ingredientConverter::convertToDto)
                        .collect(Collectors.toList())
        );
    }
}
