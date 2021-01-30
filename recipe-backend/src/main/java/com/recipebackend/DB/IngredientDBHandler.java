package com.recipebackend.DB;

import com.recipebackend.BO.Ingredient;
import com.recipebackend.DB.Repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository // Remove?
@Transactional // Remove?
public class IngredientDBHandler {

    @Autowired
    private IngredientRepository ingredientRepository;

    public boolean saveIngredient(Ingredient ingredient){
        ingredientRepository.save(ingredient);
        return true;
    }
}
