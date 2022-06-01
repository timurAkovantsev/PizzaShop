package com.example.fragments;

public class PizzaItem {
    public String name;
    public int price;
    public int identifier;
    public int type;

    public PizzaItem(String name, int price, int identifier, int type) {
        this.name = name;
        this.price = price;
        this.identifier = identifier;
        this.type = type;
    }
}
