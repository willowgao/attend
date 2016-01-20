package com.wgsoft.diary.idao;

import java.util.List;

import com.wgsoft.common.idao.IBaseDao;
import com.wgsoft.diary.model.EchartsOfBar;

/**
 * 
 * @title： IDiaryAnalysisDao.java
 * @desc： 日志统计分析
 * @author： Willowgao
 * @date： 2015-11-16 上午10:32:50
 * @version： V1.0<br>
 * @versioninfo： 远光软件股份有限公司<br>
 * @modify： 更改时间、更改人、更改原因、更改内容<br>
 */
public interface IDiaryAnalysisDao extends IBaseDao {

	/**
	 * @desc:查询最多评论数的日志，前10
	 * @return
	 * @return List<EchartsOfBar>
	 * @date： 2015-11-16 上午10:50:02
	 */
	List<EchartsOfBar> getQueryMaxDiss();
}
