package com.recipebackend.DB;

import com.recipebackend.BO.Grocery;
import com.recipebackend.BO.Ingredient;
import com.recipebackend.BO.ShoppingList;
import com.recipebackend.DB.Repository.GroceryRepository;
import com.recipebackend.DB.Repository.ShoppingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository // Remove?
@Transactional // Remove?
public class ShoppingListDBHandler {

    @Autowired
    private ShoppingListRepository repository;
    @Autowired
    private GroceryRepository groceryRepository;

    public List<ShoppingList> getAllShoppingLists(){
        return (List<ShoppingList>) repository.findAll();
    }

    public ShoppingList getShoppingListById(int id) {
        Optional<ShoppingList> shoppingList = repository.findById(id);
        return shoppingList.orElse(null);
    }

    public boolean saveShoppingList(ShoppingList shoppingList) {
        for (Ingredient i : shoppingList.getIngredientList()) {
            lookForExistingGrocery(i);
        }
        repository.save(shoppingList);
        return true;
    }

    public boolean addIngredientsToList(int id, List<Ingredient> newIngredients) {
        ShoppingList shoppingList = getShoppingListById(id);
        if (shoppingList == null)
            return false;

        for (Ingredient i : newIngredients) {
            shoppingList.addIngredients(lookForExistingGrocery(i));
        }
        repository.save(shoppingList);
        return true;
    }

    private Ingredient lookForExistingGrocery(Ingredient i){
        Grocery groceryFound;
        if ((groceryFound = (groceryRepository.findByName(i.getGrocery().getName()))) == null) {
            groceryRepository.save(i.getGrocery());
        }
        else {
            i.setGrocery(groceryFound);
        }
        return i;
    }

}
