package com.game.fubiwhizz.util;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.Writer;

public class DummyPrintWriter extends Writer {

	public DummyPrintWriter(OutputStream out) {
		super(out);
	}

	
	public void print(int i) {
		
	}

	
	public void print(String s) {
		
	}


	
	public void write(char[] cbuf, int off, int len) throws IOException {
		
	}


	public void flush() throws IOException {
		
	}



	public void close() throws IOException {

	}


	public void write(String str) throws IOException {
		
	}
	

	

}
