package com.recipebackend.DB.Repository;

import com.recipebackend.BO.Ingredient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends CrudRepository<Ingredient,Integer> {
}
