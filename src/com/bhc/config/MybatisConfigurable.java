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

import com.bhc.setting.DBSetting;
import com.bhc.setting.MainSetting;
import com.bhc.setting.SettingManager;
import com.bhc.ui.MybatisPlugin;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import org.apache.commons.lang.StringUtils;
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

	private SettingManager setting = SettingManager.getInstance();

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
		mybatisPlugin.connTest.setText("");
		MainSetting mainSetting = setting.getMainSetting();
		DBSetting dbSetting = setting.getDBSetting();
		mybatisPlugin.setMainAndDB(mainSetting, dbSetting);
	}

	private boolean isModifiedByMc() {
		MainSetting mainSetting = setting.getMainSetting();
		boolean flag = false;
		if (null != mainSetting) {
			flag = !mainSetting.getName().equals(mybatisPlugin.configNameComboBox.getSelectedItem()) ||
					!mainSetting.getJavaPath().equals(this.mybatisPlugin.javaFolder.getText()) ||
					!mainSetting.getZyPath().equals(this.mybatisPlugin.zyFolder.getText());
		} else {
			flag = null != mybatisPlugin.configNameComboBox.getSelectedItem() ||
					StringUtils.isNotBlank(mybatisPlugin.zyFolder.getText()) ||
					StringUtils.isNotBlank(mybatisPlugin.javaFolder.getText());
		}
		return flag;
	}

	private boolean isModifiedByDc() {
		DBSetting dbSetting = setting.getDBSetting();
		boolean isDb = false;
		if (null != dbSetting) {
			isDb = !dbSetting.getOtherName().equals(mybatisPlugin.databaseComboBox.getSelectedItem()) ||
					!dbSetting.getDbName().equals(mybatisPlugin.dbnameField.getText()) ||
					!dbSetting.getDbType().equals(mybatisPlugin.dbComboBox.getSelectedItem()) ||
					!dbSetting.getHost().equals(mybatisPlugin.hostFaield.getText()) ||
					!dbSetting.getPort().equals(mybatisPlugin.portField.getText()) ||
					!dbSetting.getUserNmae().equals(mybatisPlugin.usernameField.getText()) ||
					!dbSetting.getPasswd().equals(mybatisPlugin.passwdField.getText());

		} else {
			isDb = null != mybatisPlugin.databaseComboBox.getSelectedItem() ||
					StringUtils.isNotBlank(mybatisPlugin.dbnameField.getText()) ||
					StringUtils.isNotBlank(mybatisPlugin.hostFaield.getText()) ||
					StringUtils.isNotBlank(mybatisPlugin.portField.getText()) ||
					StringUtils.isNotBlank(mybatisPlugin.usernameField.getText()) ||
					StringUtils.isNotBlank(mybatisPlugin.passwdField.getText());
		}
		return isDb;
	}

	/**
	 * 是否修改
	 *
	 * @return
	 */
	@Override
	public boolean isModified() {
		return isModifiedByMc() || isModifiedByDc();
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
			this.mybatisPlugin = new MybatisPlugin();
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
		String javaPath = this.mybatisPlugin.javaFolder.getText();
		String zyPatch = this.mybatisPlugin.zyFolder.getText();
		String name = (String) mybatisPlugin.configNameComboBox.getSelectedItem();

		String host = this.mybatisPlugin.hostFaield.getText();
		String dbName = this.mybatisPlugin.dbnameField.getText();
		String port = this.mybatisPlugin.portField.getText();
		String userNmae = this.mybatisPlugin.usernameField.getText();
		String passwd = this.mybatisPlugin.passwdField.getText();
		String otherName = (String) this.mybatisPlugin.databaseComboBox.getSelectedItem();
		String dbType = (String) this.mybatisPlugin.dbComboBox.getSelectedItem();

		MainSetting mainSetting = null;
		if (isModifiedByMc()) {
			mainSetting = new MainSetting();
			if (StringUtils.isBlank(name)) {
				name = setting.getNewName("mc");
			}
			mainSetting.setName(name);
			mainSetting.setZyPath(zyPatch);
			mainSetting.setJavaPath(javaPath);
		}

		DBSetting dbSetting = null;
		if (isModifiedByDc()) {
			dbSetting = new DBSetting();
			if (StringUtils.isBlank(otherName)) {
				otherName = setting.getNewName("dc");
			}
			dbSetting.setOtherName(otherName);
			dbSetting.setDbName(dbName);
			dbSetting.setDbType(dbType);
			dbSetting.setHost(host);
			dbSetting.setPort(port);
			dbSetting.setUserNmae(userNmae);
			dbSetting.setPasswd(passwd);
		}
		setting.setMainSetting(mainSetting, dbSetting);

		//重构页面
		MainSetting nowMainSetting = setting.getMainSetting(name);
		DBSetting nowDBSetting = setting.getDBSetting(otherName);
		mybatisPlugin.setMainAndDB(nowMainSetting, nowDBSetting);
	}

}
