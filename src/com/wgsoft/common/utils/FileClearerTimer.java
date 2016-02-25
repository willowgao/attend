package com.wgsoft.common.utils;

import java.util.TimerTask;

/**
 * @title： FileClearTimer.java
 * @desc：
 * @author： Willowgao
 * @date： 2016-1-28 上午10:20:48
 * @version： V1.0<br>
 * @versioninfo： 慕安软件<br>
 * @modify： 更改时间、更改人、更改原因、更改内容<br>
 */
public class FileClearerTimer extends TimerTask {

	@SuppressWarnings("static-access")
	@Override
	public void run() {
		Doc2HtmlUtil.getDoc2HtmlUtilInstance().deleteDirFile();
		Doc2HtmlUtil.getDoc2HtmlUtilInstance().deleteExportFile();
	}

}
