package com.design.strategy;

public class PaytmPaymentStrategy implements PaymentStrategy {
 
    private String emailId;
    private String password;
     
    public PaytmPaymentStrategy(String email, String pwd){
        this.emailId=email;
        this.password=pwd;
    }
     
   
	@Override
	public void payValue(double amount) {
		// TODO Auto-generated method stub
		System.out.println(amount + " paid using PAYTM.");
	}
}