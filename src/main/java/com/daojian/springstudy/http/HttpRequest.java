package com.daojian.springstudy.http;

import java.io.IOException;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

public class HttpRequest {
	private static PoolingHttpClientConnectionManager cm = null;
	
	static {
		LayeredConnectionSocketFactory sslsf = null;
		try {
			sslsf = new SSLConnectionSocketFactory(SSLContext.getDefault());
		}catch(Exception e) {
			e.printStackTrace();
		}
		Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
			.register("https", sslsf)
			.register("http", new PlainConnectionSocketFactory()).build();
		cm=new PoolingHttpClientConnectionManager(socketFactoryRegistry);
		cm.setMaxTotal(200);
		cm.setDefaultMaxPerRoute(20);
	}
	
	private static CloseableHttpClient getHttpClient() {
		CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(cm).build();
		return httpClient;
	}
	
	public static String post(String url) {
		CloseableHttpClient httpClient = HttpRequest.getHttpClient();
		CloseableHttpResponse httpResponse = null;
		
		try {
			HttpPost post = new HttpPost(url);
			RequestConfig requestConfig = RequestConfig.custom()
				      .setSocketTimeout(5000)//数据传输过程中数据包之间间隔的最大时间
				      .setConnectTimeout(5000)//连接建立时间，三次握手完成时间
				      .setExpectContinueEnabled(true)//重点参数 
				      .setConnectionRequestTimeout(5000)
				      .setStaleConnectionCheckEnabled(true)//重点参数，在请求之前校验链接是否有效
				      .build();
			httpResponse = httpClient.execute(post);
			HttpEntity entity = httpResponse.getEntity();
			if(entity != null) {
				String response = EntityUtils.toString(entity);
				int statusCode = httpResponse.getStatusLine().getStatusCode();
				if (statusCode == HttpStatus.SC_OK) {
					return response;
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			if (httpResponse != null) {
				try {
					EntityUtils.consume(httpResponse.getEntity());
					httpResponse.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
}
