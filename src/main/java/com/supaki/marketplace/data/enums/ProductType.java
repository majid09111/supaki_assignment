package com.supaki.marketplace.data.enums;

public enum ProductType {

    FIREARM("FIREARM"),
    HELMET("HELMET"),
    ARMOR("ARMOR"),
    THROWABLE("THROWABLE");

    String name;

    ProductType(String name){
        this.name=name;
    }

}
