package com.example.kiosk;
import java.util.*;

public class Cart implements PrintMenu{
    private List<MenuItem> cartItems = new ArrayList<>();

    public Cart(){
    }

    public void addCart(MenuItem menuItem){
        cartItems.add(menuItem);
    }
    public List<MenuItem> getCart(){
        return cartItems;
    }
    public void deleteItemFromCart(MenuItem cartItem){
        cartItems.removeIf(item -> item.getName().equals(cartItem.getName()));
    }
    public double getPrice(){
        double price = 0;
        for(MenuItem menuItem : cartItems){
            price += menuItem.getPrice();
        }
        return price;
    }

}
