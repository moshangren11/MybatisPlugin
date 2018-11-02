/**
 * Title SettingManager
 * Package com.bhc.setting
 * Copyright 2018 www.hundsun.com All Rights Reserved.
 *
 * @author zhanggd1816@hundsun.com
 * @date 2018-10-30 1:54
 * @version V5.1.2
 */
package com.bhc.setting;

import com.bhc.bean.DatabaseConfig;
import com.bhc.bean.MainConfig;
import com.bhc.bean.PackageConfig;
import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 * 配置管理项
 * ClassName SettingManager
 *
 * @author zhanggd16816@hundsun.com
 * @date 2018-10-30 1:54
 */
public class SettingManager {

	private MybatisPluginSetting mps = MybatisPluginSetting.getInstance();

	private List<MainConfig> mcs = mps.getMcs();

	private List<DatabaseConfig> dcs = mps.getDcs();

	private List<PackageConfig> pcs = mps.getPcs();

	private static SettingManager settingManager = null;

	private SettingManager() {

	}

	public static SettingManager getInstance() {
		if (null == settingManager) {
			synchronized (SettingManager.class) {
				if (null == settingManager) {
					settingManager = new SettingManager();
				}
			}
		}
		return settingManager;
	}

	/**
	 * 新增包路径配置项
	 *
	 * @param mainSetting
	 */
	private void addMc(MainSetting mainSetting) {
		MainConfig mainConfig = new MainConfig();
		mainConfig.setName(mainSetting.getName());
		mainConfig.setJavaPath(mainSetting.getJavaPath());
		mainConfig.setResourcesPath(mainSetting.getZyPath());
		mainConfig.setFlag("1");
		for (MainConfig mc : mcs) {
			mc.setFlag("0");
		}
		mcs.add(mainConfig);
	}

	/**
	 * 新增包路径配置
	 *
	 * @param packageSetting
	 */
	private void addPc(PackageSetting packageSetting) {
		PackageConfig packageConfig = new PackageConfig();
		packageConfig.setName(packageSetting.getName());
		packageConfig.setSqlmapPackage(packageSetting.getSqlmap());
		packageConfig.setModelPackage(packageSetting.getModel());
		packageConfig.setDaoPackage(packageSetting.getClient());
		packageConfig.setFlag("1");
		for (PackageConfig pc : pcs) {
			pc.setFlag("0");
		}
		pcs.add(packageConfig);
	}

	/**
	 * 新增数据库配置项
	 *
	 * @param dbSetting
	 */
	private void addDc(DBSetting dbSetting) {
		DatabaseConfig dc = new DatabaseConfig();
		dc.setOtherName(dbSetting.getOtherName());
		dc.setFlag("1");
		for (DatabaseConfig data : dcs) {
			data.setFlag("0");
		}
		dc.setDatabaseType(dbSetting.getDbType());
		dc.setHost(dbSetting.getHost());
		dc.setPasswd(dbSetting.getPasswd());
		dc.setPort(dbSetting.getPort());
		dc.setUserName(dbSetting.getUserNmae());
		dc.setDatabaseName(dbSetting.getDbName());

		dcs.add(dc);
	}

	/**
	 * 根据名称获取主页配置
	 *
	 * @param name
	 * @return
	 */
	public MainSetting getMainSetting(String name) {
		if (null == mcs) {
			return null;
		}
		MainConfig mainConfig = mps.getMc(name);
		if (null == mainConfig) {
			return null;
		}
		MainSetting mainSetting = new MainSetting();
		mainSetting.setNames(mps.getMcsNames(name));
		mainSetting.setName(mainConfig.getName());
		mainSetting.setJavaPath(mainConfig.getJavaPath());
		mainSetting.setZyPath(mainConfig.getResourcesPath());
		return mainSetting;
	}

	/**
	 * 根据数据库配置名称获取数据库配置
	 *
	 * @param name
	 * @return
	 */
	public DBSetting getDBSetting(String name) {
		if (null == dcs) {
			return null;
		}
		DatabaseConfig dc = mps.getDc(name);
		if (null == dc) {
			return null;
		}
		DBSetting dbSetting = new DBSetting();
		dbSetting.setOtherNames(mps.getDcsNames(name));
		dbSetting.setOtherName(dc.getOtherName());
		dbSetting.setDbName(dc.getDatabaseName());
		dbSetting.setDbType(dc.getDatabaseType());
		dbSetting.setHost(dc.getHost());
		dbSetting.setPort(dc.getPort());
		dbSetting.setUserNmae(dc.getUserName());
		dbSetting.setPasswd(dc.getPasswd());
		return dbSetting;

	}

	/**
	 * 获取PcSettring
	 *
	 * @param name
	 * @return
	 */
	public PackageSetting getPcSetting(String name) {
		if (null == pcs) {
			return null;
		}
		PackageConfig pc = mps.getPc(name);
		if (null == pc) {
			return null;
		}
		PackageSetting packageSetting = new PackageSetting();
		packageSetting.setName(pc.getName());
		packageSetting.setClient(pc.getDaoPackage());
		packageSetting.setModel(pc.getModelPackage());
		packageSetting.setSqlmap(pc.getSqlmapPackage());
		packageSetting.setNames(mps.getPcsNames(name));

		return packageSetting;

	}

	/**
	 * 获取参数大小
	 *
	 * @return
	 */
	public int getMcSize() {
		if (null != mcs) {
			return mcs.size();
		} else {
			return 0;
		}
	}

	/**
	 * 获取db的数量
	 *
	 * @return
	 */
	public int getDcSize() {
		if (null != dcs) {
			return dcs.size();
		} else {
			return 0;
		}
	}

	/**
	 * 获取包配置数量
	 *
	 * @return
	 */
	public int getPCSize() {
		if (null != pcs) {
			return pcs.size();
		} else {
			return 0;
		}
	}

	/**
	 * 获取默认主页配置
	 *
	 * @return
	 */
	public MainSetting getMainSetting() {
		if (null == mcs || mcs.size() <= 0) {
			return null;
		}
		for (MainConfig mc : mcs) {
			if ("1".equals(mc.getFlag())) {
				return getMainSetting(mc.getName());
			}
		}
		return null;
	}

	/**
	 * 获取数据库配置
	 *
	 * @return
	 */
	public DBSetting getDBSetting() {
		if (null == dcs || dcs.size() <= 0) {
			return null;
		}

		for (DatabaseConfig dc : dcs) {
			if ("1".equals(dc.getFlag())) {
				return getDBSetting(dc.getOtherName());
			}
		}

		return null;
	}

	public PackageSetting getPcSetting() {
		if (null == pcs || pcs.size() <= 0) {
			return null;
		}

		for (PackageConfig pc : pcs) {
			if ("1".equals(pc.getFlag())) {
				return getPcSetting(pc.getName());
			}
		}

		return null;
	}

	/**
	 * 设置主页配置
	 *
	 * @param mainSetting
	 */
	public void setMainSetting(MainSetting mainSetting, DBSetting dbSetting) {
		if (null != mainSetting) {
			String mcName = mainSetting.getName();
			MainConfig mainConfig = mps.getMc(mcName);
			if (null != mainConfig) {
				mcs.remove(mainConfig);
			}
			addMc(mainSetting);
		}

		if (null != dbSetting) {
			String dbName = dbSetting.getOtherName();
			DatabaseConfig databaseConfig = mps.getDc(dbName);
			if (null != databaseConfig) {
				dcs.remove(databaseConfig);
			}
			addDc(dbSetting);
		}
	}

	/**
	 * 设置包路径页面配置
	 *
	 * @param packageSetting
	 */
	public void setPackageSetting(PackageSetting packageSetting) {
		if (null != packageSetting) {
			String name = packageSetting.getName();
			PackageConfig packageConfig = mps.getPc(name);
			if (null != packageConfig) {
				pcs.remove(packageConfig);
			}
			addPc(packageSetting);
		}
	}

	/**
	 * 新建名称
	 *
	 * @param type
	 * @return
	 */
	public String getNewName(String type) {
		String name = "新建配置项";
		int i = 1;
		if ("mc".equals(type)) {
			do {
				name = "新建配置项" + String.valueOf(getDcSize() + i++);
			} while (null != mps.getMc(name));
		} else if ("dc".equals(type)) {
			do {
				name = "新建配置项" + String.valueOf(getDcSize() + i++);
			} while (null != mps.getMc(name));
		} else if ("pc".equals(type)) {
			do {
				name = "新建配置项" + String.valueOf(getPCSize() + i++);
			} while (null != mps.getMc(name));
		}
		return name;
	}

	public boolean delMc(String name) {
		if (StringUtils.isBlank(name)) {
			return false;
		}
		MainConfig mainConfig = mps.getMc(name);
		if (null == mainConfig) {
			return false;
		}
		mcs.remove(mainConfig);
		if (mcs.size() > 0) {
			mcs.get(0).setFlag("1");
		}
		return true;
	}

	public boolean delPc(String name) {
		if (StringUtils.isBlank(name)) {
			return false;
		}
		PackageConfig packageConfig = mps.getPc(name);
		if (null == packageConfig) {
			return false;
		}
		pcs.remove(packageConfig);
		if (pcs.size() > 0) {
			pcs.get(0).setFlag("1");
		}
		return true;
	}

	public boolean delDc(String name) {
		if (StringUtils.isBlank(name)) {
			return false;
		}
		DatabaseConfig databaseConfig = mps.getDc(name);
		if (null == databaseConfig) {
			return false;
		}
		dcs.remove(databaseConfig);
		if (dcs.size() > 0) {
			dcs.get(0).setFlag("1");
		}
		return true;
	}
}
