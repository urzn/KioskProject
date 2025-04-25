package com.example.kiosk;

import java.util.*;

public class Menu {
    Map<Category, List<MenuItem>> menuMap = new HashMap<>();
    private List<MenuItem> menuItems = new ArrayList<>();
    private String menuName;
    private final Category category;

    public Menu (Category category, List<MenuItem> menuItems){
        this.menuItems = menuItems;
        this.category = category;
    }

    public String getCategories(){
        menuName = category.getLabel();
        return menuName;
    }

    public List<MenuItem> getMenuItems(){
        return menuItems;
    }

}
