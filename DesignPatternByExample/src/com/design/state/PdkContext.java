package com.design.state;

public class PdkContext implements PdkState{

	private PdkState state;
	
	public void setState(PdkState state) {
		this.state = state;
	}
	
	
	@Override
	public void doAction() {
		// TODO Auto-generated method stub
		state.doAction();
	}

}
