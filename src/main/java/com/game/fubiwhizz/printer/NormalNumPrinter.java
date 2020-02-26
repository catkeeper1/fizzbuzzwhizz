package com.game.fubiwhizz.printer;

import com.game.fubiwhizz.Context;


public class NormalNumPrinter implements IPrinter {

	
	public NormalNumPrinter() {
		super();
	}
	

	public void print(Context c, MsgWriter stream)  {
		if(c.isPrinted()){
			return;
		}
		
		stream.write("" + c.getCurPlayer());
			
		c.setPrinted(true);
	}

}
