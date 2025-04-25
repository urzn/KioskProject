package com.example.kiosk;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(new MenuItem("ShackBurger", 6.9, "토마토, 양상추, 쉑소스가 토핑된 치즈버거"));
        menuItems.add(new MenuItem("SmokeShack", 8.9, "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거"));
        menuItems.add(new MenuItem("CheeseBurger", 6.9, "포테이토 번과 비프패티, 치즈가 토핑된 치즈버거"));
        menuItems.add(new MenuItem("Hamburger", 5.4, "비프패티를 기반으로 야채가 들어간 기본버거"));

        Scanner scanner = new Scanner(System.in);

        System.out.println("[ SHAKESHACK MENU ]");
        for (int i = 0; i < menuItems.size(); i++){
            MenuItem menuItem = menuItems.get(i);
            System.out.println(i+1 + "." + menuItem.getName() + " | W " + menuItem.getPrice() + " | " + menuItem.getDetails());
        }
        System.out.println("0. 종료            | 종료   |");
        System.out.println("숫자를 입력하세요");
        int i = scanner.nextInt();
        if(i > 0 && i <= menuItems.size()){
            MenuItem menuItem = menuItems.get(i);
            System.out.println("선택한 메뉴 : "+ menuItem.getName() + " | W " + menuItem.getPrice() + " | " + menuItem.getDetails());
        }
        else if(i==0){
            System.out.println("프로그램을 종료합니다.");
        }
        else{
            System.out.println("잘못 입력했습니다.");
        }
    }
}