package com.design.strategy.flyrun;

public interface Run {
	
	public void run();
}

class CanRun implements Run{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Running in Supper Speed....");
	}
	
}

class CanNotRun implements Run{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Can't Run like others");
	}
	
}