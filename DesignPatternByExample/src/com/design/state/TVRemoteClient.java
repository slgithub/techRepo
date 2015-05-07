package com.design.state;

public class TVRemoteClient {
	public static void main(String[] args) {
		PdkContext context = new PdkContext();
		PdkState tvStartState = new TVStartState();
		PdkState tvStopState = new TVStopState();

		context.setState(tvStartState);
		context.doAction();

		context.setState(tvStopState);
		context.doAction();

	}
}
