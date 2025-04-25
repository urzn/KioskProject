package com.example.kiosk;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Kiosk {
    List<MenuItem> menuItems = new ArrayList<>();

    public Kiosk(List<MenuItem> menuItems){
        this.menuItems = menuItems;
    }
    public void start(){
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println("[ SHAKESHACK MENU ]");
            for (int i = 0; i < menuItems.size(); i++){
                MenuItem menuItem = menuItems.get(i);
                System.out.println(i+1 + "." + menuItem.getName() + " | W " + menuItem.getPrice() + " | " + menuItem.getDetails());
            }
            System.out.println("0. 종료            | 종료   |");
            System.out.println("숫자를 입력하세요");
            int i = scanner.nextInt();
            if(i > 0 && i <= menuItems.size()){
                MenuItem menuItem = menuItems.get(i-1);
                System.out.println("선택한 메뉴 : "+ menuItem.getName() + " | W " + menuItem.getPrice() + " | " + menuItem.getDetails());
            }
            else if(i==0){
                System.out.println("프로그램을 종료합니다.");
                break;
            }
            else{
                System.out.println("잘못 입력했습니다.");
            }
        }
    }
}

