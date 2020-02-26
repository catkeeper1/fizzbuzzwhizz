package com.game.fubiwhizz.printer;

import com.game.fubiwhizz.Context;


public class IncludeFirstNumPrinter extends FixedMsgPrinter {
	
	public IncludeFirstNumPrinter(String message) {
		super(message);
	}

	protected boolean enableForPrint(Context c) {
		return c.isIncludedFirstNum();
	}

	
	protected void printMessage(MsgWriter stream, Context c) {
		super.printMessage(stream, c);
		c.setDoNext(false);
	}
	
	
	
}
