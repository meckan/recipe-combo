package com.recipebackend.DB;

import com.recipebackend.BO.Picture;
import com.recipebackend.DB.Repository.PictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository // Remove?
@Transactional // Remove?
public class PictureDBHandler {

    @Autowired
    private PictureRepository pictureRepository;

    public boolean savePicture(Picture picture){
        pictureRepository.save(picture);
        return true;
    }

    public Picture getPictureById(int id){
        Optional<Picture> picture = pictureRepository.findById(id);
        return picture.orElse(null);
    }
}
