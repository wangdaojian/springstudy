package com.daojian.springstudy.http;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;

public class HttpTest {
	
	
	public static void main(String[] args) {
		String[] moneys = {"10911.60","10915.19","10480.88","4182.39",
				"612.56","610.24","404.33","435.68","15.46","603.88","266.82","2098.74","2092.06",
				"34.38","427.47","1432.50","1700.95","1986.97","74.02","34.00","4150.11",
				"119.77","820.34","430.76","2196.02"};
		final List<String> moneyList = Arrays.asList(moneys);
		long start = 36316340;//35556560
		//long start = 35223091;
		//34238579 
		final long mil = 6000;
		int threads = 3;
		final String money =  "307.06";
		
		
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
            					if(moneyList.contains(productAmount) || moneyList.contains(leftAmount)) {
            						System.out.println("productName= " + productName + ", https://www.xiaoniu88.com/product/bid/detail/" + productId + ", productAmount=" + productAmount);
            						suc = true;
            						//break;
            					}
            				}
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
