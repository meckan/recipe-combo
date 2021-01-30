package com.recipebackend.Rest;

import com.recipebackend.Controllers.PictureController;
import com.recipebackend.Rest.DTO.PictureDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = {"http://localhost:3000", "http://192.168.1.197:3000"})
@RestController
@RequestMapping("/picture")
public class PictureRestApi {

    @Autowired
    private PictureController pictureController;

    @GetMapping("/getPictureById")
    public ResponseEntity<?> getPictureById(@Param("id") int id) {
        PictureDTO pictureDTO = pictureController.getPictureById(id);
        if (pictureDTO != null)
            return new ResponseEntity<>(pictureController.getPictureById(id), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
