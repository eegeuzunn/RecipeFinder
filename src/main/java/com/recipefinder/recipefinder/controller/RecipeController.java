package com.recipefinder.recipefinder.controller;

import com.recipefinder.recipefinder.exception.InstanceAlreadyExistsException;
import com.recipefinder.recipefinder.exception.SourceNotFoundException;
import com.recipefinder.recipefinder.request.RecipePostRequest;
import com.recipefinder.recipefinder.response.ApiResponse;
import com.recipefinder.recipefinder.service.RecipeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController()
@RequestMapping("api/v1")
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("recipes")
    public ResponseEntity<ApiResponse> getRecipe() {
        try{
            var recipes = recipeService.getAllRecipes();
            return ResponseEntity.ok(new ApiResponse("All recipes succesfully gathered", recipes));
        } catch (SourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("No recipe found!", null));
        }
    }

    @GetMapping("page/recipes")
    public ResponseEntity<ApiResponse> getPageRecipe(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {
        try{
            var recipes = recipeService.getPageAllRecipes(page, size);
            return ResponseEntity.ok(new ApiResponse(("Page :" + page + " Size: " + size + " recipes succesfully gathered"), recipes));
        } catch ( SourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("No recipe found!", null));
        }
    }

    @PostMapping("recipes")
    public ResponseEntity<ApiResponse> addRecipe( @Valid @RequestBody RecipePostRequest recipePostRequest) {
        try{
            var recipe = recipeService.addRecipe(recipePostRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("Recipe succesfully added", recipe));
        } catch(InstanceAlreadyExistsException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse("Recipe already exists", null));
        }
    }

    @DeleteMapping("recipes/{id}")
    public ResponseEntity<ApiResponse> deleteRecipe(@PathVariable Long id) {
        try{
            recipeService.deleteRecipe(id);
            return ResponseEntity.ok(new ApiResponse("Recipe succesfully deleted", null));
        } catch (SourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("No recipe found!", null));
        }
    }

    @GetMapping("filter/recipes")
    public ResponseEntity<ApiResponse> filterRecipe(@RequestParam(required = false, name="ingredient") List<String> ingredients,
                                                    @RequestParam(required = false) String foodCategory,
                                                    @RequestParam(required = false) String name)
    {
        try{
            var recipes = recipeService.filterRecipe(ingredients, foodCategory, name);
            return ResponseEntity.ok(new ApiResponse("Filtered recipes succesfully gathered", recipes));
        } catch (SourceNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("No recipe found!", null));
        }
    }
}
