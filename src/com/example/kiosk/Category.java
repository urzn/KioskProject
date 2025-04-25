package com.example.kiosk;

public enum Category {
    BURGERS("Burgers"),
    DRINKS("Drinks"),
    DESSERTS("Desserts");

    private final String label;
    Category(String label) {
        this.label = label;
    }

    public String getLabel(){
        return label;
    }
}
