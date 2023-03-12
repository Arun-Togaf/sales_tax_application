package com.tax.app.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.tax.app.model.constants.SalesTaxConstants;

/**
 * Represents an Item 
 */
public class Item {
	private final int quantity;
    private final String name;
    private final BigDecimal price;
    private final boolean isImported;
    private final ItemCategory itemCategory;

    public Item(int quantity, String name, BigDecimal price, boolean isImported, ItemCategory itemCategory) {
        this.quantity = quantity;
        this.name = name;
        this.price = price;
        this.isImported = isImported;
        this.itemCategory = itemCategory;
    }
    
    /**
     * This API returns the sales tax based on the item category and import duty
     */
    public BigDecimal getSalesTax() {
        BigDecimal salesTax = BigDecimal.ZERO;
        
        // Sales tax will be applied to items except books, food and medical products 
        if ((ItemCategory.OTHERS).equals(itemCategory)) {
            salesTax = salesTax.add(price.multiply(new BigDecimal(SalesTaxConstants.SALES_TAX_PERCENTAGE)));
        }
        
        // Import duty will be applied on imported goods
        if (isImported) {
            salesTax = salesTax.add(price.multiply(new BigDecimal(SalesTaxConstants.IMPORT_TAX_PERCENTAGE)));
        }
        
        // round the value of sales tax
        salesTax = roundSalesTax(salesTax);
        
        return salesTax;
    }
    
    /**
     * This API provides the rounded value of sales tax.
     */
    private BigDecimal roundSalesTax(BigDecimal salesTax) {
        BigDecimal roundedTax = salesTax.divide(new BigDecimal(SalesTaxConstants.HALF_UP_PERCENTAGE), 0, RoundingMode.UP).multiply(new BigDecimal(SalesTaxConstants.HALF_UP_PERCENTAGE));
        return roundedTax.setScale(2, RoundingMode.HALF_UP);
    }
    
    public int getQuantity() {
        return quantity;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public boolean isImported() {
        return isImported;
    }
    
    public BigDecimal getTaxedPrice() {
        return price.add(getSalesTax());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (quantity > 1) {
            sb.append(quantity).append(" ");
        }
        if (isImported) {
            sb.append("imported ");
        }
        sb.append(name).append(": ").append(getTaxedPrice());
        return sb.toString();
    }
}