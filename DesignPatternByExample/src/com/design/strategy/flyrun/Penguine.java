package com.design.strategy.flyrun;

public class Penguine extends Animal{

	public Penguine(String PenguineName) {
		super();
		this.setName(PenguineName);
		this.setFlyType(new CanFly());
		this.setRunType(new CanRun());
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