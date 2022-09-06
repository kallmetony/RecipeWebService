package com.aaronr92.recipewebservice.repository;

import com.aaronr92.recipewebservice.entities.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    Recipe findRecipeById(Long id);

    List<Recipe> findByNameIgnoreCaseContainsOrderByDateDesc(String name);

    List<Recipe> findByCategoryIgnoreCaseOrderByDateDesc(String category);
}