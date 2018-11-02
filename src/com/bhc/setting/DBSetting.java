/**
 * Title DBSetting
 * Package com.bhc.setting
 * Copyright 2018 www.hundsun.com All Rights Reserved.
 *
 * @author zhanggd1816@hundsun.com
 * @date 2018-10-30 2:18
 * @version V5.1.2
 */
package com.bhc.setting;

import java.util.Arrays;

/**
 * 数据库配置
 * ClassName DBSetting
 * @author zhanggd16816@hundsun.com
 * @date 2018-10-30 2:18
 */
public class DBSetting {

	private String otherName;
	private String[] otherNames;
	private String dbName;
	private String host;
	private String port;
	private String dbType;
	private String userNmae;
	private String passwd;

	public String getOtherName() {
		return otherName;
	}

	public void setOtherName(String otherName) {
		this.otherName = otherName;
	}

	public String[] getOtherNames() {
		return otherNames.clone();
	}

	public void setOtherNames(String[] otherNames) {
		this.otherNames = otherNames.clone();
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
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

	public String getDbType() {
		return dbType;
	}

	public void setDbType(String dbType) {
		this.dbType = dbType;
	}

	public String getUserNmae() {
		return userNmae;
	}

	public void setUserNmae(String userNmae) {
		this.userNmae = userNmae;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	@Override
	public String toString() {
		return "DBSetting{" +
				"otherName='" + otherName + '\'' +
				", otherNames=" + Arrays.toString(otherNames) +
				", dbName='" + dbName + '\'' +
				", host='" + host + '\'' +
				", port='" + port + '\'' +
				", dbType='" + dbType + '\'' +
				", userNmae='" + userNmae + '\'' +
				", passwd='" + passwd + '\'' +
				'}';
	}
}
