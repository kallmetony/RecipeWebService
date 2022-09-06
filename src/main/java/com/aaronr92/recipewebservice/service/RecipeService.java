package com.aaronr92.recipewebservice.service;

import com.aaronr92.recipewebservice.entities.Recipe;
import com.aaronr92.recipewebservice.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class RecipeService {

    private final RecipeRepository recipeRepository;

    public Map<String, Long> save(UserDetails user, Recipe recipe) {
        recipe.setEmail(user.getUsername());
        System.out.println(recipe.getEmail());
        Recipe recipeCreate = recipeRepository.save(recipe);
        return Map.of("id", recipeCreate.getId());
    }

    public Recipe getRecipe(long id) {
        Recipe recipe = recipeRepository.findRecipeById(id);
        if (recipe == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return recipe;
    }

    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    public List<Recipe> searchRecipes(String category, String name) {
        if (category == null && name != null) {
            return recipeRepository.findByNameIgnoreCaseContainsOrderByDateDesc(name);
        } else if (name == null && category != null) {
            return recipeRepository.findByCategoryIgnoreCaseOrderByDateDesc(category);
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    public void deleteRecipeById(UserDetails user, long id) {
        Recipe recipe = recipeRepository.findRecipeById(id);
        if (recipe == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        if (Objects.equals(recipe.getEmail(), user.getUsername())) {
            recipeRepository.deleteById(id);
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
        throw new ResponseStatusException(HttpStatus.FORBIDDEN);
    }

    public void updateRecipe(UserDetails user, long id, Recipe recipe) {
        Recipe dbRecipe = recipeRepository.findRecipeById(id);
        if (dbRecipe == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        if (Objects.equals(dbRecipe.getEmail(), user.getUsername())) {
            recipe.setId(id);
            recipeRepository.save(recipe);
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
        throw new ResponseStatusException(HttpStatus.FORBIDDEN);
    }

}
