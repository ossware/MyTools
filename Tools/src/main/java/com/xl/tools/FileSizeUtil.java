package com.xl.tools;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/** 
 * getSize(String str)---是将初始大小转化为常见单位 
 * getRealNum(String str)---是将常见单位转换为初始大小(默认单位为B) 
 * plus(String str1,String str2)--是将常见单位相加(str1 + str2) 
 * subtract(String str1,String str2)--是将常见单位相减(str1 - str2) 
 * multiply(String str1,String str2)--是将常见单位相乘(str1 * str2) 
 * divide(String str1,String str2)--是将常见单位相除(str1 / str2) 
 */
public class FileSizeUtil {
	
	/**
	 * 加法
	 * 
	 * @param str1
	 * @param str2
	 * @return str1+str2
	 */
	public static String plus(String str1, String str2) {
		long numa = getRealNum(str1);
		long numb = getRealNum(str2);
		return getSize(numa + numb);
	}

	/**
	 * 减法
	 * 
	 * @param str1
	 * @param str2
	 * @return str1-str2
	 */
	public static String subtract(String str1, String str2) {
		long numa = getRealNum(str1);
		long numb = getRealNum(str2);
		return getSize(numa - numb);
	}

	/**
	 * 乘法
	 * 
	 * @param str1
	 * @param str2
	 * @return str1*str2
	 */
	public static String multiply(String str1, String str2) {
		long numa = getRealNum(str1);
		long numb = getRealNum(str2);
		return getSize(numa * numb);
	}

	/**
	 * 除法
	 * 
	 * @param str1
	 * @param str2
	 * @return str1/str2
	 */
	public static String divide(String str1, String str2) {
		long numa = getRealNum(str1);
		long numb = getRealNum(str2);
		return getSize(numa / numb);
	}

	// 获取数
	private static String getNums(String str) {
		String num = null;
		List<String> list = getList(str);
		for (int i = 0; i < list.size(); i++) {
			String a = list.get(i);
			if (isNumber(a)) {
				num = a;
			}
		}
		return num;
	}

	// 获取单位
	private static String getUnits(String str) {
		String unit = null;
		List<String> list = getList(str);
		for (int i = 0; i < list.size(); i++) {
			String a = list.get(i);
			if (isLetter(a)) {
				unit = a;
			}
		}
		return unit;
	}

	/**
	 * 转换常见单位为初始大小
	 * 
	 * @param num
	 * @param unit
	 * @return realNum
	 */
	public static Long getRealNum(String str) {
		String unit = getUnits(str);
		String num = getNums(str);
		long realNum = 0;
		Long[] nums = { getNum(0), getNum(1), getNum(2), getNum(3), getNum(4) };
		String[] units = { "B", "KB", "MB", "GB", "TB" };
		for (int k = 0; k < units.length; k++) {
			if (unit.toUpperCase().equals(units[k])) {
				long n = (long) Integer.parseInt(num);
				realNum = n * nums[k];
			}
		}
		return realNum;
	}

	// 1024的n次方
	private static Long getNum(int n) {
		return (long) Math.pow(1024, n);
	}

	// 分离英语和数字
	private static List<String> getList(String str) {
		List<String> list = new ArrayList<String>();

		String s = "\\d+.\\d|\\w+";
		Pattern pattern = Pattern.compile(s);
		Matcher ma = pattern.matcher(str);
		while (ma.find()) {
			list.add(ma.group());
		}
		return list;
	}

	// 判断是否是数字
	private static boolean isNumber(String str) {
		return isTrue("\\d+", str);
	}

	// 判断是否是字母
	private static boolean isLetter(String str) {
		return isTrue("\\w+", str);
	}

	// 判断--传入正则表达式
	private static boolean isTrue(String reg, String str) {
		Pattern pattern = Pattern.compile(reg);
		Matcher ma = pattern.matcher(str);
		while (ma.find()) {
			return true;
		}
		return false;
	}

	/**
	 * 转换初始大小为常见单位
	 * 
	 * @param size
	 * @return
	 */
	public static String getSize(long size) {
		final long unit = 1024;
		java.text.DecimalFormat df = new java.text.DecimalFormat("#.##");

		String[] units = { "B", "KB", "MB", "GB", "TB" };
		for (int i = 0; i < units.length; i++) {
			double d = getNumSize(size, i);
			if (d < unit) {
				return df.format(d) + units[i];
			}
		}
		return null;
	}

	// 单位换算
	private static double getNumSize(long size, int n) {
		double d = size / Math.pow(1024, n);
		return d;
	}

	// test
	public static void main(String[] args) {
		System.out.println(isNumber("123"));
		System.out.println(isLetter("aa"));
		System.out.println("转换为初始大小：" + getRealNum("234MB"));
		System.out.println("两字符串单位相加：" + plus("123MB", "1024KB"));
		System.out.println("小写字母的字符串相加：" + plus("123mb", "1024kb"));
	}
}
