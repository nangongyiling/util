package com.zp.test.util.file;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/**
 * 字符串加密类
 * @author LENOVO
 *
 */
public class MD5Util {
	/** 加密生成一个32长度的16进制数字
	 * 加密字符串  
	 * @param value :原始字符串
	 * @return 返回一个32位的加密数据
	 */
	public static String encodeString(String value){
		StringBuilder sb=new StringBuilder();
		try {
			MessageDigest messageDigest=MessageDigest.getInstance("md5");
			
			byte[] bytes=messageDigest.digest(value.getBytes());
			for(int i=0;i<bytes.length;i++){
				int tempInt=bytes[i]&0xff;
				if(tempInt<16){
					sb.append(0);
				}
				sb.append(Integer.toHexString(tempInt));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sb.toString();
	}
}
