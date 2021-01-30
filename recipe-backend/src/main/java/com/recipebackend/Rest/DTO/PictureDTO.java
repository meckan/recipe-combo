package com.recipebackend.Rest.DTO;

public class PictureDTO {

    private int id;

    private String name;
    private String picArray;
    private String pictureType;

    public PictureDTO(int id, String name, String picArray, String pictureType) {
        this.id = id;
        this.name = name;
        this.picArray = picArray;
        this.pictureType = pictureType;
    }

    public PictureDTO() {
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
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


    @Override
    public String toString() {
        return "PictureDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", picArray='" + picArray + '\'' +
                ", pictureType='" + pictureType + '\'' +
                '}';
    }
}
