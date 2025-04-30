/**
 * 메뉴아이템 하나 혹은 리스트 전체 출력 기능
 */

package com.example.kiosk;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public interface PrintMenu {

    // 메뉴아이템 하나를
    // "SmokeShack | W 8.9 | 베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거"
    // 형식으로 출력
    default void printMenuItem(MenuItem menuItem){
        System.out.println(menuItem.getName() + " | W " + menuItem.getPrice() + " | " + menuItem.getDetails());
    }

    // 메뉴아이템 리스트를 같은 형식으로 출력
    default void printMenuList(List<MenuItem> menuItems){
        AtomicInteger counter = new AtomicInteger(1);
        menuItems.stream()
                .forEach(item -> {
                    System.out.print(counter.getAndIncrement()+". ");
                    printMenuItem(item);
                });
        }
}
