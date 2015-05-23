package com.design.strategy.flyrun;

public class Dog extends Animal{

	public Dog(String dogName) {
		super();
		this.setName(dogName);
		this.setFlyType(new CanNotFly());
		this.setRunType(new CanRun());
		// TODO Auto-generated constructor stub
	}

	@Override
	public void motionType() {
		// TODO Auto-generated method stub
		System.out.println("I AM "+ this.getName() +"-------");
		
		this.getFlyType().fly();
		this.getRunType().run();
	}

}
