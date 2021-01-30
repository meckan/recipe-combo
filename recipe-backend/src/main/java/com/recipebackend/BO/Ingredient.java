package com.recipebackend.BO;


import javax.persistence.*;

@Entity
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "GroceryId")
    private Grocery grocery;
    private String measurement;
    private double amount;

    /**
     * Denna är för shopping list
     */
    private boolean taken;

    public Ingredient(Integer id, Grocery grocery, String measurement, double amount) {
        this.id = id;
        this.grocery = grocery;
        this.measurement = measurement;
        this.amount = amount;
    }

    public Ingredient(Grocery grocery, String measurement, double amount) {
        this.grocery = grocery;
        this.measurement = measurement;
        this.amount = amount;
    }

    public Ingredient(Grocery grocery, String measurement, double amount, boolean taken) {
        this.grocery = grocery;
        this.measurement = measurement;
        this.amount = amount;
        this.taken = taken;
    }

    public Ingredient() {
    }

    //@formatter:off
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Grocery getGrocery() {
        return grocery;
    }
    public void setGrocery(Grocery grocery) {
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
        return "Ingredient{" +
                "id=" + id +
                ", grocery=" + grocery +
                ", measurement='" + measurement + '\'' +
                ", amount=" + amount +
                ", taken=" + taken +
                '}';
    }
}
