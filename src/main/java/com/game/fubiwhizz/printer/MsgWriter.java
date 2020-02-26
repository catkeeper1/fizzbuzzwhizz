package com.game.fubiwhizz.printer;

import java.io.IOException;
import java.io.Writer;

import com.game.fubiwhizz.exception.GameException;

public class MsgWriter {
	
	private Writer writer = null;

	public MsgWriter(Writer writer) {
		super();
		this.writer = writer;
	}
	
	public void write(String s){
		
		try{
			
			this.writer.write(s);
			
		}catch(IOException e){
			
			throw new GameException("IOException if found during message generation.", e);
			
		}
		
	}
	
}
