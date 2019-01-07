package com.daojian.springstudy;

import java.util.Random;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
<<<<<<< HEAD
	public static void main(String[] args) {
		Random rdm = new Random();
		System.out.println(rdm.nextInt(500));
		rdm = new Random();
		System.out.println(rdm.nextInt(500));
		rdm = new Random();
		System.out.println(rdm.nextInt(500));
=======
	
	public static void main(String[] args) {
		/*System.out.println(new Random().nextInt(100));
		System.out.println(new Random().nextInt(100));
		System.out.println(new Random().nextInt(100));*/
		Integer t1 = new Integer(10);
		Integer t2 = new Integer(10);
		System.out.println(t1.equals(t2));
>>>>>>> branch 'master' of https://github.com/wangdaojian/springstudy.git
	}
	
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }
}
