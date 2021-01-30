package com.recipebackend.Rest.DTO;

import java.util.List;

public class RecipeDTO {

    private int id;

    private String name;

    private String type;

    private String recipeText;
    private String recipeNutrients;
    private String author;

    private int portions;
    private int min;

    private List<IngredientDTO> ingredients;

    private PictureDTO picture;

    public RecipeDTO() {
    }

    public RecipeDTO(int id, String name, String type, String recipeText,
                     String recipeNutrients, String author, int portions, int min, List<IngredientDTO> ingredients,
                     PictureDTO picture) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.recipeText = recipeText;
        this.recipeNutrients = recipeNutrients;
        this.author = author;
        this.portions = portions;
        this.min = min;
        this.ingredients = ingredients;
        this.picture = picture;
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
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getRecipeText() {
        return recipeText;
    }
    public void setRecipeText(String recipeText) {
        this.recipeText = recipeText;
    }
    public String getRecipeNutrients() {
        return recipeNutrients;
    }
    public void setRecipeNutrients(String recipeNutrients) {
        this.recipeNutrients = recipeNutrients;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public int getPortions() {
        return portions;
    }
    public void setPortions(int portions) {
        this.portions = portions;
    }
    public int getMin() {
        return min;
    }
    public void setMin(int min) {
        this.min = min;
    }
    public List<IngredientDTO> getIngredients() {
        return ingredients;
    }
    public void setIngredients(List<IngredientDTO> ingredients) {
        this.ingredients = ingredients;
    }
    public PictureDTO getPicture() {
        return picture;
    }
    public void setPicture(PictureDTO picture) {
        this.picture = picture;
    }

    @Override
    public String toString() {
        return "RecipeDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", recipeText='" + recipeText + '\'' +
                ", recipeNutrients='" + recipeNutrients + '\'' +
                ", author='" + author + '\'' +
                ", portions=" + portions +
                ", min=" + min +
                ", ingredients=" + ingredients +
                ", picture=" + picture +
                '}';
    }
}
