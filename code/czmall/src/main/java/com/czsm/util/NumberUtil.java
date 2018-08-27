package com.czsm.util;

/**
 * 数字相关工具类（比如取几位随机数）
 * 
 * @author Mac（刘平） 20180804
 */
public class NumberUtil {
	/**
	 * 根据长度返回对应的随机数
	 * 
	 * @param numLength 传入长度
	 * @return 随机数
	 */
	public static String getRandomNum(int numLength) {
		String num = "";
		for (int i = 0; i < numLength; i++) {
			num += (int) (Math.random() * 10);
		}
		return num;
	}

	public static void main(String[] args) {
		System.out.println(NumberUtil.getRandomNum(4));
	}
}
