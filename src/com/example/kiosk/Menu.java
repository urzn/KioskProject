package com.example.kiosk;

import java.util.*;

public class Menu implements PrintMenu{
    private List<MenuItem> menuItems = new ArrayList<>();
    private final Category category;

    public Menu (Category category, List<MenuItem> menuItems){
        this.menuItems = menuItems;
        this.category = category;
    }

    public String getCategories(){
        return category.getLabel();
    }

    public List<MenuItem> getMenuItems(){
        return menuItems;
    }

}
