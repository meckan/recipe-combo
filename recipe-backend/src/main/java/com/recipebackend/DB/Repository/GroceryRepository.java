package com.recipebackend.DB.Repository;

import com.recipebackend.BO.Grocery;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroceryRepository extends CrudRepository<Grocery,Integer> {

    Grocery findByName(String name);

    List<Grocery> findAllByNameContaining(String subString);
}
