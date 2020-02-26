package com.game.fubiwhizz.printer;

import com.game.fubiwhizz.Context;

/**
 * @author Administrator
 *
 */
public class PrinterChain {
	
	private IPrinter[] printerList = null;
	
	
	
	
	public PrinterChain(int noOfPrinters) {
		
		super();
		
		this.printerList = new IPrinter[noOfPrinters];
	}


	public void setPrinter(int index, IPrinter p){
		printerList[index] = p;
	}
	

	public void requestChain(Context context, MsgWriter stream) {
		
		
		//stream.print(" Number is " + num + ". ");
		
		context.reset();
		
		for(int i = 0; i < printerList.length; i++){
			
			IPrinter p = printerList[i];
			
			if(p == null){
				continue;
			}
			
			p.print(context, stream);
			
			if(!context.isDoNext()){
				break;
			}
			
		}
		
	}

}