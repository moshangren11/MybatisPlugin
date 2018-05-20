package com.bhc.action;

import com.bhc.ui.BuildFile;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;

/**
 * Title BuildFileAction
 * Package com.bhc.action
 * Copyright 2018 www.hundsun.com All Rights Reserved.
 *
 * @author zhanggd1816@hundsun.com
 * @version V5.0.0
 * @date 2018-05-18 0018 13:09
 */
public class BuildFileAction extends AnAction {

	@Override
	public void actionPerformed(AnActionEvent e) {
////		Messages.showMessageDialog("hello","Mybatis Plugin",Messages.getInformationIcon());
		BuildFile dialog = new BuildFile();
		dialog.pack();
		dialog.setVisible(true);
////		System.exit(0);

//		Project project = e.getProject();
//		if (project != null) {
//			VirtualFile file = (VirtualFile)e.getData(CommonDataKeys.VIRTUAL_FILE);
////			TestDialog dialog = new TestDialog(project);
//			BuildFile dialog = new BuildFile(project);
//			dialog.showAndGet();
//		}
	}
}
