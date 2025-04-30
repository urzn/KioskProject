package com.example.kiosk;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        // 카테고리 별 메뉴아이템 리스트 생성
        List<MenuItem> menuItemsBurgers = new ArrayList<>();
        List<MenuItem> menuItemsDrinks = new ArrayList<>();
        List<MenuItem> menuItemsDesserts = new ArrayList<>();

        // 메뉴아이템 생성
        menuItemsBurgers.add(new MenuItem("ShackBurger", 6.9, "토마토, 양상추, 쉑소스가 토핑된 치즈버거"));
        menuItemsBurgers.add(new MenuItem("SmokeShack", 8.9, "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거"));
        menuItemsBurgers.add(new MenuItem("CheeseBurger", 6.9, "포테이토 번과 비프패티, 치즈가 토핑된 치즈버거"));
        menuItemsBurgers.add(new MenuItem("Hamburger", 5.4, "비프패티를 기반으로 야채가 들어간 기본버거"));

        menuItemsDrinks.add(new MenuItem("CocaCola", 3.0, "코카콜라"));
        menuItemsDrinks.add(new MenuItem("Sprite", 3.0, "스프라이트"));
        menuItemsDrinks.add(new MenuItem("Iced Americano", 4.0, "아이스 아메리카노"));
        menuItemsDrinks.add(new MenuItem("Chocolate Shake", 6.5, "초콜릿 쉐이크"));
        menuItemsDrinks.add(new MenuItem("Vanilla Shake", 6.5, "바닐라 쉐이크"));
        menuItemsDrinks.add(new MenuItem("Budweiser", 7.5, "버드와이저"));

        menuItemsDesserts.add(new MenuItem("Fries", 5.0, "감자튀김"));
        menuItemsDesserts.add(new MenuItem("Chilli Fries", 7.0, "칠리 프라이"));
        menuItemsDesserts.add(new MenuItem("Ice Cream", 5.0, "아이스크림"));

        // 메뉴 아이템을 관리하는 카테고리 별 메뉴 클래스 객체 생성
        Menu burgersMenu = new Menu(Category.BURGERS, menuItemsBurgers);
        Menu drinksMenu = new Menu(Category.DRINKS, menuItemsDrinks);
        Menu dessertsMenu = new Menu(Category.DESSERTS, menuItemsDesserts);

        Kiosk kiosk = new Kiosk(Arrays.asList(burgersMenu, drinksMenu, dessertsMenu));

        kiosk.start();
    }
}