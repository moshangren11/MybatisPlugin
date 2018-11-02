package com.bhc.ui;

import com.bhc.setting.DBSetting;
import com.bhc.setting.MainSetting;
import com.bhc.setting.PackageSetting;
import com.bhc.setting.SettingManager;
import com.bhc.tools.BuildMybatisTools;
import com.bhc.tools.DBTools;
import com.bhc.tools.MybatisTools;
import com.intellij.openapi.ui.Messages;
import org.apache.commons.lang.StringUtils;

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
	private JComboBox packageComboBox;
	private JButton delPcButton;

	private SettingManager setting = SettingManager.getInstance();


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
		modelField.setText("");
		sqlmapField.setText("");
		clientField.setText("");
		packageComboBox.removeAllItems();

		packageComboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand().equals("comboBoxChanged")) {
					String pcName = (String) packageComboBox.getSelectedItem();
					PackageSetting packageSetting = setting.getPcSetting(pcName);
					if(null == packageSetting){
						return;
					}
					setPcParam(packageSetting);
				}
			}
		});

		delPcButton.addMouseListener(new MouseAdapter() {
			/**
			 * {@inheritDoc}
			 *
			 * @param e
			 */
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				String pcName = (String) packageComboBox.getSelectedItem();
				if(setting.delPc(pcName)){
					//删除成功刷新页面
					PackageSetting packageSetting = setting.getPcSetting();
					setPcParam(packageSetting);
				}
			}
		});

		PackageSetting ps = setting.getPcSetting();
		if (null != ps) {
			modelField.setText(ps.getModel());
			sqlmapField.setText(ps.getSqlmap());
			clientField.setText(ps.getClient());
			if (null != ps.getNames() && ps.getNames().length > 0) {
				for (String name : ps.getNames()) {
					packageComboBox.addItem(name);
				}
				packageComboBox.setSelectedIndex(0);
			}
		}

	}

	private void onOK() {

		String model = modelField.getText().trim();
		String sqlmap = sqlmapField.getText().trim();
		String client = clientField.getText().trim();
		String name = (String) packageComboBox.getSelectedItem();
		String tablesStr = tableTextArea.getText().trim();

		if (!MybatisTools.checkPackage(model) || !MybatisTools.checkPackage(sqlmap) || !MybatisTools.checkPackage(client)) {
			showMessage("包名不符合规范");
			return;
		}
		if (!MybatisTools.checkTable(tablesStr)) {
			showMessage("表名列表不符合规范");
			return;
		}

		DBSetting dbSetting = setting.getDBSetting();
		if (null == dbSetting) {
			showMessage("数据库设置异常，未保存数据库方案");
			return;
		}
		MainSetting mainSetting = setting.getMainSetting();
		if (null == mainSetting) {
			showMessage("项目目录设置异常，未保存项目目录方案");
			return;
		}

		//表名
		String[] tables = tablesStr.split(",");
		String driver = "";
		String url = "";
		if (dbSetting.getDbType().equals("mysql")) {
			driver = DBTools.MYSQL_DRIVER;
			url = DBTools.buildMysqlUrl(dbSetting.getHost(), dbSetting.getPort(), dbSetting.getDbName()).concat(DBTools.MYSQL_CONFIG);
		} else {
			driver = DBTools.ORACLE_DRIVER;
			url = DBTools.buildOracleUrl(dbSetting.getHost(), dbSetting.getPort(), dbSetting.getDbName());
		}

		String result = BuildMybatisTools.buildFile(driver, url, dbSetting.getUserNmae(), dbSetting.getPasswd(), mainSetting.getJavaPath(), mainSetting.getZyPath(),
				model, sqlmap, client, tables);
		if (result.equals("SUCCESS")) {
			if (StringUtils.isBlank(name)) {
				name = setting.getNewName("pc");
			}
			PackageSetting packageSetting = new PackageSetting();
			packageSetting.setName(name);
			packageSetting.setSqlmap(sqlmap);
			packageSetting.setModel(model);
			packageSetting.setClient(client);
			setting.setPackageSetting(packageSetting);
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

	private void setPcParam(PackageSetting packageSetting){
		if (null == packageSetting) {
			modelField.setText("");
			sqlmapField.setText("");
			clientField.setText("");
			packageComboBox.removeAllItems();
			return;
		}

		if(null != packageSetting.getNames()){
			packageComboBox.removeAllItems();
			for (String name:packageSetting.getNames()) {
				packageComboBox.addItem(name);
			}
			packageComboBox.setSelectedIndex(0);
		}
		modelField.setText(packageSetting.getModel());
		sqlmapField.setText(packageSetting.getSqlmap());
		clientField.setText(packageSetting.getClient());
	}

}
