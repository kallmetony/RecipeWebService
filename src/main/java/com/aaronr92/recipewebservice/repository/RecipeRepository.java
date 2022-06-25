package com.aaronr92.recipewebservice.repository;

import com.aaronr92.recipewebservice.entities.Recipe;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends CrudRepository<Recipe, Long> {

    @Query
    Recipe findRecipeById(Long id);

    @Query
    List<Recipe> findByNameIgnoreCaseContainsOrderByDateDesc(String name);

    @Query
    List<Recipe> findByCategoryIgnoreCaseOrderByDateDesc(String category);
}