/**
 * Title PackageSetting
 * Package com.bhc.setting
 * Copyright 2018 www.hundsun.com All Rights Reserved.
 *
 * @author zhanggd1816@hundsun.com
 * @date 2018-10-30 1:48
 * @version V5.1.2
 */
package com.bhc.setting;

import java.util.Arrays;

/**
 * 配置页面参数
 * ClassName PackageSetting
 * @author zhanggd16816@hundsun.com
 * @date 2018-10-30 1:48
 */
public class PackageSetting {

	private String name;
	private String[] names;

	private String model;
	private String client;
	private String sqlmap;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String[] getNames() {
		return names.clone();
	}

	public void setNames(String[] names) {
		this.names = names.clone();
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getSqlmap() {
		return sqlmap;
	}

	public void setSqlmap(String sqlmap) {
		this.sqlmap = sqlmap;
	}

	@Override
	public String toString() {
		return "PackageSetting{" +
				"name='" + name + '\'' +
				", names=" + Arrays.toString(names) +
				", model='" + model + '\'' +
				", client='" + client + '\'' +
				", sqlmap='" + sqlmap + '\'' +
				'}';
	}
}
