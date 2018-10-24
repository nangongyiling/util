package com.zp.test.util;

import javax.servlet.http.HttpServletRequest;

/**
 * get请求的中文乱码解决方案，tomcat8以下才有问题
 * @author lenovo
 *
 */
public class UrlDecodeUtil {

	public static String getValue(HttpServletRequest request,String key) {
		try {
			String str=request.getQueryString();
		   	 
	    	//使用URLDecoder解码字符串
	    	 
	    	String str1=java.net.URLDecoder.decode(str,"utf-8");
	    	 
	    	String[] paraStrings=str1.split("&");
	    	 
	    	//paraStrings[0]就是第一个参数，依次类推...
	    	 
	    	for(String paraString : paraStrings)
	    	 
	    	{
	    	 
	    	 String[] nameValue=paraString.split("=");
	    	 
	    	 //nameValue[0]就是表单的name，nameValue[1]就是表单name对应的值
	    	 
	    	 if(key.equals(nameValue[0])) {
	    		 return nameValue[1];
	    	 }
	    	}
		}catch (Exception e) {
			
		}
		return null;
		
	}
}
