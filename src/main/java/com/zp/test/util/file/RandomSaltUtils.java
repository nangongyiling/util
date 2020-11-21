package com.zp.test.util.file;

import java.util.Random;

public class RandomSaltUtils {
	
	public static String getRandomSalt(int n){
		char[] c = {'A','B','C','D','1','2','3','4','5','6','7','8','9','0','E','F','G','H','I','G','K','L','M','N','O','P'
				,'R','S','T','U','V'
		};
		Random random = new Random();
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < n; i++) {
			stringBuilder.append(c[random.nextInt(c.length)]);
		}
		return stringBuilder.toString();
	}
	public static void main(String[] args) {
		System.out.println(getRandomSalt(6));
	}
}
