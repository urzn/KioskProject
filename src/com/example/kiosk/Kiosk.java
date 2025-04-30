package com.example.kiosk;

import jdk.internal.util.xml.impl.Input;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Kiosk implements PrintMenu{
    private final DiscountRate[] discountRates = DiscountRate.values();
    private List<Menu> menuList;
    public Kiosk(List<Menu> menuList){
        this.menuList = menuList;
    }

    public void start(){
        int menuNum, menuItemNum, cartAddNum, orderNum, deleteNum, discountNum; // 사용자 입력 받는 변수
        double price;
        Menu menu;
        MenuItem menuItem;
        List<MenuItem> menuItems;
        Cart cart = new Cart();

        while(true){
            // 메인 메뉴 출력
            System.out.println("\n[ MAIN MENU ]");
            for(Category c : Category.values()){
                System.out.println(c.getCode() + " " + c.getLabel());
            }

            // 메인 메뉴에서 장바구니가 비어있을 때는 0으로 프로그램 종료
            // 장바구니에 상품이 있을 때는 4, 5 메뉴 출력, 5로 종료
            if(cart.getCart().isEmpty()) {
                System.out.println("0. 종료");
            }
            else{
                System.out.println("\n[ ORDER MENU ]");
                System.out.println("4. Orders | 장바구니를 확인 후 주문합니다.");
                System.out.println("5. Cancel | 진행중인 주문을 취소합니다.");
            }

            // 메인 메뉴에서 사용자 입력 받아 [카테고리] 메뉴로 이동
            menuNum = getInput();
            if(menuNum == -1) continue;

            while(true) {
                // 메인 메뉴에서 버거, 음료, 디저트 중 하나를 입력했을 때
                // 해당 카테고리의 메뉴들을 출력
                if (menuNum == 1 || menuNum == 2 || menuNum == 3) {
                    System.out.println("\n[ " + Category.getCategoryFromCode(menuNum) + " Menu ]");
                    menu = menuList.get(menuNum - 1);
                    menuItems = menu.getMenuItems();
                    printMenuList(menuItems);
                    System.out.println("0. 뒤로가기");

                    // [카테고리] 메뉴에서 사용자 입력 받아 장바구니에 추가
                    menuItemNum = getInput();
                    if(menuItemNum == -1) continue;

                    if (menuItemNum >= 1 && menuItemNum <= menuItems.size()) {
                        menuItem = menuItems.get(menuItemNum - 1);
                        System.out.print("선택한 메뉴 : ");
                        printMenuItem(menuItem);
                        System.out.println();
                        printMenuItem(menuItem);
                        System.out.println("위 메뉴를 장바구니에 추가하시겠습니까?");
                        System.out.println("1. 확인        2. 취소");

                        // 메뉴를 장바구니에 추가하기 전 확인/취소 사용자 입력 받기
                        cartAddNum = getInput();
                        if(cartAddNum == -1) continue;

                        if (cartAddNum == 1) {
                            cart.addCart(menuItem);
                            System.out.println(menuItem.getName() + " 이 장바구니에 추가되었습니다.");
                        } else if (cartAddNum == 2) {
                            continue;
                        } else {
                            System.out.println("잘못된 입력입니다");
                            continue;
                        }

                    // [카테고리] 메뉴에서 0. 뒤로가기 를 입력했을 때
                    } else if (menuItemNum == 0) {
                        System.out.println("메인 메뉴로 돌아갑니다.");
                    } else {
                        System.out.println("잘못된 입력입니다.");
                        continue;
                    }
                    break;

                // 메인 메뉴에서 0. 종료를 입력했을 때
                } else if (menuNum == 0) {
                    System.out.println("프로그램을 종료합니다.");
                    return;

                // 메인 메뉴에서 4. Orders 를 입력했을 때의 장바구니 기능
                } else if (menuNum == 4 && !cart.getCart().isEmpty()){
                    // 장바구니에 있는 상품과 장바구니 메뉴 출력
                    System.out.println("아래와 같이 주문 하시겠습니까?\n");
                    System.out.println("[ Orders ]");
                    printMenuList(cart.getCart());
                    System.out.println("\n[ Total ]");
                    System.out.println("W " + String.format("%.1f",cart.getPrice()));
                    System.out.println("\n1. 주문하기\n2. 주문 삭제하기\n3. 메뉴판으로 돌아가기");

                    // 장바구니 메뉴에서 주문, 주문삭제 사용자 입력 받기
                    orderNum = getInput();
                    if(orderNum == -1) break;

                    // 장바구니 메뉴에서 1. 주문하기를 입력했을 때
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

                        // 할인정보 사용자 입력 받기
                        discountNum = getInput();
                        if(discountNum == -1) break;

                        // 할인정보 사용자 입력 오류
                        if(discountNum < 1 || discountNum > 4){
                            System.out.println("잘못된 입력입니다.");
                            break;
                        }

                        // 총 가격에 할인율 적용하고 주문 결과 출력
                        price = cart.getPrice()* 0.01 *
                                (100 - discountRates[discountNum-1].getDiscountRate());

                        System.out.println("주문이 완료되었습니다.");
                        System.out.println("금액은 W "+ String.format("%.2f",price)+" 입니다.");
                        return;
                    }

                    // 장바구니 메뉴에서 2. 주문 삭제를 입력했을 때
                    else if(orderNum == 2){
                        System.out.println("\n삭제할 메뉴의 번호를 입력해주세요. (0으로 뒤로가기)");
                        printMenuList(cart.getCart());

                        // 장바구니 주문 삭제 메뉴에서 삭제할 메뉴 사용자 입력 받기
                        deleteNum = getInput();
                        if(deleteNum == -1) break;

                        // 장바구니 주문 삭제 메뉴에서 사용자 입력이 잘못됐을 때
                        if(deleteNum > cart.getCart().size() || deleteNum<0){
                            System.out.println("잘못된 입력입니다.");
                            break;
                        }
                        else if(deleteNum == 0){
                            break;
                        }

                        // 장바구니 주문 삭제
                        else{
                            cart.deleteItemFromCart(cart.getCart().get(deleteNum-1));
                            System.out.println(deleteNum + "번째 메뉴가 장바구니에서 삭제되었습니다.\n");
                            if (cart.getCart().isEmpty()) {
                                System.out.println("장바구니가 비었습니다.");
                                break;
                            }
                        }

                    }

                    // 장바구니 메뉴에서 3. 메뉴판으로 돌아가기 를 입력했을 때
                    else if(orderNum == 3){
                        break;
                    }
                    else{
                        System.out.println("잘못된 입력입니다.");
                        break;
                    }

                // 메인 메뉴에서 5. Cancel 을 입력했을 때 프로그램 종료
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

    /**
     * 사용자로부터 정수를 입력 받고 반환한다.
     * 입력값이 숫자가 아닐 경우 -1 반환
     */
    private int getInput(){
        Scanner scanner = new Scanner(System.in);
        int input;
        try{
            input = scanner.nextInt();
            return input;
        }catch(InputMismatchException e){
            System.out.println("숫자만 입력해주세요.");
            scanner.nextLine();
            return -1;
        }
    }
}

