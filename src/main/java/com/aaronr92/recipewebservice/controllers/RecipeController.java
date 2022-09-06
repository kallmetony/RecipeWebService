package com.aaronr92.recipewebservice.controllers;

import com.aaronr92.recipewebservice.entities.Recipe;
import com.aaronr92.recipewebservice.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/recipe")
@RequiredArgsConstructor
public class RecipeController {

    private final RecipeService recipeService;

    //Post mappings

    @PostMapping("/new")
    public ResponseEntity<Map<String, Long>> saveRecipe(Authentication auth,
                                        @Valid @RequestBody Recipe recipe) {
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        return new ResponseEntity<>((recipeService.save(userDetails, recipe)), HttpStatus.OK);
    }

    //Get mappings

    @GetMapping("/{id}")
    public ResponseEntity<Recipe> getRecipe(@PathVariable long id) {
        return new ResponseEntity<>(recipeService.getRecipe(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Recipe>> getAllRecipes() {
        return ResponseEntity.ok(recipeService.getAllRecipes());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Recipe>> searchRecipes(@RequestParam(required = false) String category,
                                      @RequestParam(required = false) String name) {
        return ResponseEntity.ok(recipeService.searchRecipes(category, name));
    }

    //Delete mappings

    @DeleteMapping("/{id}")
    public void deleteRecipe(@AuthenticationPrincipal UserDetails userDetails,
                             @PathVariable long id) {
        recipeService.deleteRecipeById(userDetails, id);
    }

    //Put mappings

    @PutMapping("/{id}")
    public void updateRecipe(@AuthenticationPrincipal UserDetails userDetails,
                             @PathVariable long id,
                             @Valid @RequestBody Recipe recipe) {
        recipeService.updateRecipe(userDetails, id, recipe);
    }
}