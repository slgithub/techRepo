package com.design.strategy.flyrun;

public abstract class Animal {

	protected String name;
	protected Fly flyType;
	protected Run runType;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Fly getFlyType() {
		return flyType;
	}

	public void setFlyType(Fly flyType) {
		this.flyType = flyType;
	}

	public Run getRunType() {
		return runType;
	}

	public void setRunType(Run runType) {
		this.runType = runType;
	}

	public abstract void motionType();
}
