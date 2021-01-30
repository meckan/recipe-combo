package com.recipebackend.Rest;

import com.recipebackend.Controllers.ShoppingListController;
import com.recipebackend.Rest.DTO.ShoppingListDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = {"http://localhost:3000","http://192.168.1.197:3000"})
@RestController
@RequestMapping("/shoppingList")
public class ShoppingListRestApi {

    @Autowired
    private ShoppingListController controller;

    @GetMapping("/getAllLists")
    public ResponseEntity<?> getAllLShoppingLists(){
        return new ResponseEntity<>(controller.getAllShoppingLists(), HttpStatus.OK);
    }

    @GetMapping("/getShoppingList/{id}")
    public ResponseEntity<?> getShoppingList(@PathVariable int id){
        ShoppingListDTO shoppingListDTO = controller.getShoppingListById(id);
        if(shoppingListDTO != null)
            return new ResponseEntity<>(shoppingListDTO,HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/addShoppingList")
    public ResponseEntity<?> postNewShoppingList(@RequestBody ShoppingListDTO shoppingListDTO){
        return new ResponseEntity<>(controller.addNewShoppingList(shoppingListDTO), HttpStatus.OK);
    }

    @PostMapping("/addShoppingList/items/{listId}")
    public ResponseEntity<?> addListToShoppingList(@PathVariable int listId,@RequestBody ShoppingListDTO shoppingListDTO){
        return new ResponseEntity<>(controller.addItemsToShoppingList(listId,shoppingListDTO), HttpStatus.OK);
    }

}
