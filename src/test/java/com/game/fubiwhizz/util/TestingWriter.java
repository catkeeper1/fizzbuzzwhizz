package com.game.fubiwhizz.util;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class TestingWriter extends Writer {

	private List<String> messageList = new ArrayList<String>(); 
	
	public List<String> getMessageList() {
		return messageList;
	}


	private StringBuffer strBuffer = new StringBuffer();
	
	public TestingWriter(OutputStream out) {
		super(out);
		// TODO Auto-generated constructor stub
	}


	public void write(char[] cbuf, int off, int len) throws IOException {
		// TODO Auto-generated method stub
		
	}


	public void flush() throws IOException {
		// TODO Auto-generated method stub
		
	}


	public void close() throws IOException {
		// TODO Auto-generated method stub
		
	}


	
	public void write(String str) throws IOException {
		strBuffer.append(str);
		
		if(str.endsWith("\r\n")){
			this.messageList.add(strBuffer.toString());
			strBuffer.setLength(0);
		}
		
	}
	
	
	
}
