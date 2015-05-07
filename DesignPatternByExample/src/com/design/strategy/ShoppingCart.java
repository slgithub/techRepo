package com.design.strategy;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
	List<Item> cartList = null;

	ShoppingCart() {
		cartList = new ArrayList<Item>();
	}

	public void addItem(Item item) {
		cartList.add(item);
	}

	public void removeItem(Item item) {
		cartList.remove(item);
	}

	private double calculateCartValue() {
		double payAmount = 0;
		for (Item item : cartList) {
			payAmount += item.getPrice();
		}
		return payAmount;
	}
	
	public void pay(PaymentStrategy pstrategy){
		pstrategy.payValue(calculateCartValue());
	}
}
