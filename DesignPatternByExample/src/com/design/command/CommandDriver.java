/**
 * 
 */
package com.design.command;

/**
 * @author su828791
 *
 */
public class CommandDriver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FileSystemOperation fs=FileSystemReceiverUtil.getUnderlyingFileSystem();
		Command openFile = new OpenFileCommand(fs);
		FileInvoker invoke=new FileInvoker(openFile);
		invoke.invoke();

	}

}
