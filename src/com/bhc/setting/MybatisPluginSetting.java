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

import com.bhc.bean.DatabaseConfig;
import com.bhc.bean.MainConfig;
import com.bhc.bean.PackageConfig;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import org.apache.commons.lang.StringUtils;
import org.jdom.Element;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName MybatisPluginSetting
 * Description 缓存配置类
 *
 * @author zhanggd16816 zhanggd16816@hundsun.com
 * @date 2018-05-18 0018 1:30
 */
@State(
		name = "MybatisPluginSetting",
		storages = {@Storage(value = "$APP_CONFIG$/mybatisPluginSetting.xml")}
)
public class MybatisPluginSetting implements PersistentStateComponent<Element> {

	private List<MainConfig> mcs;

	private List<DatabaseConfig> dcs;

	private List<PackageConfig> pcs;

	private MybatisPluginSetting() {
	}

	public static MybatisPluginSetting getInstance() {
		MybatisPluginSetting setting = ServiceManager.getService(MybatisPluginSetting.class);
		return setting;
	}

	@Override
	public void loadState(@NotNull Element element) {

		if(null == element){
			return;
		}

		//路径设置
		mcs = new ArrayList<>();
		Element mcsElement = null;
		try {
			mcsElement = element.getChild("MainConfigs");
		} catch (Exception e) {
			System.out.println("mcsElement为空");
		}
		if (null != mcsElement) {
			List<Element> mainConfigElements = mcsElement.getChildren("MainConfig");
			if (mainConfigElements.size() > 0) {
				for (Element mainConfigElement : mainConfigElements) {
					MainConfig mc = new MainConfig();
					String name = mainConfigElement.getAttributeValue("name");
					mc.setName(name);
					String javaPath = mainConfigElement.getAttributeValue("javaPatch");
					mc.setJavaPath(javaPath);
					String resourcesPatch = mainConfigElement.getAttributeValue("resourcesPatch");
					mc.setResourcesPath(resourcesPatch);
					mc.setFlag(mainConfigElement.getAttributeValue("flag"));
					mcs.add(mc);
				}
			}
		}

		//数据库配置
		dcs = new ArrayList<>();
		Element dcsElement = null;
		try {
			dcsElement = element.getChild("DatabaseConfigs");
		} catch (Exception e) {
			System.out.println("dcsElement为空");
		}
		if (null != dcsElement) {
			List<Element> databaseConfigElements = dcsElement.getChildren("DatabaseConfig");
			if (databaseConfigElements.size() > 0) {
				for (Element dce : databaseConfigElements) {
					DatabaseConfig dc = new DatabaseConfig();
					dc.setDatabaseName(dce.getAttributeValue("databaseName"));
					dc.setDatabaseType(dce.getAttributeValue("databaseType"));
					dc.setHost(dce.getAttributeValue("host"));
					dc.setOtherName(dce.getAttributeValue("otherName"));
					dc.setPasswd(dce.getAttributeValue("passwd"));
					dc.setPort(dce.getAttributeValue("port"));
					dc.setUserName(dce.getAttributeValue("userName"));
					dc.setFlag(dce.getAttributeValue("flag"));
					dcs.add(dc);
				}
			}
		}

		//包路径配置
		pcs = new ArrayList<>();
		Element pcsElement = null;
		try {
			pcsElement = element.getChild("PackageConfigs");
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (null != pcsElement) {
			List<Element> packageConfigElements = pcsElement.getChildren("PackageConfig");
			if (packageConfigElements.size() > 0) {
				for (Element pce : packageConfigElements) {
					PackageConfig pc = new PackageConfig();
					pc.setName(pce.getAttributeValue("name"));
					pc.setDaoPackage(pce.getAttributeValue("daoPackage"));
					pc.setModelPackage(pce.getAttributeValue("modelPackage"));
					pc.setSqlmapPackage(pce.getAttributeValue("sqlmapPackage"));
					pc.setFlag(pce.getAttributeValue("flag"));
					pcs.add(pc);
				}
			}
		}
	}

	@Nullable
	@Override
	public Element getState() {
		Element element = new Element("MybatisPluginSetting");
		if (null != mcs && mcs.size() > 0) {
			Element mcsElement = new Element("MainConfigs");
			for (MainConfig mc : mcs) {
				//主配置
				Element mainConfigElement = new Element("MainConfig");
				mainConfigElement.setAttribute("name", mc.getName());
				mainConfigElement.setAttribute("javaPatch", mc.getJavaPath());
				mainConfigElement.setAttribute("resourcesPatch", mc.getResourcesPath());
				mainConfigElement.setAttribute("flag", mc.getFlag());
				mcsElement.addContent(mainConfigElement);
			}
			element.addContent(mcsElement);
		}

		//数据库配置
		if (null != dcs && dcs.size() > 0) {
			Element dcsElement = new Element("DatabaseConfigs");
			for (DatabaseConfig md : dcs) {
				Element databaseConfigElement = new Element("DatabaseConfig");
				databaseConfigElement.setAttribute("otherName", md.getDatabaseName());
				databaseConfigElement.setAttribute("databaseName", md.getDatabaseName());
				databaseConfigElement.setAttribute("host", md.getHost());
				databaseConfigElement.setAttribute("port", md.getPort());
				databaseConfigElement.setAttribute("databaseType", md.getDatabaseType());
				databaseConfigElement.setAttribute("userName", md.getUserName());
				databaseConfigElement.setAttribute("passwd", md.getPasswd());
				databaseConfigElement.setAttribute("flag", md.getFlag());
				dcsElement.addContent(databaseConfigElement);
			}
			element.addContent(dcsElement);
		}

		//包路径配置
		if (null != pcs && pcs.size() > 0) {
			Element pcsElement = new Element("PackageConfigs");
			for (PackageConfig mp : pcs) {
				Element packageConfigElement = new Element("PackageConfig");
				packageConfigElement.setAttribute("name", mp.getName());
				packageConfigElement.setAttribute("daoPackage", mp.getDaoPackage());
				packageConfigElement.setAttribute("modelPackage", mp.getModelPackage());
				packageConfigElement.setAttribute("sqlmapPackage", mp.getSqlmapPackage());
				packageConfigElement.setAttribute("flag", mp.getFlag());
				pcsElement.addContent(packageConfigElement);
			}
			element.addContent(pcsElement);
		}

		return element;
	}

	public List<MainConfig> getMcs() {
		if (null == mcs) {
			mcs = new ArrayList<>();
		}
		return mcs;
	}

	public DatabaseConfig getDc(String dcName) {
		if (null == dcs || dcs.size() <= 0) {
			return null;
		}
		if (StringUtils.isBlank(dcName)) {
			return null;
		}
		for (DatabaseConfig dc : dcs) {
			if (dcName.equals(dc.getOtherName())) {
				return dc;
			}
		}
		return null;
	}

	public MainConfig getMc(String name) {
		if (null == mcs || mcs.size() <= 0) {
			return null;
		}
		if (StringUtils.isBlank(name)) {
			return null;
		}
		for (MainConfig mc : mcs) {
			if (name.equals(mc.getName())) {
				return mc;
			}
		}
		return null;
	}

	public PackageConfig getPc(String name) {
		if (null == pcs || pcs.size() <= 0) {
			return null;
		}
		if (StringUtils.isBlank(name)) {
			return null;
		}
		for (PackageConfig pc : pcs) {
			if (name.equals(pc.getName())) {
				return pc;
			}
		}
		return null;
	}

	public String[] getDcsNames(String name) {
		List<String> list = new ArrayList<>();
		for (DatabaseConfig dc : dcs) {
			if (!name.equals(dc.getOtherName())) {
				list.add(dc.getOtherName());
			}
		}
		list.add(0, name);
		String[] names = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			names[i] = list.get(i);
		}
		return names;
	}

	public String[] getPcsNames(String name) {
		List<String> list = new ArrayList<>();
		for (PackageConfig pc : pcs) {
			if (!name.equals(pc.getName())) {
				list.add(pc.getName());
			}
		}
		list.add(0, name);
		String[] names = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			names[i] = list.get(i);
		}
		return names;
	}

	public String[] getMcsNames(String name) {
		List<String> list = new ArrayList<>();
		for (MainConfig mc : mcs) {
			if (!name.equals(mc.getName())) {
				list.add(mc.getName());
			}
		}
		list.add(0, name);
		String[] names = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			names[i] = list.get(i);
		}
		return names;
	}

	public void setMcs(List<MainConfig> mcs) {
		this.mcs = mcs;
	}

	public List<DatabaseConfig> getDcs() {
		if (null == dcs) {
			dcs = new ArrayList<>();
		}
		return dcs;
	}

	public void setDcs(List<DatabaseConfig> dcs) {
		this.dcs = dcs;
	}

	public List<PackageConfig> getPcs() {
		if (null == pcs) {
			pcs = new ArrayList<>();
		}
		return pcs;
	}

	public void setPcs(List<PackageConfig> pcs) {

		this.pcs = pcs;
	}

	@Override
	public String toString() {
		return "MybatisPluginSetting{" +
				"mcs=" + mcs +
				", dcs=" + dcs +
				", pcs=" + pcs +
				'}';
	}
}
