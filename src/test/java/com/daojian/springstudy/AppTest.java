package com.daojian.springstudy;

import java.text.SimpleDateFormat;
import java.util.Date;

import junit.framework.TestCase;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase
{
		public static void main(String[] args) {
			//System.out.println(System.currentTimeMillis());
			String time = "1547003074333";
			Date now = new Date(Long.parseLong(time));
		    SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss.SSS");
		    System.out.println(ft.format(now));
		}
}
