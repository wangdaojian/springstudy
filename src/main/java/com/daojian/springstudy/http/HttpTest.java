package com.daojian.springstudy.http;

import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;

public class HttpTest {
	
	
	public static void main(String[] args) {
		long start = 32637499;
		long mil = 2000;
		String money =  "5533.75";
		
		
		long startTime = System.currentTimeMillis();
		boolean suc = getData(start, mil, money);
		long milTime = System.currentTimeMillis() - startTime;
		System.out.println(milTime);
		System.out.println(suc);
	}
	
	public static boolean getData(long start, long mil, String money) {
		boolean suc = false;
		String url = "https://www.xiaoniu88.com/product/detail/5/";
		
		for(long i = 0; i<mil; i++) {
			String queryUrl = url + start;
			HttpClientResult result;
			try {
				result = HttpClient.doPost(queryUrl);
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
			} catch (Exception e) {
				//e.printStackTrace();
			}
			start += 1;
		}
		return suc;
	}
}
