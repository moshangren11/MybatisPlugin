/**
 * Title MainSetting
 * Package com.bhc.setting
 * Copyright 2018 www.hundsun.com All Rights Reserved.
 *
 * @author zhanggd1816@hundsun.com
 * @date 2018-10-30 1:47
 * @version V5.1.2
 */
package com.bhc.setting;

import java.util.Arrays;

/**
 * 配置主页面参数
 * ClassName MainSetting
 * @author zhanggd16816@hundsun.com
 * @date 2018-10-30 1:47
 */
public class MainSetting {

	private String name;
	private String[] names;
	private String javaPath;
	private String zyPath;

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

	public String getJavaPath() {
		return javaPath;
	}

	public void setJavaPath(String javaPath) {
		this.javaPath = javaPath;
	}

	public String getZyPath() {
		return zyPath;
	}

	public void setZyPath(String zyPath) {
		this.zyPath = zyPath;
	}

	@Override
	public String toString() {
		return "MainSetting{" +
				"name='" + name + '\'' +
				", names=" + Arrays.toString(names) +
				", javaPath='" + javaPath + '\'' +
				", zyPath='" + zyPath + '\'' +
				'}';
	}
}
