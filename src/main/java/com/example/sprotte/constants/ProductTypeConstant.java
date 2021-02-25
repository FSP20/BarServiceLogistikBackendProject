package com.example.sprotte.constants;

public enum ProductTypeConstant {
    FOOD(DatabaseConstants.PRODUCT_TYPE_FOOD),
    DRINK(DatabaseConstants.PRODUCT_TYPE_DRINK),
    EQUIPMENT(DatabaseConstants.PRODUCT_TYPE_EQUIPMENT)
    ;

    private final String text;

    ProductTypeConstant(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}