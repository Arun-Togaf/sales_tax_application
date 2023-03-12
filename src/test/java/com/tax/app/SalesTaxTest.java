package com.tax.app;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

import com.tax.app.cart.ShoppingCart;
import com.tax.app.model.Item;
import com.tax.app.model.ItemCategory;

public class SalesTaxTest {
	
	ShoppingCart shoppingCart;

    @Test
    public void testGetTotalPriceInput1() {
        shoppingCart = new ShoppingCart();
        shoppingCart.addItem(new Item(1, "Book", new BigDecimal("12.49"), false, ItemCategory.BOOK));
        shoppingCart.addItem(new Item(1, "Music Cd", new BigDecimal("14.99"), false, ItemCategory.OTHERS));
        shoppingCart.addItem(new Item(1, "Chocolate bar", new BigDecimal("0.85"), false, ItemCategory.FOOD));
        assertEquals(new BigDecimal("1.50"), shoppingCart.getTotalTax());
        assertEquals(new BigDecimal("29.83"), shoppingCart.getTotalPrice());
    }
    
    @Test
    public void testGetTotalPriceInput2() {
        shoppingCart = new ShoppingCart();
        shoppingCart.addItem(new Item(1, "Chocolate bar", new BigDecimal("10.00"), true, ItemCategory.FOOD));
        shoppingCart.addItem(new Item(1, "Perfume", new BigDecimal("47.50"), true, ItemCategory.OTHERS));
        assertEquals(new BigDecimal("7.65"), shoppingCart.getTotalTax());
        assertEquals(new BigDecimal("65.15"), shoppingCart.getTotalPrice());
    }
    
    @Test
    public void testGetTotalPriceInput3() {
        shoppingCart = new ShoppingCart();
        shoppingCart.addItem(new Item(1, "Imported perfume", new BigDecimal("27.99"), true, ItemCategory.OTHERS));
        shoppingCart.addItem(new Item(1, "Perfume", new BigDecimal("18.99"), false, ItemCategory.OTHERS));
        shoppingCart.addItem(new Item(1, "Headache pills", new BigDecimal("9.75"), false, ItemCategory.MEDICINE));
        shoppingCart.addItem(new Item(1, "Chocolate bar", new BigDecimal("11.25"), true, ItemCategory.FOOD));
        assertEquals(new BigDecimal("6.70"), shoppingCart.getTotalTax());
        assertEquals(new BigDecimal("74.68"), shoppingCart.getTotalPrice());
    }
    
}
