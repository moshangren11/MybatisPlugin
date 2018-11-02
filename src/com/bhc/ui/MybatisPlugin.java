/**
 * Title MybatisPlugin
 * Package com.bhc.ui
 * Copyright 2018 www.hundsun.com All Rights Reserved.
 *
 * @author zhanggd1816@hundsun.com
 * @date 2018-05-17 0017 23:03
 * @version V5.0.0
 */
package com.bhc.ui;

import com.bhc.setting.DBSetting;
import com.bhc.setting.MainSetting;
import com.bhc.setting.SettingManager;
import com.bhc.tools.DBTools;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
import com.intellij.openapi.ui.TextBrowseFolderListener;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import org.apache.commons.lang.StringUtils;

import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.util.HashMap;

/**
 * ClassName MybatisPlugin
 * Description
 * @author zhanggd16816
 * @date 2018-05-17 0017 23:03
 */
public class MybatisPlugin {
	public TextFieldWithBrowseButton javaFolder;
	public JTextField dbnameField;
	public JPanel mainPanel;
	private JLabel javaLabel;
	private JPanel javaPanel;
	private JPanel zyPanel;
	private JLabel zyLabel;
	public TextFieldWithBrowseButton zyFolder;
	private JLabel dataLabel;
	private JPanel dataPanel;
	public JTextField portField;
	private JLabel hostName;
	private JLabel portName;
	public JTextField hostFaield;
	public JTextField usernameField;
	public JTextField passwdField;
	public JButton connButton;
	public JLabel connTest;
	public JComboBox dbComboBox;
	public JComboBox configNameComboBox;
	public JComboBox databaseComboBox;
	private JButton delMCButton;
	private JButton delDCButton;
	private static final String[] DBTYPE = {"oracle","mysql"};
	private SettingManager setting = SettingManager.getInstance();

	public MybatisPlugin(){
		connTest.setText("");
		FileChooserDescriptor descriptor = FileChooserDescriptorFactory.createSingleFolderDescriptor();
		javaFolder.addBrowseFolderListener(new TextBrowseFolderListener(descriptor) {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				String current = javaFolder.getText();
				if (!current.isEmpty()) {
					fc.setCurrentDirectory(new File(current));
				}
				fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				fc.showOpenDialog(mainPanel);

				File file = fc.getSelectedFile();
				String path = file == null
						? ""
						: file.getAbsolutePath();
				javaFolder.setText(path);
			}
		});

		zyFolder.addBrowseFolderListener(new TextBrowseFolderListener(descriptor) {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				String current = zyFolder.getText();
				if (!current.isEmpty()) {
					fc.setCurrentDirectory(new File(current));
				}
				fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				fc.showOpenDialog(mainPanel);

				File file = fc.getSelectedFile();
				String path = file == null
						? ""
						: file.getAbsolutePath();
				zyFolder.setText(path);
			}
		});

		connButton.addMouseListener(new MouseAdapter() {
			/**
			 * {@inheritDoc}
			 *
			 * @param e
			 */
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				connTest.setText("");
				boolean tBool = false;
				if(dbComboBox.getSelectedItem().equals(DBTYPE[1])){
					//mysql

					tBool = DBTools.connectDB(DBTools.MYSQL_DRIVER,
							DBTools.buildMysqlUrl(hostFaield.getText(), portField.getText(), dbnameField.getText()),
							usernameField.getText(),
							passwdField.getText());
				}else if(dbComboBox.getSelectedItem().equals(DBTYPE[0])){
					//oracle
					tBool = DBTools.connectDB(DBTools.ORACLE_DRIVER,
							DBTools.buildOracleUrl(hostFaield.getText(), portField.getText(), dbnameField.getText()),
							usernameField.getText(),
							passwdField.getText());
				}
				if(tBool) {
					connTest.setText("数据库连接成功");
				}else{
					connTest.setText("数据库连接失败");
				}
			}
		});


		configNameComboBox.addActionListener(new ActionListener(){
			/**
			 * Invoked when an action occurs.
			 *
			 * @param e
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand().equals("comboBoxChanged")){
					String name = (String) configNameComboBox.getSelectedItem();
					MainSetting mainSetting = setting.getMainSetting(name);
					if(null == mainSetting){
						return;
					}
					setMainParam(mainSetting);
				}
			}
		});

		databaseComboBox.addActionListener(new ActionListener(){

			/**
			 * Invoked when an action occurs.
			 *
			 * @param e
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand().equals("comboBoxChanged")){
					String dcName = (String) databaseComboBox.getSelectedItem();
					DBSetting dbSetting = setting.getDBSetting(dcName);
					if(null == dbSetting){
						return;
					}
					setDBParam(dbSetting);
				}
			}
		});

		delDCButton.addMouseListener(new MouseAdapter() {
			/**
			 * {@inheritDoc}
			 *
			 * @param e
			 */
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				String dcName = (String) databaseComboBox.getSelectedItem();
				if(setting.delDc(dcName)){
					//删除成功刷新页面
					DBSetting dbSetting = setting.getDBSetting();
					setDBParam(dbSetting);
				}
			}
		});

		delMCButton.addMouseListener(new MouseAdapter() {
			/**
			 * {@inheritDoc}
			 *
			 * @param e
			 */
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				String mcName = (String) configNameComboBox.getSelectedItem();
				if(setting.delMc(mcName)){
					//删除成功刷新页面
					MainSetting mainSetting = setting.getMainSetting();
					setMainParam(mainSetting);
				}
			}
		});
	}

	public void setMainAndDB(MainSetting mainSetting,DBSetting dbSetting){
		setMainParam(mainSetting);
		setDBParam(dbSetting);
	}

	private void setDBParam(DBSetting dbSetting){
		if (null == dbSetting) {
			this.portField.setText("");
			this.hostFaield.setText("");
			this.dbnameField.setText("");
			this.usernameField.setText("");
			this.passwdField.setText("");
			databaseComboBox.removeAllItems();
			setDbtype("");
			return;
		}
		if(null != dbSetting.getOtherNames()){
			databaseComboBox.removeAllItems();
			for (String name:dbSetting.getOtherNames()) {
				databaseComboBox.addItem(name);
			}
			databaseComboBox.setSelectedIndex(0);
		}
		this.hostFaield.setText(dbSetting.getHost());
		this.dbnameField.setText(dbSetting.getDbName());
		this.portField.setText(dbSetting.getPort());
		this.usernameField.setText(dbSetting.getUserNmae());
		this.passwdField.setText(dbSetting.getPasswd());
		setDbtype(dbSetting.getDbType());
	}

	private void setMainParam(MainSetting mainSetting) {
		if (null == mainSetting) {
			this.hostFaield.setText("");
			this.dbnameField.setText("");
			this.portField.setText("");
			this.usernameField.setText("");
			this.passwdField.setText("");
			this.javaFolder.setText("");
			this.zyFolder.setText("");
			configNameComboBox.removeAllItems();
			setDbtype("");
			return;
		}

		this.javaFolder.setText(mainSetting.getJavaPath());
		this.zyFolder.setText(mainSetting.getZyPath());
		if (null != mainSetting.getNames()) {
			configNameComboBox.removeAllItems();
			for (String name : mainSetting.getNames()) {
				configNameComboBox.addItem(name);
			}
			configNameComboBox.setSelectedIndex(0);
		}
	}

	/**
	 * 设置数据库类型下拉按钮
	 * @param type
	 */
	public void setDbtype(String type){
		dbComboBox.removeAllItems();
		if(StringUtils.isNotBlank(type)){
			dbComboBox.addItem(type);
		}
		for (String s:DBTYPE){
			if(!s.equals(type)){
				dbComboBox.addItem(s);
			}
		}
		dbComboBox.setSelectedIndex(0);
	}
}
