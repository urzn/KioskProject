package com.example.kiosk;

public enum DiscountRate {
    VETERAN("국가유공자", 10),
    SOLDIER("군인", 5),
    STUDENT("학생", 3),
    GENERAL("일반", 0);

    private final String label;
    private final int discountRate;

    DiscountRate(String label, int discountRate){
        this.label = label;
        this.discountRate = discountRate;
    }

    public String getLabel(){
        return label;
    }

    public int getDiscountRate(){
        return discountRate;
    }

}
