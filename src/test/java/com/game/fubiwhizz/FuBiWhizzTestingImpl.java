package com.game.fubiwhizz;

import java.io.PrintStream;
import java.io.Writer;

public class FuBiWhizzTestingImpl {
	private byte param1;
	private byte param2;
	private byte param3;

	private int noOfPlayers;

	private Writer outPutStream;

	public FuBiWhizzTestingImpl(byte param1, byte param2, byte param3,
			int noOfPlayers, Writer outPutStream) {
		super();
		this.param1 = param1;
		this.param2 = param2;
		this.param3 = param3;
		this.noOfPlayers = noOfPlayers;
		this.outPutStream = outPutStream;

	}

	public void startGame() {
		try {

			for (int i = 1; i <= this.noOfPlayers; i++) {

				if ((i + "").indexOf("" + this.param1) != -1) {

					this.outPutStream.write("Fizz\r\n");

				} else {

					boolean printed = false;
					if (i % this.param1 == 0) {
						this.outPutStream.write("Fizz");
						printed = true;
					}

					if (i % this.param2 == 0) {
						this.outPutStream.write("Buzz");
						printed = true;
					}

					if (i % this.param3 == 0) {
						this.outPutStream.write("Whizz");
						printed = true;
					}

					if (!printed) {
						this.outPutStream.write("" + i);
					}

					this.outPutStream.write("\r\n");

				}

			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

}
