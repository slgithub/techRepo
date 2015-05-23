package com.design.strategy.flyrun;

public interface Fly {
	
	public void fly();

}

class CanFly implements Fly{

	@Override
	public void fly() {
		// TODO Auto-generated method stub
		System.out.println("Flying High.........");
	}

}

class CanNotFly implements Fly{

	@Override
	public void fly() {
		// TODO Auto-generated method stub
		System.out.println("Not Able to Fly.........");
	}

}