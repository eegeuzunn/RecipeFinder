package com.recipefinder.recipefinder.dto.converter;


import com.recipefinder.recipefinder.dto.IngredientDto;
import com.recipefinder.recipefinder.model.Ingredient;
import org.springframework.stereotype.Component;

@Component
public class IngredientConverter {

    public IngredientDto convertToDto(Ingredient ingredient) {
        return new IngredientDto(ingredient.getName(), ingredient.getId());
    }

}
