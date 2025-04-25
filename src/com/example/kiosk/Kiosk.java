package com.example.kiosk;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Kiosk {
    private Menu menu;
    private List<MenuItem> menuItems = new ArrayList<>();
    private List<String> categories = new ArrayList<>();
    private List<Menu> menuList = new ArrayList<>();
    public Kiosk(List<Menu> menuList){
        this.menuList = menuList;
    }

    public void start(){
        Scanner scanner = new Scanner(System.in);
        int menuNum, menuItemNum;
        Menu menu;
        MenuItem menuItem;

        while(true){
            System.out.println("[ MAIN MENU ]");
            for(int i = 0; i<menuList.size(); i++){
                System.out.println((i+1) + " "+ menuList.get(i).getCategories());
            }
            System.out.println("0. 종료");

            try{
                menuNum = scanner.nextInt();
            } catch(InputMismatchException e){
                System.out.println("숫자만 입력해주세요.");
                scanner.nextLine();
                continue;
            }

            if(menuNum == 1 || menuNum ==2 || menuNum == 3){
                System.out.println("[ Burgers Menu ]");
                menu = menuList.get(menuNum-1);
                menuItems = menu.getMenuItems();
                for(int i = 0; i<menuItems.size();i++){
                    System.out.println((i+1) + ". "+menuItems.get(i).getName()+ " | W " + menuItems.get(i).getPrice()
                            + " | " + menuItems.get(i).getDetails());
                }
                System.out.println("0. 뒤로가기");

                while(true){
                    try{
                        menuItemNum = scanner.nextInt();
                    } catch(InputMismatchException e){
                        System.out.println("숫자만 입력해주세요.");
                        scanner.nextLine();
                        continue;
                    }
                    if(menuItemNum>=1 && menuItemNum <= menuItems.size()){
                        menuItem = menuItems.get(menuItemNum-1);
                        System.out.print("선택한 메뉴 : ");
                        System.out.println(menuItem.getName()+ " | W " + menuItem.getPrice() + " | " + menuItem.getDetails());
                    }
                    else if(menuItemNum == 0 ){
                        System.out.println("메인 메뉴로 돌아갑니다.");
                    }
                    else{
                        System.out.println("잘못된 입력입니다.");
                        continue;
                    }
                    break;
                }
            }
            else if(menuNum == 0){
                System.out.println("프로그램을 종료합니다.");
                return;
            }
            else{
                System.out.println("잘못된 입력입니다.");
                continue;
            }
        }
    }
}

