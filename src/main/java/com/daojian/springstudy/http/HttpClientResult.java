package com.daojian.springstudy.http;

public class HttpClientResult {
	/**
     * 响应状态码
     */
    private int code;

    /**
     * 响应数据
     */
    private String content;
    
    
    public HttpClientResult(int statusCode, String content) {
		this.code = statusCode;
		this.content = content;
	}
    
    public HttpClientResult(int scInternalServerError) {
    	this.code = scInternalServerError;
	}
    
    public HttpClientResult() {
    	
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
    
    
}
