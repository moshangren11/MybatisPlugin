/**
 * Title BuildMybatisTools
 * Package com.bhc.tools
 * Copyright 2018 www.hundsun.com All Rights Reserved.
 *
 * @author zhanggd1816@hundsun.com
 * @date 2018-05-20 0020 1:24
 * @version V5.0.0
 */
package com.bhc.tools;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * ClassName BuildMybatisTools
 * Description 构建mybatis文件帮助类
 *
 * @author zhanggd16816 zhanggd16816@hundsun.com
 * @date 2018-05-20 0020 1:24
 */
public class BuildMybatisTools {

	public static String buildFile(String driver, String url, String username, String userpasswd,
								   String javaSrc,String zy, String modelLJ,String sqlmapLJ,String cleintLJ, String[] tables) {
		List<String> warnings = new ArrayList<String>();
		boolean overwrite = true;
		String resultMsg = "SUCCESS";
		try {
			//如果这里出现空指针，直接写绝对路径即可。
			InputStream is= BuildMybatisTools.class.getResourceAsStream("configuration.xml");
			SAXReader reader = new SAXReader();
			Document document = reader.read(is);
			Element rootElem = document.getRootElement();
			Element contactElem = rootElem.element("context");

			//jdbc
			Element jdbcElem = contactElem.element("jdbcConnection");
			//driver
			Attribute driverAttr = jdbcElem.attribute("driverClass");
			driverAttr.setValue(driver);
			//url
			Attribute urlAttr = jdbcElem.attribute("connectionURL");
			urlAttr.setValue(url);
			//username
			Attribute usernameAttr = jdbcElem.attribute("userId");
			usernameAttr.setValue(username);
			//密码
			Attribute passwordAttr = jdbcElem.attribute("password");
			passwordAttr.setValue(userpasswd);

			//model
			Element modelElem = contactElem.element("javaModelGenerator");
			//modelPackage
			Attribute modelPackage = modelElem.attribute("targetPackage");
			modelPackage.setValue(modelLJ);
			//modelProject
			Attribute modelProject = modelElem.attribute("targetProject");
			modelProject.setValue(javaSrc);

			//mapper
			Element sqlmapElem = contactElem.element("sqlMapGenerator");
			//modelPackage
			Attribute sqlmapPackage = sqlmapElem.attribute("targetPackage");
			sqlmapPackage.setValue(sqlmapLJ);
			//modelProject
			Attribute sqlmapProject = sqlmapElem.attribute("targetProject");
			sqlmapProject.setValue(zy);

			//model
			Element javaclientElem = contactElem.element("javaClientGenerator");
			//modelPackage
			Attribute javaclientPackage = javaclientElem.attribute("targetPackage");
			javaclientPackage.setValue(cleintLJ);
			//modelProject
			Attribute javaclientProject = javaclientElem.attribute("targetProject");
			javaclientProject.setValue(javaSrc);

			contactElem.remove(contactElem.element("table"));
			for (String table : tables) {
				if(StringUtils.isNotBlank(table)) {
					Element tableElem = contactElem.addElement("table");
					tableElem.addAttribute("schema", username);
					tableElem.addAttribute("tableName", table);
					tableElem.addAttribute("domainObjectName", MybatisTools.lineToHump(table));

					Element propertyElem = tableElem.addElement("property ");
					propertyElem.addAttribute("name","ignoreQualifiersAtRuntime");
					propertyElem.addAttribute("value","true");
					tableElem.add((Element) propertyElem.clone());
					contactElem.add((Element) tableElem.clone());
				}
			}

			ByteArrayOutputStream fos = new ByteArrayOutputStream();
			XMLWriter writer = new XMLWriter(fos);
			writer.write(document);
			writer.flush();
			writer.close();
			ByteArrayInputStream fis = parse(fos);

			ConfigurationParser cp = new ConfigurationParser(warnings);
			Configuration config = null;

			config = cp.parseConfiguration(fis);
			DefaultShellCallback callback = new DefaultShellCallback(overwrite);
			MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
			myBatisGenerator.generate(null);
		} catch (Exception e) {
			resultMsg = e.getMessage();
			e.printStackTrace();
		}
		return resultMsg;
	}

	public static ByteArrayInputStream parse(OutputStream out) throws Exception {
		ByteArrayOutputStream baos = (ByteArrayOutputStream) out;
		ByteArrayInputStream swapStream = new ByteArrayInputStream(baos.toByteArray());
		return swapStream;
	}
}
