package com.daojian.springstudy.http;

import java.net.SocketTimeoutException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;

public class HttpTest {
	
	
	public static void main(String[] args) {
		String[] moneys = {"203.75", "1382.71", "397.20", "3295.99", "6790.82", "480.94", 
				"267.23", "6051.06", "2955.87"};
		/*String[] moneys = {"10673.43", "12792.95", "31582.45", "12797.13", "10433.86", "12763.66", "4197.82", 
				"614.82", "607.85", "9371.95", "31582.45", "487.78", "2225.16", "525.60", "18.64", "728.52", 
				"321,89", "2106.49", "2099.78", "34.18", "429.05", "1437.79", "1691.32", "1994.31", "74.30", 
				"34.12", "177.93", "4165.43", "119.14", "823.37", "538.43", "2204.13"};
		*/ 
		final List<String> moneyList= Arrays.asList(moneys);
		System.out.println(moneyList);
		long start = 35010960;//35013055
		//long start = 34619022;//34590755
		final long mil = 60;
		int threads = 2;
		final String money =  "464.14";
		/*PrintStream ps;
		try {
			ps = new PrintStream("e:/log.txt");
			System.setOut(ps);
		} catch (FileNotFoundException e2) {
			e2.printStackTrace();
		} */
		
		long startTime = System.currentTimeMillis();
		
		final CountDownLatch countDownLatch = new CountDownLatch(threads);
		for(int i=0; i<threads; i++) {
			final int threadID = i;
			final long begin = start + i * mil;
			new Thread() {
                public void run() {
                	getData(begin, mil, money);
                    countDownLatch.countDown();
                }
                
                public boolean getData(long start, long mil, String money) {
            		boolean suc = false;
            		long i = 0;
            		String url = "https://www.xiaoniu88.com/product/detail/5/";
            		for(i = 0; i<mil; i++) {
            			String queryUrl = url + start;
            			try {
            				Random rdm = new Random();
							Thread.sleep(rdm.nextInt(100));
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}
            			try {
            				HttpClientResult result = HttpClient.doPost(queryUrl);
            				if(result != null && StringUtils.hasLength(result.getContent())) {
            					JSONObject jsonObj = JSONObject.parseObject(result.getContent()).getJSONArray("data").getJSONObject(0);
            					String productName = jsonObj.getString("productName");
            					String productId = jsonObj.getString("productId");
            					String productAmount = jsonObj.getString("productAmount");
            					//System.out.println("productName=" + productName + ", productId=" + productId + ", productAmount=" + productAmount);
            					//if(productAmount.equals(money)) {
            					if(moneyList.contains(productAmount)) {
            						System.out.println("productName= " + productName + ", https://www.xiaoniu88.com/product/bid/detail/" + productId + ", productAmount=" + productAmount);
            						suc = true;
            						//break;
            					}
            				}
            			} catch (SocketTimeoutException e) {
            				//e.printStackTrace();
            			} catch (Exception e) {
            				//e.printStackTrace();
            			}
            			start += 1;
            		}
            		System.out.println("threadID=" + threadID + ", 查询次数：" + i);
            		return suc;
            	}
            }.start();
		}
		try {
			countDownLatch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		long milTime = System.currentTimeMillis() - startTime;
		System.out.println(milTime);
		
	}
}
