/**
 * Title DatabaseConfig
 * Package com.bhc.bean
 * Copyright 2018 www.hundsun.com All Rights Reserved.
 *
 * @author zhanggd1816@hundsun.com
 * @date 2018-10-28 20:46
 * @version V5.1.2
 */
package com.bhc.bean;

/**
 * 数据库配置类
 * ClassName DatabaseConfig
 *
 * @author zhanggd16816@hundsun.com
 * @date 2018-10-28 20:46
 */
public class DatabaseConfig {

	private String otherName;

	private String databaseName;

	private String host;

	private String port;

	private String databaseType;

	private String userName;

	private String passwd;

	private String flag;

	public String getOtherName() {
		return otherName;
	}

	public void setOtherName(String otherName) {
		this.otherName = otherName;
	}

	public String getDatabaseName() {
		return databaseName;
	}

	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getDatabaseType() {
		return databaseType;
	}

	public void setDatabaseType(String databaseType) {
		this.databaseType = databaseType;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	@Override
	public String toString() {
		return "DatabaseConfig{" +
				"otherName='" + otherName + '\'' +
				", databaseName='" + databaseName + '\'' +
				", host='" + host + '\'' +
				", port='" + port + '\'' +
				", databaseType='" + databaseType + '\'' +
				", userName='" + userName + '\'' +
				", passwd='" + passwd + '\'' +
				", flag='" + flag + '\'' +
				'}';
	}
}
