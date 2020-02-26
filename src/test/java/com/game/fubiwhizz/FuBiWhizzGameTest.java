package com.game.fubiwhizz;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import mockit.Deencapsulation;
import mockit.Mock;
import mockit.MockUp;
import mockit.Mocked;
import mockit.NonStrictExpectations;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.game.fubiwhizz.exception.GameException;
import com.game.fubiwhizz.printer.DividePrinter;
import com.game.fubiwhizz.printer.FixedMsgPrinter;
import com.game.fubiwhizz.printer.IPrinter;
import com.game.fubiwhizz.printer.IncludeFirstNumPrinter;
import com.game.fubiwhizz.printer.MsgWriter;
import com.game.fubiwhizz.printer.NormalNumPrinter;
import com.game.fubiwhizz.printer.PrinterChain;

@RunWith(JUnit4.class)
public class FuBiWhizzGameTest {
	
	//@Mocked
	//final private FiBuWhizzGame game = null;
	
	@Test
    public void testStartGame() throws Exception {
		new MockUp<FiBuWhizzGame>() {
	         @Mock(invocations = 1)
	         void buildPrinterChain(){
	            
	            return ;
	         }

	         @Mock(invocations = 1)
	         void playGame(){

	         }
	      };
	      
	      FiBuWhizzGame game = new FiBuWhizzGame((byte)3, (byte)5, (byte)7, 1000, new OutputStreamWriter(System.out));
	      
	      game.startGame();
	      
	}

	
	@Test
    public void testErrorMessage() throws Exception {
		
		
		
		new MockUp<FiBuWhizzGame>() {
	         @Mock(invocations = 1)
	         void printErrorMsg(){
	            
	            return ;
	         }

	      };
	      
	     
	      FiBuWhizzGame.main(new String[]{"",""});
	      
	}
	
	
	@Test
    public <T extends PrintStream> void testErrorMessage1() throws Exception {
		
	      final List<String> strList = new ArrayList<String>();
	      
	      new MockUp<T>() {
	          @Mock void println(String x){ 
	        	  strList.add(x);
	          }
	       };
	      
	      FiBuWhizzGame.main(new String[]{"",""});
	      
	      assertEquals(strList.size(), 4);
	      
	      
	      
	}	
	
	
	@Test
    public void testInit() throws Exception {
		
		FiBuWhizzGame game = new FiBuWhizzGame((byte)1, (byte)5, (byte)9, 500, new OutputStreamWriter(System.out));
		
		//Deencapsulation.invoke(game, "buildPrinterChain");
	
		byte[] numArray = Deencapsulation.getField(game, "numArray");
		
		assertEquals(numArray.length, 3);
		
		
		game = new FiBuWhizzGame((byte)3, (byte)6, (byte)7, 1000, new OutputStreamWriter(System.out));
		numArray = Deencapsulation.getField(game, "numArray");
		assertEquals(numArray.length, 4);
		
		game = new FiBuWhizzGame((byte)5, (byte)4, (byte)2, 2000, new OutputStreamWriter(System.out));
		numArray = Deencapsulation.getField(game, "numArray");
		assertEquals(numArray.length, 4);
		
		game = new FiBuWhizzGame((byte)1, (byte)2, (byte)3, 20000, new OutputStreamWriter(System.out));
		numArray = Deencapsulation.getField(game, "numArray");
		assertEquals(numArray.length, 5);
		
	}
	
	
	@Test
    public void testbuildPrinterChain() throws Exception {
		
		FiBuWhizzGame game = new FiBuWhizzGame((byte)1, (byte)5, (byte)9, 500, new OutputStreamWriter(System.out));
		
		Deencapsulation.invoke(game, "buildPrinterChain");
		PrinterChain printerChain = Deencapsulation.getField(game, "printerChain");
		
		IPrinter[] printerList = Deencapsulation.getField(printerChain, "printerList");
		
		assertEquals(printerList.length , 6);
		
		assertTrue(printerList[0] instanceof IncludeFirstNumPrinter);
		
		assertTrue(printerList[1] instanceof DividePrinter);
		FixedMsgPrinter printer =  (FixedMsgPrinter) printerList[1];
		assertEquals(printer.getMessage(),"Fizz");
		
		assertTrue(printerList[2] instanceof DividePrinter);
		printer =  (FixedMsgPrinter) printerList[2];
		assertEquals(printer.getMessage(),"Buzz");
		
		assertTrue(printerList[3] instanceof DividePrinter);
		printer =  (FixedMsgPrinter) printerList[3];
		assertEquals(printer.getMessage(),"Whizz");
		
		assertTrue(printerList[4] instanceof NormalNumPrinter);
		
		
		
	}
	
	
	
	@Test
    public void testGoToNextPlayer() throws Exception {
		FiBuWhizzGame game = new FiBuWhizzGame((byte)1, (byte)5, (byte)9, 500, new OutputStreamWriter(System.out));
		
		for(int i = 0 ; i < 123; i ++){
			Deencapsulation.invoke(game, "goToNextPlayer");
		}
		
		byte[] numArray = Deencapsulation.getField(game, "numArray");
		
		assertEquals(numArray[0], 3);
		assertEquals(numArray[1], 2);
		assertEquals(numArray[2], 1);
		
		
		game = new FiBuWhizzGame((byte)1, (byte)5, (byte)9, 500, new OutputStreamWriter(System.out));
		
		for(int i = 0 ; i < 432; i ++){
			Deencapsulation.invoke(game, "goToNextPlayer");
		}
		
		numArray = Deencapsulation.getField(game, "numArray");
		
		assertEquals(numArray[0], 2);
		assertEquals(numArray[1], 3);
		assertEquals(numArray[2], 4);
		
	}
	
	
	@Test
    public void testPlayGame() throws Exception {
		
		
		new MockUp<FiBuWhizzGame>(){
			
			private int callTimes = 0;
			
	        @Mock()
	        boolean goToNextPlayer(){
	            
	        	if(callTimes >=3){
	        		return false;
	        	}
	        	 
	        	callTimes++;
	            return true;
	         }
		};
	
		new MockUp<PrinterChain>(){
			
	         @Mock(invocations =3)
	         void requestChain(Context context, MsgWriter stream) {
	            
	         }

		};
		
		PrinterChain chain = new PrinterChain(6);
		
		FiBuWhizzGame game = new FiBuWhizzGame((byte)1, (byte)5, (byte)9, 123, new OutputStreamWriter(System.out));
		
		Deencapsulation.setField(game, "printerChain", chain);
		
		Deencapsulation.invoke(game, "playGame");
		
		
	}
	
	@Test(expected = GameException.class)
    public void testIOException(@Mocked final OutputStreamWriter writer) throws Exception {
		
		
		new NonStrictExpectations() {{
			writer.write((String)withNotNull());
			result = new IOException("test IO exception");
		}};
		
		FiBuWhizzGame game = new FiBuWhizzGame((byte)1, (byte)5, (byte)9, 123, writer);
		
		game.startGame();
		
	}
}
