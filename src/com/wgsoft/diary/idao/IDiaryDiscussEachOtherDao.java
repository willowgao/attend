package com.wgsoft.diary.idao;


import java.util.List;

import com.wgsoft.common.idao.IBaseDao;
import com.wgsoft.diary.model.DiaryComments;

/**
 * @title： IDiaryDiscussEachOtherDao.java
 * @desc： 日志互评
 * @author： Willowgao
 * @date： 2015-11-12 下午12:39:45
 * @version： V1.0<br>
 * @versioninfo： 远光软件股份有限公司<br>
 * @modify： 更改时间、更改人、更改原因、更改内容<br>
 */
public interface IDiaryDiscussEachOtherDao extends IBaseDao {
 


	/**
	 * @desc: 查询日志所有的评论信息
	 * @param id
	 * @return
	 * @return List<DiaryDaily>
	 * @date： 2015-11-12 下午05:02:39
	 */
	List<DiaryComments> getDiaryCommentsById(String id);
	
	/**
	 * @desc: 增加评论
	 * @param comments
	 * @return
	 * @return String commentid
	 * @date： 2015-11-12 下午12:03:17
	 */
	int delComments(String id);
	
	/**
	 * @desc:更新查阅次数
	 * @param id
	 * @return 
	 * @return int
	 * @date： 2015-11-13 上午09:03:58
	 */
	int addViewCount(String id);

}
