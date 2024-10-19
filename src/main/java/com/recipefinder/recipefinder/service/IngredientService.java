package com.recipefinder.recipefinder.service;

import com.recipefinder.recipefinder.model.Ingredient;
import com.recipefinder.recipefinder.repository.IngredientRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IngredientService {
    private final IngredientRepository ingredientRepository;

    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    protected Optional<Ingredient> findIngredientByName(String name) {
        return ingredientRepository.findByName(name);
    }

    protected Ingredient saveIngredient(Ingredient newIngredient) {
        return ingredientRepository.save(newIngredient);
    }
}
