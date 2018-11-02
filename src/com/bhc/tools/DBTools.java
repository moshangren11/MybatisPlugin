/**
 * Title DBTools
 * Package com.bhc.tools
 * Copyright 2018 www.hundsun.com All Rights Reserved.
 *
 * @author zhanggd1816@hundsun.com
 * @date 2018-05-19 0019 22:56
 * @version V5.0.0
 */
package com.bhc.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * ClassName DBTools
 * Description 数据库连接工具
 *
 * @author zhanggd16816 zhanggd16816@hundsun.com
 * @date 2018-05-19 0019 22:56
 */
public class DBTools {

	public static final String MYSQL_DRIVER = "com.mysql.jdbc.Driver";
	public static final String ORACLE_DRIVER = "oracle.jdbc.driver.OracleDriver";
	public static final String MYSQL_CONFIG = "?useUnicode=true&amp;characterEncoding=utf8";

	/**
	 * 生成mysqlURL
	 *
	 * @param host
	 * @param port
	 * @param dbname
	 * @return
	 */
	public static String buildMysqlUrl(String host, String port, String dbname) {
		StringBuilder sb = new StringBuilder("jdbc:mysql://");
		sb.append(host).append(":").append(port).append("/").append(dbname);
		return sb.toString();

	}

	/**
	 * 测试连接oracle
	 *
	 * @param host
	 * @param port
	 * @param dbname
	 * @return
	 */
	public static String buildOracleUrl(String host, String port, String dbname) {
		StringBuilder sb = new StringBuilder("jdbc:oracle:thin:@");
		sb.append(host).append(":").append(port).append(":").append(dbname);
		return sb.toString();
	}

	/**
	 * 测试连接数据库
	 *
	 * @param driver
	 * @param url
	 * @param username
	 * @param passwd
	 * @return
	 */
	public static boolean connectDB(String driver, String url, String username, String passwd) {

		Connection con = null;
		PreparedStatement pst = null;
		try {
			//创建驱动器
			Class.forName(driver);
			//这是数据库的路径，并且还有输入账号（一般默认是root），密码之前创建用户时的那个
			con = DriverManager.getConnection(url, username, passwd);
			//输入的是要在MySQL中执行的代码
			pst = con.prepareCall("select 1+1 from dual");
		} catch (ClassNotFoundException ex) {
			return false;
		} catch (SQLException ex) {
			return false;
		} catch (Exception ex) {
			return false;
		} finally {
			try {
				if (null != pst) {
					pst.close();
				}
			} catch (Exception e) {
				System.out.println("pst关闭异常");
			}
			try {
				if (null != con) {
					con.close();
				}
			} catch (Exception e) {
				System.out.println("con关闭异常");
			}
		}

		return true;
	}
}
