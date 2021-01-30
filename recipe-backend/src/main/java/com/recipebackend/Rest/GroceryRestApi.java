package com.recipebackend.Rest;

import com.recipebackend.Controllers.GroceryController;
import com.recipebackend.Rest.DTO.GroceryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"http://localhost:3000", "http://192.168.1.197:3000"})
@RestController
@RequestMapping("/grocery")
public class GroceryRestApi {

    @Autowired
    private GroceryController groceryController;

    @GetMapping("/groceryBySubString")
    public ResponseEntity<?> getGroceryBySubString(@RequestParam("subString") String subString) {
        return new ResponseEntity<>(groceryController.getGroceryBySubString(subString), HttpStatus.OK);
    }
    
    @PostMapping("/newGrocery")
    public ResponseEntity<?> addNewGrocery(@RequestBody GroceryDTO groceryDTO){
        return new ResponseEntity<>(groceryController.addNewGrocery(groceryDTO), HttpStatus.OK);
    }

}
