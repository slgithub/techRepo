package com.design.strategy;

public class Item {
	 
    private String upcCode;
    private double price;
     
    public Item(String upc, double cost){
        this.upcCode=upc;
        this.price=cost;
    }
 
    public String getUpcCode() {
        return upcCode;
    }
 
    public double getPrice() {
        return price;
    }
     
}