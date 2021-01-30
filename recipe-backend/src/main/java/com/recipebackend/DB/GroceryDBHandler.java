package com.recipebackend.DB;

import com.recipebackend.BO.Grocery;
import com.recipebackend.DB.Repository.GroceryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository // Remove?
@Transactional // Remove?
public class GroceryDBHandler {

    @Autowired
    private GroceryRepository groceryRepository;

    public Grocery saveGrocery(Grocery grocery){
        Grocery returnGrocery;
        if((returnGrocery = groceryRepository.findByName(grocery.getName())) != null)
            return returnGrocery;
        groceryRepository.save(grocery);
        return grocery;
    }

    public List<Grocery> getGroceryBySubString(String subString){
        return groceryRepository.findAllByNameContaining(subString);
    }
}
