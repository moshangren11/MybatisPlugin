/**
 * Title PackageConfig
 * Package com.bhc.bean
 * Copyright 2018 www.hundsun.com All Rights Reserved.
 *
 * @author zhanggd1816@hundsun.com
 * @date 2018-10-28 20:47
 * @version V5.1.2
 */
package com.bhc.bean;

/**
 * 包路径配置类
 * ClassName PackageConfig
 *
 * @author zhanggd16816@hundsun.com
 * @date 2018-10-28 20:47
 */
public class PackageConfig {

	private String name;

	private String modelPackage;

	private String daoPackage;

	private String sqlmapPackage;

	private String flag;

	public String getModelPackage() {
		return modelPackage;
	}

	public void setModelPackage(String modelPackage) {
		this.modelPackage = modelPackage;
	}

	public String getDaoPackage() {
		return daoPackage;
	}

	public void setDaoPackage(String daoPackage) {
		this.daoPackage = daoPackage;
	}

	public String getSqlmapPackage() {
		return sqlmapPackage;
	}

	public void setSqlmapPackage(String sqlmapPackage) {
		this.sqlmapPackage = sqlmapPackage;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "PackageConfig{" +
				"name='" + name + '\'' +
				", modelPackage='" + modelPackage + '\'' +
				", daoPackage='" + daoPackage + '\'' +
				", sqlmapPackage='" + sqlmapPackage + '\'' +
				", flag='" + flag + '\'' +
				'}';
	}
}
