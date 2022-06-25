package com.aaronr92.recipewebservice.service;

import com.aaronr92.recipewebservice.entities.Recipe;
import com.aaronr92.recipewebservice.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeService {
    @Autowired
    private RecipeRepository recipeRepository;

    public Recipe findRecipeById(Long id) {
        return recipeRepository.findRecipeById(id);
    }

    public List<Recipe> findRecipesByName(String name) {
        return recipeRepository.findByNameIgnoreCaseContainsOrderByDateDesc(name);
    }

    public List<Recipe> findRecipesByCategory(String category) {
        return recipeRepository.findByCategoryIgnoreCaseOrderByDateDesc(category);
    }

    public Recipe save(Recipe toSave) {
        return recipeRepository.save(toSave);
    }

    public void deleteRecipeById(Long id) {
        recipeRepository.deleteById(id);
    }

}
