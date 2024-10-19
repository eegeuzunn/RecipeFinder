package com.recipefinder.recipefinder.service;

import com.recipefinder.recipefinder.dto.RecipeDto;
import com.recipefinder.recipefinder.dto.converter.RecipeConverter;
import com.recipefinder.recipefinder.exception.InstanceAlreadyExistsException;
import com.recipefinder.recipefinder.exception.SourceNotFoundException;
import com.recipefinder.recipefinder.model.Ingredient;
import com.recipefinder.recipefinder.model.Recipe;
import com.recipefinder.recipefinder.repository.IngredientRepository;
import com.recipefinder.recipefinder.repository.RecipeRepository;
import com.recipefinder.recipefinder.request.RecipePostRequest;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final RecipeConverter recipeConverter;
    private final IngredientService ingredientService;
    private final EntityManager entityManager;

    public RecipeService(RecipeRepository recipeRepository, RecipeConverter recipeConverter, IngredientService ingredientService, EntityManager entityManager) {
        this.recipeRepository = recipeRepository;
        this.recipeConverter = recipeConverter;
        this.ingredientService = ingredientService;
        this.entityManager = entityManager;
    }

    public List<RecipeDto> getAllRecipes() {
        var recipes = recipeRepository.findAll();

        if (recipes.isEmpty()) {
            throw new SourceNotFoundException("No recipes found!");
        }

        return recipes.stream()
                .map(recipeConverter::convertToDto)
                .collect(Collectors.toList());
    }

    public Page<RecipeDto> getPageAllRecipes(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        var recipes = recipeRepository.findAll(pageable);

        if (recipes.isEmpty()) {
            throw new SourceNotFoundException("No recipes found!");
        }

        return recipes.map(recipeConverter::convertToDto);
    }

    @Transactional
    public RecipeDto addRecipe(RecipePostRequest recipePostRequest) {
        // Check if recipe already exists

        if ( recipeRepository.existsByName(recipePostRequest.getName()) ){
            throw new InstanceAlreadyExistsException("Recipe already exists");
        }

        // Convert ingredients to Entities

        List<Ingredient> ingredients = recipePostRequest.getIngredients().stream()
                .map(ingre -> ingredientService.findIngredientByName(ingre).orElseGet(() -> {
                    Ingredient newIngredient = new Ingredient();
                    newIngredient.setName(ingre);
                    return ingredientService.saveIngredient(newIngredient);
                }))
                .collect(Collectors.toList());

        // Convert RecipePostRequest to Recipe Entity

        var recipe = new Recipe(
                ingredients,
                recipePostRequest.getFoodCategory(),
                recipePostRequest.getInstructions(),
                recipePostRequest.getDescription(),
                recipePostRequest.getName());

        //Save Recipe Entity

        return recipeConverter.convertToDto(recipeRepository.save(recipe));

    }
    public void deleteRecipe(Long id) {
        if (!recipeRepository.existsById(id)) {
            throw new SourceNotFoundException("No recipe found!");
        }
        recipeRepository.deleteById(id);
    }

    public List<RecipeDto> filterRecipe(List<String> ingredients, String foodCategory, String name) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder(); // Create CriteriaBuilder
        CriteriaQuery<Recipe> q = cb.createQuery(Recipe.class);   // Create CriteriaQuery
        Root<Recipe> from = q.from(Recipe.class);          // Create Root
        // Define predicates
        List<Predicate> predicates = new ArrayList<>();
        if( ingredients != null && !ingredients.isEmpty()) {
            for(String ingredient : ingredients) {
                // Her ingredient için yeni bir join
                Join<Recipe, Ingredient> join = from.join("ingredients", JoinType.INNER);
                predicates.add(cb.equal(join.get("name"), ingredient));
            }
        }
        if(foodCategory != null) {
            predicates.add(cb.equal(from.get("foodCategory"), foodCategory));
        }
        if(name != null) {
            predicates.add(cb.equal(from.get("name"), name));
        }


        q.select(from).where(cb.and(predicates.toArray(new Predicate[0]))); // Adding predicates to query
        List<Recipe> recipes = entityManager.createQuery(q).getResultList(); // Execute query

        if( recipes.isEmpty())
            throw new SourceNotFoundException("No recipe found!");


        return recipes.stream()
                .map(recipeConverter::convertToDto)
                .collect(Collectors.toList());
    }
}
