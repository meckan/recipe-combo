package com.recipebackend.DB.Repository;

import com.recipebackend.BO.Recipe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends CrudRepository<Recipe,Integer> {

    List<Recipe> getAllByNameStartingWith(String subString);

    List<Recipe> getAllByNameContaining(String subString);

    List<Recipe> getAllByType(String type);

}
