/**
 * Title OverIsMergeablePlugin
 * Package com.bhc.plugin
 * Copyright 2018 www.hundsun.com All Rights Reserved.
 *
 * @author zhanggd1816@hundsun.com
 * @date 2018-05-23 0023 13:21
 * @version V5.0.0
 */
package com.bhc.plugin;

import org.mybatis.generator.api.GeneratedXmlFile;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.plugins.SerializablePlugin;

import java.lang.reflect.Field;
import java.util.List;

/**
 * ClassName OverIsMergeablePlugin
 * Description TODO
 * @author zhanggd16816 zhanggd16816@hundsun.com
 * @date 2018-05-23 0023 13:21
 */
public class OverIsMergeablePlugin extends SerializablePlugin {

	@Override
	public boolean validate(List<String> list) {
		return true;
	}

	@Override
	public boolean sqlMapGenerated(GeneratedXmlFile sqlMap, IntrospectedTable introspectedTable) {
		try {
			Field field = sqlMap.getClass().getDeclaredField("isMergeable");
			field.setAccessible(true);
			field.setBoolean(sqlMap, false);
		} catch (Exception e) {
			System.out.println("sqlMapGenerated空指针异常");
		}
		return true;
	}
}
