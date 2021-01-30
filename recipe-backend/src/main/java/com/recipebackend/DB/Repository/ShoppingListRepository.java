package com.recipebackend.DB.Repository;

import com.recipebackend.BO.ShoppingList;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingListRepository extends CrudRepository<ShoppingList,Integer> {

}
