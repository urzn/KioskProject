/**
 * 메뉴 아이템을 관리하는 메뉴 클래스
 * 메뉴아이템 리스트와 카테고리 변수
 * 메뉴아이템을 리스트로 반환하는 getter
 */

package com.example.kiosk;

import java.util.*;

public class Menu {
    private List<MenuItem> menuItems = new ArrayList<>();
    private final Category category;

    public Menu (Category category, List<MenuItem> menuItems){
        this.menuItems = menuItems;
        this.category = category;
    }

    public String getCategories(){
        return category.getLabel();
    }

    public List<MenuItem> getMenuItems(){
        return menuItems;
    }

}
