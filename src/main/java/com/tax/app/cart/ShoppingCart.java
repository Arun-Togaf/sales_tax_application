package com.tax.app.cart;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.tax.app.model.Item;

/**
 * Represents the items in the cart
 */
public class ShoppingCart {
	private static final Logger LOGGER = LogManager.getLogger(ShoppingCart.class);
	private final List<Item> items;

	public ShoppingCart() {
		this.items = new ArrayList<Item>();
	}

	public void addItem(Item item) {
		items.add(item);
	}

	/**
     * This API computes the total tax of the items.
     */
	public BigDecimal getTotalTax() {
		BigDecimal totalTax = BigDecimal.ZERO;
		for (Item item : items) {
			totalTax = totalTax.add(item.getSalesTax());
		}
		return totalTax;
	}
    
	/**
     * This API computes the total price of the items.
     */
	public BigDecimal getTotalPrice() {
		BigDecimal totalPrice = BigDecimal.ZERO;
		for (Item item : items) {
			totalPrice = totalPrice.add(item.getTaxedPrice());
		}
		return totalPrice;
	}
    
	/**
     * This API can be used to log the price details and tax information.
     */
	public void printReceipt() {

		for (Item item : items) {
			LOGGER.info(
					"Item name: " + item.getName() + " Price: " + item.getPrice() + " Quantity: " + item.getQuantity());
		}

		LOGGER.info("Sales Taxes: " + getTotalTax());
		LOGGER.info("Total: " + getTotalPrice());
	}
}
