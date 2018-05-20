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

import com.bhc.tools.DBTools;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
import com.intellij.openapi.ui.TextBrowseFolderListener;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;

import javax.swing.*;
import java.awt.event.*;
import java.io.File;

/**
 * ClassName MybatisPlugin
 * Description TODO
 * @author zhanggd16816 zhanggd16816@hundsun.com
 * @date 2018-05-17 0017 23:03
 */
public class MybatisPlugin {
	public TextFieldWithBrowseButton javaFolder;
	public JTextField dbnameField;
	public JPanel mainPanel;
	public JLabel javaLabel;
	public JPanel javaPanel;
	public JPanel zyPanel;
	public JLabel zyLabel;
	public TextFieldWithBrowseButton zyFolder;
	public JLabel dataLabel;
	public JPanel dataPanel;
	public JTextField portField;
	public JLabel hostName;
	public JLabel portName;
	public JTextField hostFaield;
	public JTextField usernameField;
	public JTextField passwdField;
	public JButton connButton;
	public JLabel connTest;
	public JComboBox dbComboBox;
	private static final String[] cb = {"mysql","oracle"};

	public MybatisPlugin(String dbname){
		connTest.setText("");
		if(dbname.equals(cb[0])){
			dbComboBox.addItem("mysql");
			dbComboBox.addItem("oracle");
		}else {
			dbComboBox.addItem("oracle");
			dbComboBox.addItem("mysql");
		}
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
				if(dbComboBox.getSelectedItem().equals(cb[0])){
					//mysql

					tBool = DBTools.connectDB(DBTools.MYSQL_DRIVER,
							DBTools.buildMysqlUrl(hostFaield.getText(), portField.getText(), dbnameField.getText()),
							usernameField.getText(),
							passwdField.getText());
				}else if(dbComboBox.getSelectedItem().equals(cb[1])){
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
	}
}
