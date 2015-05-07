package com.design.command;

public class OpenFileCommand implements Command{

	FileSystemOperation fileOperation;
	
	OpenFileCommand(FileSystemOperation fileSystemOperation){
		this.fileOperation=fileSystemOperation;
	}

	
	public void setFileOperation(FileSystemOperation fileOperation) {
		this.fileOperation = fileOperation;
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		fileOperation.openFile();
	}

}
