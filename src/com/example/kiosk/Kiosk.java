package com.example.kiosk;

import jdk.internal.util.xml.impl.Input;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Kiosk implements PrintMenu{
    private Menu menu;
    private final Category[] categories = Category.values();
    private List<Menu> menuList = new ArrayList<>();
    public Kiosk(List<Menu> menuList){
        this.menuList = menuList;
    }

    public void start(){
        Scanner scanner = new Scanner(System.in);
        int menuNum, menuItemNum, cartAddNum, orderNum;
        Menu menu;
        List<MenuItem> menuItems;
        MenuItem menuItem;
        Cart cart = new Cart();

        while(true){
            System.out.println("\n[ MAIN MENU ]");
            for(int i = 0; i<menuList.size(); i++){
                System.out.println((i+1) + " "+ menuList.get(i).getCategories());
            }
            System.out.println("0. 종료");

            if(!cart.getCart().isEmpty()) {
                System.out.println("\n[ ORDER MENU ]");
                System.out.println("4. Orders | 장바구니를 확인 후 주문합니다.");
                System.out.println("5. Cancel | 진행중인 주문을 취소합니다.");
            }

            try{
                menuNum = scanner.nextInt();
            } catch(InputMismatchException e){
                System.out.println("숫자만 입력해주세요.");
                scanner.nextLine();
                continue;
            }

            while(true) {
                if (menuNum == 1 || menuNum == 2 || menuNum == 3) {
                    System.out.println("\n[ " + categories[menuNum - 1].getLabel() + " Menu ]");
                    menu = menuList.get(menuNum - 1);
                    menuItems = menu.getMenuItems();
                    printMenuList(menuItems);
                    System.out.println("0. 뒤로가기");

                    try {
                        menuItemNum = scanner.nextInt();
                    } catch (InputMismatchException e) {
                        System.out.println("숫자만 입력해주세요.");
                        scanner.nextLine();
                        continue;
                    }
                    if (menuItemNum >= 1 && menuItemNum <= menuItems.size()) {
                        menuItem = menuItems.get(menuItemNum - 1);
                        System.out.print("선택한 메뉴 : ");
                        printMenuItem(menuItem);
                        System.out.println();
                        printMenuItem(menuItem);
                        System.out.println("위 메뉴를 장바구니에 추가하시겠습니까?");
                        System.out.println("1. 확인        2. 취소");
                        try {
                            cartAddNum = scanner.nextInt();
                            if (cartAddNum == 1) {
                                cart.addCart(menuItem);
                                System.out.println(menuItem.getName() + " 이 장바구니에 추가되었습니다.");
                            } else if (cartAddNum == 2) {
                                continue;
                            } else {
                                System.out.println("잘못된 입력입니다");
                                continue;
                            }

                        } catch (InputMismatchException e) {
                            System.out.println("숫자만 입력해주세요.");
                            scanner.nextLine();
                            continue;
                        }
                    } else if (menuItemNum == 0) {
                        System.out.println("메인 메뉴로 돌아갑니다.");
                    } else {
                        System.out.println("잘못된 입력입니다.");
                        continue;
                    }
                    break;
                } else if (menuNum == 0) {
                    System.out.println("프로그램을 종료합니다.");
                    return;
                } else if (menuNum == 4 && !cart.getCart().isEmpty()){
                    System.out.println("아래와 같이 주문 하시겠습니까?\n");
                    System.out.println("[ Orders ]");
                    printMenuList(cart.getCart());
                    System.out.println("\n[ Total ]");
                    System.out.println("W " + cart.getPrice());
                    System.out.println("\n1. 주문 2. 메뉴판");
                    try{
                        orderNum = scanner.nextInt();
                        if(orderNum == 1){
                            System.out.println("주문이 완료되었습니다.");
                            System.out.println("금액은 W "+ String.format("%.1f",cart.getPrice())+" 입니다.");
                            return;
                        }
                        else if(orderNum == 2){
                            break;
                        }
                        else{
                            System.out.println("잘못된 입력입니다.");
                            break;
                        }
                    } catch(InputMismatchException e){
                        System.out.println("숫자만 입력해주세요.");
                        scanner.nextLine();
                        break;
                    }

                } else if (menuNum == 5 && !cart.getCart().isEmpty()){
                    System.out.println("프로그램을 종료합니다.");
                    return;
                }
                else {
                    System.out.println("잘못된 입력입니다.");
                    break;
                }
            }
        }
    }
}

