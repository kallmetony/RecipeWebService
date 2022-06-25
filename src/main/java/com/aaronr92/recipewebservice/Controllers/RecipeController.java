package com.aaronr92.recipewebservice.controllers;

import com.aaronr92.recipewebservice.entities.Recipe;
import com.aaronr92.recipewebservice.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api/recipe")
public class RecipeController {

    @Autowired
    RecipeService recipeService;

    //Post mappings

    @PostMapping("/new")
    public ResponseEntity<?> saveRecipe(Authentication auth,
                                        @Valid @RequestBody Recipe recipe) {
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        recipe.setEmail(userDetails.getUsername());
        Recipe recipeCreate = recipeService.save(recipe);
        return new ResponseEntity<>(Map.of("id", recipeCreate.getId()), HttpStatus.OK);
    }

    //Get mappings

    @GetMapping("/{id}")
    public ResponseEntity<Recipe> getRecipe(@PathVariable long id) {
        Optional<Recipe> recipe = Optional.ofNullable(recipeService.findRecipeById(id));
        if (recipe.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(recipe.get(), HttpStatus.OK);
    }

    @GetMapping("/search")
    public List<Recipe> searchRecipes(@RequestParam(required = false) String category,
                                      @RequestParam(required = false) String name) {
        Optional<String> nameOptional = Optional.ofNullable(name);
        Optional<String> categoryOptional = Optional.ofNullable(category);
        if (nameOptional.isPresent() && categoryOptional.isEmpty()) {
            return recipeService.findRecipesByName(name);
        } else if (categoryOptional.isPresent() && nameOptional.isEmpty()) {
            return recipeService.findRecipesByCategory(category);
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    //Delete mappings

    @DeleteMapping("/{id}")
    public void deleteRecipe(@AuthenticationPrincipal UserDetails userDetails,
                             @PathVariable long id) {
        Optional<Recipe> recipe = Optional.ofNullable(recipeService.findRecipeById(id));
        if (recipe.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        if (Objects.equals(recipeService.findRecipeById(id).getEmail(), userDetails.getUsername())) {
            recipeService.deleteRecipeById(id);
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
        throw new ResponseStatusException(HttpStatus.FORBIDDEN);
    }

    //Put mappings

    @PutMapping("/{id}")
    public void updateRecipe(@AuthenticationPrincipal UserDetails userDetails,
                             @PathVariable long id,
                             @Valid @RequestBody Recipe recipe) {
        if (recipeService.findRecipeById(id) == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        if (Objects.equals(recipeService.findRecipeById(id).getEmail(), userDetails.getUsername())) {
            recipe.setId(id);
            recipeService.save(recipe);
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
        throw new ResponseStatusException(HttpStatus.FORBIDDEN);
    }
}