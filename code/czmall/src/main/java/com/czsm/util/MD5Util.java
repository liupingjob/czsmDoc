package com.czsm.util;

import java.security.MessageDigest;

import sun.misc.BASE64Encoder;

/**
 * MD5加密帮助类
 * 
 * @author Mac(刘平) 20180804
 *
 */
@SuppressWarnings("restriction")
public class MD5Util {
	/**
	 * 加密方法
	 * 
	 * @param str 原文
	 * @return 密文
	 */
	public static String EncoderByMd5(String str) {

		String newstr = "加密失败";
		// 确定计算方法
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			BASE64Encoder base64en = new BASE64Encoder();
			// 加密后的字符串
			newstr = base64en.encode(md5.digest(str.getBytes("utf-8")));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return newstr;
	}

	/**
	 * 密文校验
	 * 
	 * @param newpasswd 明文
	 * @param oldpasswd 密文
	 * @return
	 */
	public static boolean checkpassword(String newpasswd, String oldpasswd) {
		if (EncoderByMd5(newpasswd).equals(oldpasswd))
			return true;
		else
			return false;
	}
	
	public static void main(String[] args) {
		System.out.println(MD5Util.EncoderByMd5("1234"));
	}
}
