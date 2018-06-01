package com.bhc.ui;

import com.bhc.setting.MybatisPluginSetting;
import com.bhc.tools.BuildMybatisTools;
import com.bhc.tools.DBTools;
import com.bhc.tools.MybatisTools;
import com.intellij.openapi.ui.Messages;

import javax.swing.*;
import java.awt.event.*;

public class BuildFile extends JDialog {
	private JPanel contentPane;
	private JButton buttonOK;
	private JButton buttonCancel;
	private JTextField sqlmapField;
	private JLabel sqlmapLabel;
	private JLabel tableLabel;
	private JTextArea tableTextArea;
	private JTextField modelField;
	private JTextField clientField;
	private JLabel clientLabel;
	private JLabel modelLabel;
	private JScrollPane jScrollPane;
	private MybatisPluginSetting setting = MybatisPluginSetting.getInstance();


	public BuildFile() {
		setContentPane(contentPane);
		setModal(true);
		getRootPane().setDefaultButton(buttonOK);

		buttonOK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				onOK();
			}
		});

		buttonCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				onCancel();
			}
		});

		// call onCancel() when cross is clicked
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				onCancel();
			}
		});

		// call onCancel() on ESCAPE
		contentPane.registerKeyboardAction(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				onCancel();
			}
		}, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

		this.setTitle("Build Mybatis File");
		this.setSize(500, 400);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.tableTextArea.setLineWrap(true);
		this.tableTextArea.setWrapStyleWord(true);
		this.jScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		modelField.setText(setting.getModel());
		sqlmapField.setText(setting.getSqlmap());
		clientField.setText(setting.getClient());
	}

	private void onOK() {

		String model = modelField.getText().trim();
		String sqlmap = sqlmapField.getText().trim();
		String client = clientField.getText().trim();
		String tablesStr = tableTextArea.getText().trim();

		if(!MybatisTools.checkPackage(model) || !MybatisTools.checkPackage(sqlmap) || !MybatisTools.checkPackage(client)){
			showMessage("包名不符合规范");
			return;
		}
		if(!MybatisTools.checkTable(tablesStr)){
			showMessage("表名列表不符合规范");
			return;
		}

		//表名
		String[] tables = tablesStr.split(",");
		String driver = "";
		String url = "";
		if (setting.getDbType().equals("mysql")) {
			driver = DBTools.MYSQL_DRIVER;
			url = DBTools.buildMysqlUrl(setting.getDbHost(), setting.getDbPort(), setting.getDbName()).concat(DBTools.MYSQL_CONFIG);
		} else {
			driver = DBTools.ORACLE_DRIVER;
			url = DBTools.buildOracleUrl(setting.getDbHost(), setting.getDbPort(), setting.getDbName());
		}

		String result = BuildMybatisTools.buildFile(driver, url, setting.getUsername(), setting.getPasswd(), setting.getJavaPath(), setting.getResourcesPath(),
				model, sqlmap, client, tables);
		if (result.equals("SUCCESS")) {
			setting.setModel(model);
			setting.setClient(client);
			setting.setSqlmap(sqlmap);
			showMessage("创建成功");
		} else {
			showMessage("创建失败[" + result + "]");
		}

	}

	private void showMessage(String msg) {
		Messages.showMessageDialog(msg,
				"Build Result",
				Messages.getInformationIcon());
	}

	private void onCancel() {
		// add your code here if necessary
		dispose();
	}

}
