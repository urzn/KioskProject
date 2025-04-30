/**
 * 메뉴 아이템 클래스
 * 메뉴 이름, 가격, 상세정보 변수, getter
 */

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
