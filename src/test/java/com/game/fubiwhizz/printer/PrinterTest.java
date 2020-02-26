package com.game.fubiwhizz.printer;

import mockit.Expectations;
import mockit.Mocked;
import mockit.NonStrictExpectations;

import org.junit.Test;

import com.game.fubiwhizz.Context;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class PrinterTest {
	
	@Test
	public void testDividePrinter1(@Mocked final Context c, @Mocked final MsgWriter writer){
		
		new Expectations() {{
			c.getCurPlayer();
			result = 7;
			
			writer.write("ABC");
			times = 0;
		}};
		
		
		DividePrinter printer = new DividePrinter(3, "ABC");
		
		printer.print(c, writer);
	}
	
	
	@Test
	public void testDividePrinter2(@Mocked final Context c, @Mocked final MsgWriter writer){
		
		DividePrinter printer = new DividePrinter(3, "ABC");
		
		printer.print(c, writer);
		
		new Expectations() {{
			c.getCurPlayer();
			result = 9;
			
			writer.write("ABC");
			times = 1;
		}};
		
		printer = new DividePrinter(3, "ABC");
		printer.print(c, writer);
	}
	
	
	@Test
	public void testIncludeFirstNumberPrinter1(@Mocked final Context c, @Mocked final MsgWriter writer){
		
		new NonStrictExpectations() {{
			c.getCurPlayer();
			result = 7;
			
			c.isIncludedFirstNum();
			result = true;
			
			writer.write("ABC");
			times = 1;
		}};
		
		
		IncludeFirstNumPrinter printer = new IncludeFirstNumPrinter("ABC");
		
		printer.print(c, writer);
		
		
		
	}
	
	
	@Test
	public void testIncludeFirstNumberPrinter2(@Mocked final Context c, @Mocked final MsgWriter writer){
		
		new NonStrictExpectations() {{
			c.getCurPlayer();
			result = 7;
			
			c.isIncludedFirstNum();
			result = false;
			
			writer.write("ABC");
			times = 0;
		}};
		
		
		IncludeFirstNumPrinter printer = new IncludeFirstNumPrinter("ABC");
		
		printer.print(c, writer);
		
		
		
	}
	
	
	@Test
	public void testNormalNumPrinter(@Mocked final Context c, @Mocked final MsgWriter writer){
		
		new NonStrictExpectations() {{
			c.getCurPlayer();
			result = 7;
			
			c.isIncludedFirstNum();
			result = false;
			
			writer.write("7");
			times = 1;
			
			writer.write("8");
			times = 0;
		}};
		
		
		NormalNumPrinter printer = new NormalNumPrinter();
		
		printer.print(c, writer);
		
		
		
	}	
	
	
	@Test
	public void testPrinterChain( @Mocked final MsgWriter writer){
		
		Context c = new Context();
		
		IPrinter p = new IPrinter(){

			public void print(Context c, MsgWriter stream) {
				c.setDoNext(true);
				c.setPrinted(true);
			}
			
		};
		
		IPrinter p1 = new IPrinter(){

			public void print(Context c, MsgWriter stream) {
				c.setDoNext(false);
			}
			
		};
		
		IPrinter p2 = new IPrinter(){

			public void print(Context c, MsgWriter stream) {
				throw new RuntimeException("this method should not be executed.");
			}
			
		};

		
		IPrinter p3 = new IPrinter(){

			public void print(Context c, MsgWriter stream) {
				c.setPrinted(false);
			}
			
		};
		
		
		PrinterChain chain = new PrinterChain(4);
		chain.setPrinter(0, p);
		chain.setPrinter(1, null);
		chain.setPrinter(2, p1);
		chain.setPrinter(3, p2);
		
		chain.requestChain(c, writer);
		
		assertEquals(c.isPrinted(), true);
		
		
		chain = new PrinterChain(3);
		chain.setPrinter(0, p);
		chain.setPrinter(1, null);
		chain.setPrinter(2, p3);
		
		chain.requestChain(c, writer);
		
		assertEquals(c.isPrinted(), false);
	}
	
}
