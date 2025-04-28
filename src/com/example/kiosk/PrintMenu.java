package com.example.kiosk;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public interface PrintMenu {

    default void printMenuItem(MenuItem menuItem){
        System.out.println(menuItem.getName() + " | W " + menuItem.getPrice() + " | " + menuItem.getDetails());
    }

    default void printMenuList(List<MenuItem> menuItems){
        AtomicInteger counter = new AtomicInteger(1);
        menuItems.stream()
                .forEach(item -> {
                    System.out.print(counter.getAndIncrement()+". ");
                    printMenuItem(item);
                });
        }
}
