package com.recipebackend.Rest;

import com.recipebackend.Controllers.RecipeController;
import com.recipebackend.Rest.DTO.RecipeDTO;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;

@CrossOrigin(origins = {"http://localhost:3000", "http://192.168.1.197:3000"})
@RestController
@RequestMapping("/recipe")
public class RecipeRestApi {

    @Autowired
    private RecipeController recipeController;

    @PostMapping("/addRecipe")
    public ResponseEntity<?> addRecipe(@RequestBody RecipeDTO recipeDTO) {
        return new ResponseEntity<>(recipeController.addRecipe(recipeDTO), HttpStatus.OK);
    }

    @GetMapping("/getAllRecipes")
    public ResponseEntity<?> getAllRecipies(){
        return new ResponseEntity<>(recipeController.getAllRecipies(),HttpStatus.OK);
    }

    @GetMapping("/getAllRecipesSim")
    public ResponseEntity<?> getAllRecipesSim() {
        return new ResponseEntity<>(recipeController.getRecipeAllSim(), HttpStatus.OK);
    }

    @GetMapping("getRecipeBySubStringSim")
    public ResponseEntity<?> getRecipeBySubStringSim(@RequestParam("subString") String subString) {
        return new ResponseEntity<>(recipeController.getRecipeBySubStringSim(subString), HttpStatus.OK);
    }

    @GetMapping("/getRecipeById")
    public ResponseEntity<?> getRecipeById(@Param("id") int id) {
        RecipeDTO recipeDTO = recipeController.getById(id);
        if (recipeDTO != null) {
            return new ResponseEntity<>(recipeDTO, HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getRecipeByType")
    public ResponseEntity<?> getRecipeByType(@Param("type") String type) {
        return new ResponseEntity<>(recipeController.getRecipesByType(type), HttpStatus.OK);
    }


    @PostMapping("/testPic")
    public ResponseEntity<?> tesseractTest(@RequestParam("file") MultipartFile file,
                                           @RequestParam("orientation") String orientation)
            throws TesseractException, IOException {
        return new ResponseEntity<>(recipeController.tesseractTest(file, orientation), HttpStatus.OK);
    }

    /**
     * Testing
     */
    @GetMapping("/getByteArray")
    public ResponseEntity<?> getByteArray(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        System.out.println(Arrays.toString(multipartFile.getBytes()));
        return new ResponseEntity<>(multipartFile.getBytes(), HttpStatus.OK);
    }


}
