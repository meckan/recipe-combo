package com.recipebackend.BO;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Entity
public class ShoppingList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private Date date;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "shoppingListId")
    private List<Ingredient> ingredientList;

    public ShoppingList() {
        SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
        date = new Date(System.currentTimeMillis());
        name = sdf.format(date);
    }

    public ShoppingList(String name, Date date, List<Ingredient> ingredientList) {
        this.name = name;
        this.date = date;
        this.ingredientList = ingredientList;
    }

    public ShoppingList(String name, List<Ingredient> ingredientList) {
        this.name = name;
        date = new Date(System.currentTimeMillis());
        this.ingredientList = ingredientList;
    }

    public ShoppingList(List<Ingredient> ingredientList) {
        SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
        date = new Date(System.currentTimeMillis());
        name = sdf.format(date);
        this.ingredientList = ingredientList;
    }

    public void addIngredients(Ingredient newIngredient) {
        boolean added = false;
        for (Ingredient i : ingredientList) {
            if (newIngredient.getGrocery().getName().equals(i.getGrocery().getName())) {
                i.setAmount(i.getAmount() + newIngredient.getAmount());
                added = true;
            }
        }
        if (!added)
            ingredientList.add(newIngredient);
    }

    //@formatter:off
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public List<Ingredient> getIngredientList() {
        return ingredientList;
    }
    public void setIngredientList(List<Ingredient> ingredientList) {
        this.ingredientList = ingredientList;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    //@formatter:on

    @Override
    public String toString() {
        return "ShoppingList{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", ingredientList=" + ingredientList +
                '}';
    }
}
