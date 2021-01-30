package com.recipebackend.Rest.DTO;

public class IngredientDTO {

    private Integer id;

    private GroceryDTO grocery;

    private String measurement;
    private double amount;

    private boolean taken;

    public IngredientDTO(Integer id, GroceryDTO grocery, String measurement, double amount) {
        this.id = id;
        this.grocery = grocery;
        this.measurement = measurement;
        this.amount = amount;
    }

    public IngredientDTO(Integer id, GroceryDTO grocery, String measurement, double amount, boolean taken) {
        this.id = id;
        this.grocery = grocery;
        this.measurement = measurement;
        this.amount = amount;
        this.taken = taken;
    }

    public IngredientDTO() {
    }

    //@formatter:off
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public GroceryDTO getGrocery() {
        return grocery;
    }
    public void setGrocery(GroceryDTO grocery) {
        this.grocery = grocery;
    }
    public String getMeasurement() {
        return measurement;
    }
    public void setMeasurement(String measurement) {
        this.measurement = measurement;
    }
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public boolean isTaken() {
        return taken;
    }
    public void setTaken(boolean taken) {
        this.taken = taken;
    }
    //@formatter:on

    @Override
    public String toString() {
        return "IngredientDTO{" +
                "id=" + id +
                ", grocery=" + grocery +
                ", measurement='" + measurement + '\'' +
                ", amount=" + amount +
                ", taken=" + taken +
                '}';
    }
}
