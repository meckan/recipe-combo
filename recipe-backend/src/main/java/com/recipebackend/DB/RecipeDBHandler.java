package com.recipebackend.DB;

import com.recipebackend.BO.Ingredient;
import com.recipebackend.BO.Recipe;
import com.recipebackend.DB.Repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository // Remove?
@Transactional // Remove?
public class RecipeDBHandler {

    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    private GroceryDBHandler groceryDBHandler;
    @Autowired
    private IngredientDBHandler ingredientDBHandler;
    @Autowired
    private PictureDBHandler pictureDBHandler;

    public boolean saveRecipe(Recipe recipe){
        for (Ingredient i: recipe.getIngredients()) {
            i.setGrocery(groceryDBHandler.saveGrocery(i.getGrocery()));
            ingredientDBHandler.saveIngredient(i);
        }
        if(recipe.getPicture() != null)
            pictureDBHandler.savePicture(recipe.getPicture());
        recipeRepository.save(recipe);
        return true;
    }

    public List<Recipe> getAllRecipes(){
        return (List<Recipe>) recipeRepository.findAll();
    }

    public List<Recipe> getAllRecipeSim(){
        return (List<Recipe>) recipeRepository.findAll();
    }

    public Recipe getById(int id){
        Optional<Recipe> recipeOpt = recipeRepository.findById(id);
        return recipeOpt.orElse(null);
    }

    public List<Recipe> getRecipesBySubString(String subString){
        //return recipeRepository.getAllByNameStartingWith(subString);
        return recipeRepository.getAllByNameContaining(subString);
    }

    public List<Recipe> getRecipeByType(String type){
        return recipeRepository.getAllByType(type);
    }
}
