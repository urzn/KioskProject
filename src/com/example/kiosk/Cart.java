/**
 * 장바구니
 * 장바구니 아이템을 리스트로 관리
 * 아이템 출력, 삭제, 총 가격 계산
 */
package com.example.kiosk;
import java.util.*;

public class Cart{
    private List<MenuItem> cartItems = new ArrayList<>();

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
