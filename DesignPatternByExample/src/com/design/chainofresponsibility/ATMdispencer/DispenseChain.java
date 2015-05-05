package com.design.chainofresponsibility.ATMdispencer;

public interface DispenseChain {

	void setNextChain(DispenseChain nextChain);

	void dispense(Currency cur);
}