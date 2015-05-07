package com.design.command;

public class FileInvoker {

	private Command command;
	
	public FileInvoker(Command c) {
		// TODO Auto-generated constructor stub
		this.command=c;
	}
	
	public void invoke(){
		this.command.execute();
	}
}
