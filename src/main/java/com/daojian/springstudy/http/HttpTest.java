package com.daojian.springstudy.http;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;

public class HttpTest {
	
	
	public static void main(String[] args) {
		/*String[] moneys = {"12843.15", "12847.34", "10469.13", "4178.28", 
				"611.96", "609.64", "489.85", "527.83", "18.72", "731.61", "323.26", 
				"2096.67", "2090.00", "34.33", "427.05", "1431.09", "1698.54", 
				"1985.01", "73.95", "33.97", "177.10", "4146.02", "119.61", "819.53", 
				"430.34", "2193.86"};*/
		String[] moneys = {"695.25", "401.87", "3330.91", "1395.81", "3706.78", "293.59", "446.34",
				"2581.50", "5225.95"};
		final List<String> moneyList = Arrays.asList(moneys);
		long start = 36084046;//35556570
		//long start = 35223091;
		//34238579 
		final long mil = 2000;
		int threads = 3;
		//final String money =  "307.06";
		Date now = new Date();
	    SimpleDateFormat ft = new SimpleDateFormat ("yyyyMMddhhmm");
	    String fileName = ft.format(now);
		try {  
            PrintStream print=new PrintStream("/testniu" + fileName + ".txt");  //写好输出位置文件
            System.setOut(print);  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        }  
		
		long startTime = System.currentTimeMillis();
		
		final CountDownLatch countDownLatch = new CountDownLatch(threads);
		for(int i=0; i<threads; i++) {
			final int threadID = i;
			final long begin = start + i * mil;
			new Thread() {
                public void run() {
                	getData(begin, mil);
                    countDownLatch.countDown();
                }
                
                public boolean getData(long start, long mil) {
            		boolean suc = false;
            		long i = 0;
            		String url = "https://www.xiaoniu88.com/product/detail/5/";
            		for(i = 0; i<mil; i++) {
            			try {
							Thread.sleep(new Random().nextInt(100));
						} catch (Exception e1) {
							e1.printStackTrace();
						}
            			String queryUrl = url + start;
            			try {
            				HttpClientResult result = HttpClient.doPost(queryUrl);
            				if(result != null && StringUtils.hasLength(result.getContent())) {
            					JSONObject jsonObj = JSONObject.parseObject(result.getContent()).getJSONArray("data").getJSONObject(0);
            					String productName = jsonObj.getString("productName");
            					String productId = jsonObj.getString("productId");
            					String productAmount = jsonObj.getString("productAmount");
            					String leftAmount = jsonObj.getString("leftAmount");
            					String publishTime = jsonObj.getString("publishTime");
            				    SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
            				    String publishDate = ft.format(new Date(Long.parseLong(publishTime)));
            					//if(productAmount.equals(money)) {
            					System.out.println(publishDate + ": " + productName + ", " + productId + ", leftAmount=" + leftAmount + ", productAmount=" + productAmount);
            					/*if(moneyList.contains(productAmount) || moneyList.contains(leftAmount)) {
            						System.out.println("productName= " + productName + ", https://www.xiaoniu88.com/product/bid/detail/" + productId + ", productAmount=" + productAmount);
            						suc = true;
            						//break;
            					}*/
            				}
            				/*String result = HttpRequest.post(queryUrl);
            				if(result != null && StringUtils.hasLength(result)) {
            					JSONObject jsonObj = JSONObject.parseObject(result).getJSONArray("data").getJSONObject(0);
            					String productName = jsonObj.getString("productName");
            					String productId = jsonObj.getString("productId");
            					String productAmount = jsonObj.getString("productAmount");
            					System.out.println("productName= " + productName + ", " + productId + ", productAmount=" + productAmount);
            					if(moneyList.contains(productAmount)) {
            						System.out.println("productName= " + productName + ", https://www.xiaoniu88.com/product/bid/detail/" + productId + ", productAmount=" + productAmount);
            						suc = true;
            						//break;
            					}
            				}*/
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
