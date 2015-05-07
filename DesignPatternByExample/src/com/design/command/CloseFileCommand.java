package com.design.command;

public class CloseFileCommand implements Command {

	FileSystemOperation fileOperation;
	
	CloseFileCommand(FileSystemOperation fileSystemOperation){
		this.fileOperation=fileSystemOperation;
	}

	public void setFileOperation(FileSystemOperation fileOperation) {
		this.fileOperation = fileOperation;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		fileOperation.closeFile();
	}

}
