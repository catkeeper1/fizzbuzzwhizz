package com.game.fubiwhizz;

import java.io.ByteArrayOutputStream;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.game.fubiwhizz.util.DummyPrintWriter;
import com.game.fubiwhizz.util.TestingWriter;

/**
 * Unit test for simple App.
 */
@RunWith(JUnit4.class)
public class AppTest 
{
    
	/**
	 * By defeault, this test case is ignored because the jmockit-coverage is in the class path and it will
	 * make the production source execution time much higher than the test source execution time and this
	 * test case is always failed. If you want to run this test case, remove @ignore and remove jmockit-coverage
	 * from the class path.
	 * @throws Exception
	 */
	@Ignore
	@Test
    public void testPerformance() throws Exception
    {
    	ByteArrayOutputStream out = new ByteArrayOutputStream();
    	
    	DummyPrintWriter stream = new DummyPrintWriter(out);
    	
        System.out.println("This is an performance testing to compare the performance between the production implementation and the testing implementation.");
        System.out.println("Run testing implementation first.");
        
        new FiBuWhizzGame((byte)3,(byte)5,(byte)7,10000000, stream); //just let the classloader load this class so that all class has been loaded before testing.
        
        System.gc();
        
    	long t1 = System.currentTimeMillis();
        
    	FuBiWhizzTestingImpl tester = new FuBiWhizzTestingImpl((byte)3,(byte)5,(byte)7,23456789, stream);
        tester.startGame();
        System.gc();
        long t2 = System.currentTimeMillis() - t1;
        
        
        System.out.println("time for testing impl is " + t2 + " ms.");
        
        
        System.out.println("Then, run production implementation.");
        System.gc();
        
        t1 = System.currentTimeMillis();
        FiBuWhizzGame game = new FiBuWhizzGame((byte)3,(byte)5,(byte)7,23456789, stream);
        game.startGame();
        System.gc();
        long t3 = System.currentTimeMillis() - t1;
        
        System.out.println("time for production impl is " + t3 + " ms.");
        
        assertTrue("The production impl performance is worse than testing impl.", t2 > t3);
    }
    
    
	
	public void method() throws Exception{
		int i = 0; 
		if(i == 1){
			throw new Exception();
		}
	}
	
	
    
	@Test
    public void testTheWholeGame() throws Exception
    {
    	for(byte param1 = 1; param1 <=9; param1++){
    		for(byte param2 = 1; param2 <=9; param2++){
    			
    			if(param2 == param1){
    				continue;
    			}
    			
    			for(byte param3 = 1; param3 <=9; param3++){
    				
    				if(param3 == param2 || param3 == param1){
    					continue;
    				}
    				
    				
    				this.compareTestAndRealImpl(param1, param2, param3, 5478);
    				
    			}
    			
    		}
    		
    	}
        
    }
    
    private void compareTestAndRealImpl(byte param1, byte param2, byte param3, int noOfPlayers){
    	
        TestingWriter stream = new TestingWriter(new ByteArrayOutputStream());
    	
    	FuBiWhizzTestingImpl tester = new FuBiWhizzTestingImpl((byte)3,(byte)5,(byte)7,noOfPlayers, stream);
        tester.startGame();
        
        List<String> msgList1 = stream.getMessageList();
        
        
        stream = new TestingWriter(new ByteArrayOutputStream());
    	
        FiBuWhizzGame game = new FiBuWhizzGame((byte)3,(byte)5,(byte)7,noOfPlayers, stream);
        game.startGame();
        
        List<String> msgList2 = stream.getMessageList();
        
        assertEquals(msgList1.size(), noOfPlayers);
        assertEquals(msgList2.size(), noOfPlayers);
        
        for(int i = 0; i < msgList1.size(); i++){
        	//System.out.print(msgList1.get(i));
        	assertEquals(msgList1.get(i), msgList2.get(i));
        }
    }
    
}
