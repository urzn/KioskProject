package com.example.kiosk;

import java.util.*;

public interface PrintMenu {
    default void printMenuItem(MenuItem menuItem){
        System.out.println(menuItem.getName() + " | W " + menuItem.getPrice() + " | " + menuItem.getDetails());
    }

    default void printMenuList(List<MenuItem> menuItems){
        for(int i = 0; i<menuItems.size();i++){
            System.out.print((i+1) + ". ");
            printMenuItem(menuItems.get(i));
        }
    }
}
