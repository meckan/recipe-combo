package com.recipebackend.Controllers;

import com.recipebackend.BO.Grocery;
import com.recipebackend.DB.GroceryDBHandler;
import com.recipebackend.Rest.DTO.GroceryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
@Configurable
public class GroceryController {

    @Autowired
    private GroceryDBHandler groceryDBHandler;


    public boolean addNewGrocery(GroceryDTO groceryDTO){
        return groceryDBHandler.saveGrocery(convertGrocery(groceryDTO)) != null;
    }

    private Grocery convertGrocery(GroceryDTO groceryDTO){
        return new Grocery(groceryDTO.getName(),groceryDTO.getType());
    }

    public List<GroceryDTO> getGroceryBySubString(String subString){
        List<Grocery> groceryList = groceryDBHandler.getGroceryBySubString(subString);
        return convertGroceryDTOList(groceryList);
    }
    public List<GroceryDTO> convertGroceryDTOList(List<Grocery> groceryList){
        List<GroceryDTO> groceryDTOS = new ArrayList<>();
        for (Grocery g: groceryList) {
            groceryDTOS.add(convertGroceryDTO(g));
        }
        return groceryDTOS;
    }
    public GroceryDTO convertGroceryDTO(Grocery grocery){
        return new GroceryDTO(grocery.getId(),grocery.getName(),grocery.getType());
    }

}

