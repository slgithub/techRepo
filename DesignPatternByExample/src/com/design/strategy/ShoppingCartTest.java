package com.design.strategy;

public class ShoppingCartTest {
	public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();
         
        Item item1 = new Item("1234",100.00);
        Item item2 = new Item("5678",40.99);
         
        cart.addItem(item1);
        cart.addItem(item2);
         
        //pay by payTM
        cart.pay(new PaytmPaymentStrategy("myemail@example.com", "mypwd"));
         
        //pay by credit card
        cart.pay(new CreditCardPaymentStrategy("SUMANTA LAHA", "1234567890123456", "786", "12/15"));
    }
}
