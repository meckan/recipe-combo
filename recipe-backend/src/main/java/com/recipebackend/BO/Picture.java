package com.recipebackend.BO;

import javax.persistence.*;
import java.io.IOException;

@Entity
public class Picture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    @Lob
    private String picArray;
    private String pictureType;

    public Picture(Integer id, String name, String picArray) {
        this.id = id;
        this.name = name;
        this.picArray = picArray;
    }

    public Picture(String picArray) throws IOException {
        this.picArray = picArray;
        //pictureType = URLConnection.guessContentTypeFromStream(new ByteArrayInputStream(picArray));

    }

    public Picture(String picArray, String pictureType) {
        this.picArray = picArray;
        this.pictureType = pictureType;
    }

    public Picture() {
    }

    //@formatter:off
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPicArray() {
        return picArray;
    }
    public void setPicArray(String picArray) {
        this.picArray = picArray;
    }
    public String getPictureType() {
        return pictureType;
    }
    public void setPictureType(String pictureType) {
        this.pictureType = pictureType;
    }

    //@formatter:on
    @Override
    public String toString() {
        return "Picture{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", picArray='" + picArray + '\'' +
                ", pictureType='" + pictureType + '\'' +
                '}';
    }
}
