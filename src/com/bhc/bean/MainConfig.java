/**
 * Title MainConfig
 * Package com.bhc.bean
 * Copyright 2018 www.hundsun.com All Rights Reserved.
 *
 * @author zhanggd1816@hundsun.com
 * @date 2018-10-28 19:09
 * @version V5.1.2
 */
package com.bhc.bean;

/**
 * 设置页面参数类
 * ClassName MainConfig
 *
 * @author zhanggd16816@hundsun.com
 * @date 2018-10-28 19:09
 */
public class MainConfig {

	private String name;

	private String javaPath;

	private String resourcesPath;

	private String flag;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJavaPath() {
		return javaPath;
	}

	public void setJavaPath(String javaPath) {
		this.javaPath = javaPath;
	}

	public String getResourcesPath() {
		return resourcesPath;
	}

	public void setResourcesPath(String resourcesPath) {
		this.resourcesPath = resourcesPath;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	@Override
	public String toString() {
		return "MainConfig{" +
				"name='" + name + '\'' +
				", javaPath='" + javaPath + '\'' +
				", resourcesPath='" + resourcesPath + '\'' +
				", flag='" + flag + '\'' +
				'}';
	}
}
