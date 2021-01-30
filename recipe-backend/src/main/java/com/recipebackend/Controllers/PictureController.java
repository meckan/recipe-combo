package com.recipebackend.Controllers;

import com.recipebackend.BO.Picture;
import com.recipebackend.DB.PictureDBHandler;
import com.recipebackend.Rest.DTO.PictureDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Controller;

@Controller
@Configurable
public class PictureController {

    @Autowired
    private PictureDBHandler pictureDBHandler;

    public PictureDTO getPictureById(int id){
        Picture picture = pictureDBHandler.getPictureById(id);
        if(picture != null){
            return new PictureDTO(picture.getId(),picture.getName(),picture.getPicArray(),picture.getPictureType());
        }
        return null;
    }
}
