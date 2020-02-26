package com.game.fubiwhizz;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import com.game.fubiwhizz.exception.GameException;
import com.game.fubiwhizz.printer.DividePrinter;
import com.game.fubiwhizz.printer.IPrinter;
import com.game.fubiwhizz.printer.IncludeFirstNumPrinter;
import com.game.fubiwhizz.printer.MsgWriter;
import com.game.fubiwhizz.printer.NormalNumPrinter;
import com.game.fubiwhizz.printer.PrinterChain;

public class FiBuWhizzGame {

	private byte param1;
	private byte param2;
	private byte param3;

	private int noOfPlayers;

	private MsgWriter outPutStream;

	private byte numArray[] = null;

	private Context context = new Context();

	private PrinterChain printerChain = null;

	private short includeFirstParam;

	public FiBuWhizzGame(byte param1, byte param2, byte param3,
			int noOfPlayers, Writer outPutStream) {
		super();
		this.param1 = param1;
		this.param2 = param2;
		this.param3 = param3;
		this.noOfPlayers = noOfPlayers;
		this.outPutStream = new MsgWriter(outPutStream);

		this.init();
	}

	private void init() {
		int pow = 10;
		int noOfDigit = 2;

		while (pow <= this.noOfPlayers) {
			pow = pow * 10;
			noOfDigit++;
		}

		this.numArray = new byte[noOfDigit - 1];

	}

	private boolean goToNextPlayer() {

		if (this.context.getCurPlayer() >= this.noOfPlayers) {
			return false;
		}

		this.context.nextPlayer();

		for (int i = 0; i < this.numArray.length; i++) {
			byte curDigVal = numArray[i];
			if (curDigVal == 9) {
				this.numArray[i] = 0;
				continue;
			}

			this.numArray[i] = (byte) (curDigVal + 1);

			if (this.numArray[i] == this.param1) {
				this.includeFirstParam = (short) (this.includeFirstParam | (1 << i));
			} else {
				this.includeFirstParam = (short) (this.includeFirstParam & (~(1 << i)));
			}

			break;
		}

		this.context.setIncludedFirstNum(this.includeFirstParam > 0);

		return true;
	}

	public static void main(String[] args) throws IOException {

		Writer writer = new BufferedWriter(new OutputStreamWriter(System.out),
				1024);

		if (args.length < 4) {
			printErrorMsg();
			return;
		}

		byte param1, param2, param3;
		int noOfPlayer;

		try {
			param1 = Byte.parseByte(args[0]);
			param2 = Byte.parseByte(args[1]);
			param3 = Byte.parseByte(args[2]);
			noOfPlayer = Integer.parseInt(args[3]);
		} catch (NumberFormatException e) {
			printErrorMsg();
			return;
		}

		FiBuWhizzGame game = new FiBuWhizzGame(param1, param2, param3,
				noOfPlayer, writer);

		game.startGame();

		writer.flush();
		writer.close();
	}

	private static void printErrorMsg() {
		System.out.println("Please specify 4 parameters.");
		System.out
				.println("The first 3 parameters are 3 numbers between 1 to 9. They must be unique.");
		System.out
				.println("The 4th parameter is the number of people in this game. It must be higher or equal to 10.");
		System.out.println("For example \"3 5 7 100\".");
	}

	
	public void startGame() throws GameException {

		this.buildPrinterChain();

		this.playGame();

	}

	private void buildPrinterChain() {
		PrinterChain chain = new PrinterChain(6);

		chain.setPrinter(0, new IncludeFirstNumPrinter("Fizz\r\n"));
		chain.setPrinter(1, new DividePrinter(this.param1, "Fizz"));
		chain.setPrinter(2, new DividePrinter(this.param2, "Buzz"));
		chain.setPrinter(3, new DividePrinter(this.param3, "Whizz"));
		chain.setPrinter(4, new NormalNumPrinter());
		
		IPrinter p = new IPrinter(){

			public void print(Context c, MsgWriter stream) {
				stream.write("\r\n");
				
			}
			
		};
		
		chain.setPrinter(5, p);

		this.printerChain = chain;
	}

	private void playGame() {

		while (this.goToNextPlayer()) {

			this.printerChain.requestChain(this.context, this.outPutStream);
		}

	}

}
