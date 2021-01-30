package com.recipebackend.Controllers;

import com.recipebackend.BO.Grocery;
import com.recipebackend.BO.Ingredient;
import com.recipebackend.BO.ShoppingList;
import com.recipebackend.DB.ShoppingListDBHandler;
import com.recipebackend.Rest.DTO.IngredientDTO;
import com.recipebackend.Rest.DTO.ShoppingListDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Controller
@Configurable
public class ShoppingListController {

    @Autowired
    private ShoppingListDBHandler dbHandler;

    @Autowired
    private GroceryController groceryController;

    public List<ShoppingListDTO> getAllShoppingLists(){
        List<ShoppingList> shoppingListList = dbHandler.getAllShoppingLists();
        List<ShoppingListDTO> shoppingListDTOList = new ArrayList<>();

        for (ShoppingList shopp: shoppingListList) {
            shoppingListDTOList.add(convertToDTO(shopp));
        }
        return shoppingListDTOList;
    }

    public ShoppingListDTO getShoppingListById(int id) {
        ShoppingList shoppingList = dbHandler.getShoppingListById(id);
        if (shoppingList != null)
            return convertToDTO(shoppingList);
        return null;
    }

    public boolean addNewShoppingList(ShoppingListDTO shoppingListDTO) {
        ShoppingList shoppingList;
        if(shoppingListDTO.getName() != null)
            shoppingList = new ShoppingList(shoppingListDTO.getName(),
                    convertToIngredientList(shoppingListDTO.getIngredientDTOS()));
        else
            shoppingList = new ShoppingList(convertToIngredientList(shoppingListDTO.getIngredientDTOS()));
        return dbHandler.saveShoppingList(shoppingList);
    }

    public boolean addItemsToShoppingList(int shoppingListId, ShoppingListDTO newItemsDTO){
        List<Ingredient> newIngredients = convertToIngredientList(newItemsDTO.getIngredientDTOS());
        return dbHandler.addIngredientsToList(shoppingListId,newIngredients);
    }


    //@formatter:off
    private ShoppingListDTO convertToDTO(ShoppingList shoppingList) {
        SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
        String date = sdf.format(shoppingList.getDate());
        return new ShoppingListDTO(shoppingList.getId(),shoppingList.getName(),date,convertToIngredientListDTO(shoppingList.getIngredientList()));
    }
    public List<Ingredient> convertToIngredientList(List<IngredientDTO> ingredientDTOS) {
        List<Ingredient> ingredients = new ArrayList<>();
        for (IngredientDTO i : ingredientDTOS) {
//            Grocery grocery = new Grocery(i.getName(), i.getType());
            Grocery grocery = new Grocery(i.getGrocery().getName(),i.getGrocery().getType());

            Ingredient ingredient = new Ingredient(i.getId(), grocery, i.getMeasurement(), i.getAmount());
            ingredients.add(ingredient);
        }
        return ingredients;
    }
    private List<IngredientDTO> convertToIngredientListDTO(List<Ingredient> ingredientList) {
        ArrayList<IngredientDTO> ingredientDTOArrayList = new ArrayList<>();
        for (Ingredient i : ingredientList) {
            ingredientDTOArrayList.add(convertToIngredientDTO(i));
        }
        return ingredientDTOArrayList;
    }
    private IngredientDTO convertToIngredientDTO(Ingredient ingredient) {
        return new IngredientDTO(ingredient.getId(), groceryController.convertGroceryDTO(ingredient.getGrocery()),
                ingredient.getMeasurement(), ingredient.getAmount());
    }
    //@formatter:on
}
