/**
 * 카테고리 이름을 관리
 * 카테고리에 번호를 부여해 사용자 인풋과 매치해 label을 반환
 *
 */
package com.example.kiosk;

import java.util.Map;
import java.util.HashMap;

public enum Category {
    BURGERS(1,"Burgers"),
    DRINKS(2, "Drinks"),
    DESSERTS(3, "Desserts");

    private final int code;
    private final String label;

    Category(int code, String label) {
        this.label = label;
        this.code = code;
    }

    public int getCode(){
        return code;
    }
    public String getLabel(){
        return label;
    }

    // 카테고리와 번호를 매핑
    private static final Map<Integer, Category> categoryMap = new HashMap<>();
    static{
        for (Category category : Category.values()){
            categoryMap.put(category.code, category);
        }
    }

    // 사용자 입력에 해당하는 카테고리의 label을 가져오는 메소드
    public static String getCategoryFromCode(int menuNum){
        return categoryMap.get(menuNum).label;
    }
}
