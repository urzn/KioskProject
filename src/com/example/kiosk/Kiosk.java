package com.example.kiosk;

import jdk.internal.util.xml.impl.Input;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Kiosk implements PrintMenu{
    private Menu menu;
    private final Category[] categories = Category.values();
    private final DiscountRate[] discountRates = DiscountRate.values();
    private List<Menu> menuList = new ArrayList<>();
    public Kiosk(List<Menu> menuList){
        this.menuList = menuList;
    }

    public void start(){
        Scanner scanner = new Scanner(System.in);
        int menuNum, menuItemNum, cartAddNum, orderNum, deleteNum, discountNum;
        double price;
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
                    System.out.println("W " + String.format("%.1f",cart.getPrice()));
                    System.out.println("\n1. 주문하기\n2. 주문 삭제하기\n3. 메뉴판으로 돌아가기");
                    try{
                        orderNum = scanner.nextInt();
                        if(orderNum == 1){
                            System.out.println("할인 정보를 입력해주세요.");
                            System.out.print("1. " + DiscountRate.VETERAN.getLabel());
                            System.out.println(" : " + DiscountRate.VETERAN.getDiscountRate() + "%");
                            System.out.print("2. " + DiscountRate.SOLDIER.getLabel());
                            System.out.println(" : " + DiscountRate.SOLDIER.getDiscountRate() + "%");
                            System.out.print("3. " + DiscountRate.STUDENT.getLabel());
                            System.out.println(" : " + DiscountRate.STUDENT.getDiscountRate() + "%");
                            System.out.print("4. " + DiscountRate.GENERAL.getLabel());
                            System.out.println(" : " + DiscountRate.GENERAL.getDiscountRate() + "%");
                            try{
                                discountNum = scanner.nextInt();
                                if(discountNum < 1 || discountNum > 4){
                                    System.out.println("잘못된 입력입니다.");
                                    break;
                                }
                                price = cart.getPrice()* 0.01 *
                                        (100 - discountRates[discountNum-1].getDiscountRate());
                            } catch(InputMismatchException e){
                                System.out.println("숫자만 입력해주세요.");
                                scanner.nextLine();
                                break;
                            }
                            System.out.println("주문이 완료되었습니다.");
                            System.out.println("금액은 W "+ String.format("%.2f",price)+" 입니다.");
                            return;
                        }
                        else if(orderNum == 2){
                            System.out.println("\n삭제할 메뉴의 번호를 입력해주세요. (0으로 뒤로가기)");
                            printMenuList(cart.getCart());
                            try{
                                deleteNum = scanner.nextInt();
                                if(deleteNum > cart.getCart().size() || deleteNum<0){
                                    System.out.println("잘못된 입력입니다.");
                                    break;
                                }
                                else if(deleteNum == 0){
                                    break;
                                }
                                else{
                                    cart.deleteItemFromCart(cart.getCart().get(deleteNum-1));
                                    System.out.println(deleteNum + "번째 메뉴가 장바구니에서 삭제되었습니다.\n");
                                    if (cart.getCart().isEmpty()) {
                                        System.out.println("장바구니가 비었습니다.");
                                        break;
                                    }
                                }
                            }catch(InputMismatchException e){
                                System.out.println("숫자만 입력해주세요.");
                                scanner.nextLine();
                                break;
                            }

                        }
                        else if(orderNum == 3){
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

