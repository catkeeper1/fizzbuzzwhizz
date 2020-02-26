package com.game.fubiwhizz.printer;

import com.game.fubiwhizz.Context;


public class DividePrinter extends FixedMsgPrinter {
	
	private int divisor;
	

	public DividePrinter(int divisor , String message) {
		super(message);
		this.divisor = divisor;
	}
	


	protected boolean enableForPrint(Context c) {
		if(c.getCurPlayer() % this.divisor == 0){
			return true;
		}
		
		return false;
	}
	
}
