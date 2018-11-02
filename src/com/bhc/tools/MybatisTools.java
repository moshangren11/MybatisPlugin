/**
 * Title MybatisTools
 * Package com.bhc.tools
 * Copyright 2018 www.hundsun.com All Rights Reserved.
 *
 * @author zhanggd1816@hundsun.com
 * @date 2018-05-19 0019 13:31
 * @version V5.0.0
 */
package com.bhc.tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ClassName MybatisTools
 * Description 工具类
 * @author zhanggd16816 zhanggd16816@hundsun.com
 * @date 2018-05-19 0019 13:31
 */
public class MybatisTools {

	public static final Pattern packagePattern = Pattern.compile("^([a-zA-Z]+[.][a-zA-Z]+)[.]*.*");
	public static final Pattern tablePattern = Pattern.compile("[a-zA-Z]+[0-9a-zA-Z_,]*");
	/**
	 * 首字母大写
	 * @param str
	 * @return
	 */
	public static String captureName(String str) {
		char[] chars=str.toCharArray();
		chars[0]-=32;
		return String.valueOf(chars);
	}
	private static Pattern linePattern = Pattern.compile("_(\\w)");
	/**下划线转驼峰*/
	public static String lineToHump(String str){
		str = str.toLowerCase();
		Matcher matcher = linePattern.matcher(str);
		StringBuffer sb = new StringBuffer();
		while(matcher.find()){
			matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
		}
		matcher.appendTail(sb);
		return captureName(sb.toString());
	}
	/**驼峰转下划线(简单写法，效率低于{@link #humpToLine2(String)})*/
	public static String humpToLine(String str){
		return str.replaceAll("[A-Z]", "_$0").toLowerCase();
	}
	private static Pattern humpPattern = Pattern.compile("[A-Z]");
	/**驼峰转下划线,效率比上面高*/
	public static String humpToLine2(String str){
		Matcher matcher = humpPattern.matcher(str);
		StringBuffer sb = new StringBuffer();
		while(matcher.find()){
			matcher.appendReplacement(sb, "_"+matcher.group(0).toLowerCase());
		}
		matcher.appendTail(sb);
		return sb.toString();
	}

	/**
	 * 验证包名
	 * @param s
	 * @return
	 */
	public static boolean checkPackage(String s) {
		Matcher matcher = packagePattern.matcher(s);
		return matcher.matches();
	}

	/**
	 * 验证表名
	 * @param s
	 * @return
	 */
	public static boolean checkTable(String s){
		Matcher matcher = tablePattern.matcher(s);
		return matcher.matches();
	}

	public static void main(String[] args){
		System.out.println(checkPackage("org.hundsun.pay.model"));
		System.out.println(checkPackage("org.hundsun.pay.client"));
		System.out.println(checkPackage("org.hundsun.pay.sqlmap"));
	}

}
