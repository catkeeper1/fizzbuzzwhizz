package com.game.fubiwhizz.printer;

import com.game.fubiwhizz.Context;


public abstract class FixedMsgPrinter implements IPrinter {
	
	public void print(Context c, MsgWriter stream ){
		
		if(this.enableForPrint(c)){
			printMessage(stream, c);
		}
		
	}
	
	protected abstract boolean enableForPrint(Context c);
	
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	protected void printMessage(MsgWriter stream, Context c){
		
		stream.write(this.message);
		
		c.setPrinted(true);
	}

	public FixedMsgPrinter(String message) {
		super();
		this.message = message;
	}
	
	
}
