package com.design.strategy.flyrun;

public class Bird  extends Animal{

	public Bird(String birdName) {
		super();
		this.setName(birdName);
		this.setFlyType(new CanFly());
		this.setRunType(new CanNotRun());
		// TODO Auto-generated constructor stub
	}

	@Override
	public void motionType() {
		// TODO Auto-generated method stub"
		System.out.println("I AM "+ this.getName() +"-------");
		this.getFlyType().fly();
		this.getRunType().run();
	}

}
