package com.design.command;

public class WriteFileCommand implements Command {

	FileSystemOperation fileOperation;
	
	WriteFileCommand(FileSystemOperation fileSystemOperation){
		this.fileOperation=fileSystemOperation;
	}

	public void setFileOperation(FileSystemOperation fileOperation) {
		this.fileOperation = fileOperation;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		fileOperation.writeFile();
	}

}
