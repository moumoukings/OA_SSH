package com.jboa.action.mail;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.jboa.action.BaseAction;

@Controller("fileDownAction")
@Scope("prototype")
public class FileDownAction extends BaseAction{

	private static final long serialVersionUID = 8103645423236748859L;
	
	private String inputPath;
	private String fileName;
	private InputStream inputStream;
	public String getInputPath() {		return inputPath;	}
	public void setInputPath(String inputPath) {		this.inputPath = inputPath;	}
	public String getFileName() {		return fileName;	}
	public void setFileName(String fileName) {		this.fileName = fileName;	}
	public InputStream getInputStream() throws FileNotFoundException{
		String filePath = ServletActionContext.getServletContext().getRealPath("upload");
		return new BufferedInputStream(new FileInputStream(filePath + "\\" + fileName));
	}
	public void setInputStream(InputStream inputStream) {		this.inputStream = inputStream;	}
	
	@Override
	public String execute () throws Exception{
		return SUCCESS;
	}
}
