package com.recipebackend.DB.Repository;

import com.recipebackend.BO.Picture;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PictureRepository extends CrudRepository<Picture,Integer> {
}
