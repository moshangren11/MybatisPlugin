/**
 * Title MybatisConfigurable
 * Package com.bhc.config
 * Copyright 2018 www.hundsun.com All Rights Reserved.
 *
 * @author zhanggd1816@hundsun.com
 * @date 2018-05-17 0017 23:13
 * @version V5.0.0
 */
package com.bhc.config;

import com.bhc.setting.MybatisPluginSetting;
import com.bhc.ui.MybatisPlugin;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * ClassName MybatisConfigurable
 * Description 配置类
 *
 * @author zhanggd16816 zhanggd16816@hundsun.com
 * @date 2018-05-17 0017 23:13
 */
public class MybatisConfigurable implements Configurable {

	private MybatisPlugin mybatisPlugin;
	private MybatisPluginSetting setting = MybatisPluginSetting.getInstance();

	@Nullable
	@Override
	public String getHelpTopic() {
		return "Mybatis Plugin";
	}

	/**
	 * 重置
	 */
	@Override
	public void reset() {
		this.mybatisPlugin.hostFaield.setText(this.setting.getDbHost());
		this.mybatisPlugin.dbnameField.setText(this.setting.getDbName());
		this.mybatisPlugin.portField.setText(this.setting.getDbPort());
		this.mybatisPlugin.javaFolder.setText(this.setting.getJavaPath());
		this.mybatisPlugin.zyFolder.setText(this.setting.getResourcesPath());
		this.mybatisPlugin.usernameField.setText(setting.getUsername());
		this.mybatisPlugin.passwdField.setText(setting.getPasswd());
		this.mybatisPlugin.connTest.setText("");
	}

	/**
	 * 是否修改
	 *
	 * @return
	 */
	@Override
	public boolean isModified() {
		return !this.setting.getDbHost().equals(this.mybatisPlugin.hostFaield.getText()) ||
				!this.setting.getDbName().equals(this.mybatisPlugin.dbnameField.getText()) ||
				!this.setting.getDbPort().equals(this.mybatisPlugin.portField.getText()) ||
				!this.setting.getJavaPath().equals(this.mybatisPlugin.javaFolder.getText()) ||
				!this.setting.getResourcesPath().equals(this.mybatisPlugin.zyFolder.getText()) ||
				!this.setting.getUsername().equals(mybatisPlugin.usernameField.getText()) ||
				!setting.getPasswd().equals(mybatisPlugin.passwdField.getText())||
				!setting.getDbType().equals((String)mybatisPlugin.dbComboBox.getSelectedItem());
	}

	@Nls
	@Override
	public String getDisplayName() {
		return "Mybatis Plugin";
	}

	@Nullable
	@Override
	public JComponent createComponent() {
		if (null == this.mybatisPlugin) {
			this.mybatisPlugin = new MybatisPlugin(this.setting.getDbType());
		}
		return this.mybatisPlugin.mainPanel;
	}

	/**
	 * 确认
	 *
	 * @throws ConfigurationException
	 */
	@Override
	public void apply() throws ConfigurationException {
		this.setting.setDbHost(this.mybatisPlugin.hostFaield.getText());
		this.setting.setDbName(this.mybatisPlugin.dbnameField.getText());
		this.setting.setDbPort(this.mybatisPlugin.portField.getText());
		this.setting.setJavaPath(this.mybatisPlugin.javaFolder.getText());
		this.setting.setResourcesPath(this.mybatisPlugin.zyFolder.getText());
		this.setting.setUsername(this.mybatisPlugin.usernameField.getText());
		this.setting.setPasswd(this.mybatisPlugin.passwdField.getText());
		setting.setDbType((String)mybatisPlugin.dbComboBox.getSelectedItem());
	}
}
