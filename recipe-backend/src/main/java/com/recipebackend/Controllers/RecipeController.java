package com.recipebackend.Controllers;

import com.recipebackend.BO.Grocery;
import com.recipebackend.BO.Ingredient;
import com.recipebackend.BO.Picture;
import com.recipebackend.BO.Recipe;
import com.recipebackend.DB.RecipeDBHandler;
import com.recipebackend.Rest.DTO.GroceryDTO;
import com.recipebackend.Rest.DTO.IngredientDTO;
import com.recipebackend.Rest.DTO.PictureDTO;
import com.recipebackend.Rest.DTO.RecipeDTO;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@Configurable
public class RecipeController {

    @Autowired
    private RecipeDBHandler recipeDBHandler;

    public String addRecipe(RecipeDTO recipeDTO){

        Recipe recipe = convertToRecipe(recipeDTO);
        recipeDBHandler.saveRecipe(recipe);
        return recipe.getRecipeText();
    }

    public List<RecipeDTO> getAllRecipies(){
        return convertRecipeList(recipeDBHandler.getAllRecipes());
    }

    public List<RecipeDTO> getRecipeAllSim() {
        return convertRecipeSim(recipeDBHandler.getAllRecipeSim());
    }

    public List<RecipeDTO> getRecipeBySubStringSim(String subString){
        return convertRecipeSim(recipeDBHandler.getRecipesBySubString(subString));
    }

    public RecipeDTO getById(int id) {
        Recipe recipe = recipeDBHandler.getById(id);
        if (recipe == null)
            return null;
        return convertToRecipeDTO(recipe);
    }

    public List<RecipeDTO> getRecipesByType(String type){
        return convertRecipeSim( recipeDBHandler.getRecipeByType(type));
    }


    // TODO converter of picture

    public RecipeDTO tesseractTest(MultipartFile multipartFile, String orientation) throws TesseractException, IOException {
        Recipe recipe = new Recipe();

        File convFile = new File(multipartFile.getOriginalFilename());
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(multipartFile.getBytes());
        fos.close();

        String returnText;
        if (orientation.equals("pc"))
            returnText = recipe.convertPictureToText(convFile);
        else
            returnText = recipe.processRecipeText(convFile, orientation);
        convFile.delete();

        RecipeDTO recipeDTO = new RecipeDTO();
        recipeDTO.setRecipeText(returnText);

        return recipeDTO;
    }

    public String getPicText(byte[] byteArray) throws TesseractException, IOException {

        Recipe recipe = new Recipe();
        return recipe.convertPictureToTextByteArray(byteArray);

    }

    private List<RecipeDTO> convertRecipeList(List<Recipe> recipeList){
        List<RecipeDTO> returnList = new ArrayList<>();
        for (Recipe r: recipeList) {
            returnList.add(convertToRecipeDTO(r));
        }
        return returnList;
    }
    private List<RecipeDTO> convertRecipeSim(List<Recipe> recipeList){
        List<RecipeDTO> recipeDTOS = new ArrayList<>();
        for (Recipe r : recipeList) {
            RecipeDTO recipeDTO = new RecipeDTO();
            recipeDTO.setId(r.getId());
            recipeDTO.setName(r.getName());
            recipeDTO.setMin(r.getMin());
            recipeDTO.setPortions(r.getPortions());
            recipeDTO.setType(r.getType());
            recipeDTOS.add(recipeDTO);
        }
        return recipeDTOS;
    }

    private Recipe convertToRecipe(RecipeDTO recipeDTO) {

        return new Recipe(recipeDTO.getName(), recipeDTO.getType(), recipeDTO.getRecipeText(),
                recipeDTO.getRecipeNutrients(), recipeDTO.getAuthor(), recipeDTO.getPortions(), recipeDTO.getMin(),
                convertToIngredientList(recipeDTO.getIngredients()), convertToPicture(recipeDTO.getPicture()));
    }
    private List<Ingredient> convertToIngredientList(List<IngredientDTO> ingredientDTOS) {
        ShoppingListController shoppingListController = new ShoppingListController();
        return shoppingListController.convertToIngredientList(ingredientDTOS);
    }
    private Picture convertToPicture(PictureDTO pictureDTO){
        if(pictureDTO == null)
            return null;
        return new Picture(pictureDTO.getPicArray(),pictureDTO.getPictureType());
    }
    private RecipeDTO convertToRecipeDTO(Recipe recipe) {
        RecipeDTO recipeDTO = new RecipeDTO();
        recipeDTO.setId(recipe.getId());
        recipeDTO.setName(recipe.getName());
        recipeDTO.setPortions(recipe.getPortions());
        recipeDTO.setType(recipe.getType());
        recipeDTO.setMin(recipe.getMin());

        recipeDTO.setAuthor(recipe.getAuthor());
        //recipeDTO.setRecipeNutrients(recipe.getRecipeNutrients());
        recipeDTO.setRecipeText(recipe.getRecipeText());

        recipeDTO.setIngredients(convertToIngredientDTOList(recipe.getIngredients()));

        if (recipe.getPicture() != null)
            recipeDTO.setPicture(new PictureDTO(recipe.getPicture().getId(), null,
                    recipe.getPicture().getPicArray(), recipe.getPicture().getPictureType()));

        return recipeDTO;
    }

    private List<IngredientDTO> convertToIngredientDTOList(List<Ingredient> ingredients) {
        List<IngredientDTO> ingredientDTOS = new ArrayList<>();
        for (Ingredient i : ingredients) {
            ingredientDTOS.add(convertToIngredientDTO(i));
        }
        return ingredientDTOS;
    }
    private IngredientDTO convertToIngredientDTO(Ingredient ingredient) {
        return new IngredientDTO(ingredient.getId(),convertToGroceryDTO(ingredient.getGrocery()),ingredient.getMeasurement(),ingredient.getAmount());
    }
    private GroceryDTO convertToGroceryDTO(Grocery grocery){
        return new GroceryDTO(grocery.getId(),grocery.getName(),grocery.getType());
    }

    /*
    private GroceryDTO convertToGroceryDTO(Grocery grocery) {
        System.out.println();
        return new GroceryDTO(grocery.getId(), grocery.getName(), grocery.getType());
    }
     */
}
