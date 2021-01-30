package com.recipebackend.Rest.DTO;

import java.util.List;

public class ShoppingListDTO {

    private int id;

    private String name;

    private String date;

    private List<IngredientDTO> ingredientDTOS;

    public ShoppingListDTO() {
    }

    public ShoppingListDTO(int id, String name, String date, List<IngredientDTO> ingredientDTOS) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.ingredientDTOS = ingredientDTOS;
    }

    public ShoppingListDTO(int id, List<IngredientDTO> ingredientDTOS) {
        this.id = id;
        this.ingredientDTOS = ingredientDTOS;
    }
    //@formatter:off
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public List<IngredientDTO> getIngredientDTOS() {
        return ingredientDTOS;
    }
    public void setIngredientDTOS(List<IngredientDTO> ingredientDTOS) {
        this.ingredientDTOS = ingredientDTOS;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    //@formatter:on
    @Override
    public String toString() {
        return "ShoppingListDTO{" +
                "ingredientDTOS=" + ingredientDTOS +
                '}';
    }
}
