package com.game.fubiwhizz;

public class Context{
	
	private int curPlayer = 0;
	
	private boolean includedFirstNum;
	
	private boolean doNext;
	
	private boolean printed;

	public boolean isDoNext() {
		return doNext;
	}

	public void setDoNext(boolean doNext) {
		this.doNext = doNext;
	}

	public boolean isPrinted() {
		return printed;
	}

	public void setPrinted(boolean printed) {
		this.printed = printed;
	}
	
	public int getCurPlayer() {
		return curPlayer;
	}

	public int nextPlayer() {

		return ++this.curPlayer;
		
	}

	public boolean isIncludedFirstNum() {
		return includedFirstNum;
	}

	public void setIncludedFirstNum(boolean includedFirstNum) {
		this.includedFirstNum = includedFirstNum;
	}
	
	public void reset(){
		this.doNext = true;
		this.printed = false;
	}

	public Context() {
		super();
		this.reset();
	}
	
	
}