package com.example.kiosk;

public class MenuItem {
    private final String name, details;
    private final double price;

    public MenuItem(String name, double price, String details){
        this.name = name;
        this.price = price;
        this.details = details;
    }

    public String getName(){
        return name;
    }
    public double getPrice(){
        return price;
    }
    public String getDetails(){
        return details;
    }
}
