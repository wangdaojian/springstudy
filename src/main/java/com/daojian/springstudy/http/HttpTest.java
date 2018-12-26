package com.daojian.springstudy.http;

import java.util.concurrent.CountDownLatch;

import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;

public class HttpTest {
	
	
	public static void main(String[] args) {
		long start = 31602607;
		final long mil = 500;
		int threads = 20;
		final String money =  "246.58";
		
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
            				HttpClientResult result = HttpClient.doPost(queryUrl);
            				if(result != null && StringUtils.hasLength(result.getContent())) {
            					JSONObject jsonObj = JSONObject.parseObject(result.getContent()).getJSONArray("data").getJSONObject(0);
            					String productName = jsonObj.getString("productName");
            					String productId = jsonObj.getString("productId");
            					String productAmount = jsonObj.getString("productAmount");
            					if(productAmount.equals(money)) {
            						System.out.println("productName= " + productName + ", productId=" + productId + ", productAmount=" + productAmount);
            						suc = true;
            						break;
            					}
            				}
            				/*String result = HttpRequest.post(queryUrl);
            				if(result != null && StringUtils.hasLength(result)) {
            					JSONObject jsonObj = JSONObject.parseObject(result).getJSONArray("data").getJSONObject(0);
            					String productName = jsonObj.getString("productName");
            					String productId = jsonObj.getString("productId");
            					String productAmount = jsonObj.getString("productAmount");
            					if(productAmount.equals(money)) {
            						System.out.println("productName= " + productName + ", productId=" + productId + ", productAmount=" + productAmount);
            						suc = true;
            						break;
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
