/**
 * Title MybatisPluginSetting
 * Package com.bhc.setting
 * Copyright 2018 www.hundsun.com All Rights Reserved.
 *
 * @author zhanggd1816@hundsun.com
 * @date 2018-05-18 0018 1:30
 * @version V5.0.0
 */
package com.bhc.setting;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import org.jdom.Element;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * ClassName MybatisPluginSetting
 * Description TODO
 * @author zhanggd16816 zhanggd16816@hundsun.com
 * @date 2018-05-18 0018 1:30
 */
@State(
		name = "MybatisPluginSetting",
		storages = {@Storage(value = "$APP_CONFIG$/mybatisPluginSetting.xml")}
)
public class MybatisPluginSetting implements PersistentStateComponent<Element> {

	private String javaPath;
	private String resourcesPath;
	private String dbName;
	private String dbHost;
	private String dbPort;
	private String username;
	private String passwd;
	private String dbType;
	private String model;
	private String sqlmap;
	private String client;

	private MybatisPluginSetting() {
	}

	public static MybatisPluginSetting getInstance() {
		MybatisPluginSetting setting = ServiceManager.getService(MybatisPluginSetting.class);
		return setting;
	}

	@Override
	public void loadState(@NotNull Element element) {
		this.setDbHost(element.getAttributeValue("dbHost"));
		this.setDbName(element.getAttributeValue("dbName"));
		this.setDbPort(element.getAttributeValue("dbPort"));
		this.setJavaPath(element.getAttributeValue("javaPath"));
		this.setResourcesPath(element.getAttributeValue("resourcesPath"));
		this.setUsername(element.getAttributeValue("username"));
		this.setPasswd(element.getAttributeValue("password"));
		this.setDbType(element.getAttributeValue("dbType"));
		this.setModel(element.getAttributeValue("model"));
		this.setSqlmap(element.getAttributeValue("sqlmap"));
		this.setClient(element.getAttributeValue("client"));
	}

	@Nullable
	@Override
	public Element getState() {
		Element element = new Element("MybatisPluginSetting");
		element.setAttribute("javaPath", this.getJavaPath());
		element.setAttribute("resourcesPath", this.getResourcesPath());
		element.setAttribute("dbName", this.getDbName());
		element.setAttribute("dbHost", this.getDbHost());
		element.setAttribute("dbPort", this.getDbPort());
		element.setAttribute("username",this.getUsername());
		element.setAttribute("password",this.getPasswd());
		element.setAttribute("dbType",this.getDbType());
		element.setAttribute("model",this.getModel());
		element.setAttribute("sqlmap",this.getSqlmap());
		element.setAttribute("client",this.getClient());
		return element;
	}

	public String getJavaPath() {
		return javaPath == null ? "":javaPath;
	}

	public void setJavaPath(String javaPath) {
		this.javaPath = javaPath;
	}

	public String getResourcesPath() {
		return resourcesPath == null ? "":resourcesPath;
	}

	public void setResourcesPath(String resourcesPath) {
		this.resourcesPath = resourcesPath;
	}

	public String getDbName() {
		return dbName == null ? "":dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public String getDbHost() {
		return dbHost == null ? "":dbHost;
	}

	public void setDbHost(String dbHost) {
		this.dbHost = dbHost;
	}

	public String getDbPort() {
		return dbPort == null ? "":dbPort;
	}

	public void setDbPort(String dbPort) {
		this.dbPort = dbPort;
	}

	public String getUsername() {
		return username == null ? "":username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswd() {
		return passwd == null ? "":passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getDbType() {
		return dbType == null ? "oracle":dbType;
	}

	public void setDbType(String dbType) {
		this.dbType = dbType;
	}

	public String getModel() {
		return model == null ? "":model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getSqlmap() {
		return sqlmap == null ? "":sqlmap;
	}

	public void setSqlmap(String sqlmap) {
		this.sqlmap = sqlmap;
	}

	public String getClient() {
		return client == null ? "":client;
	}

	public void setClient(String client) {
		this.client = client;
	}
}
