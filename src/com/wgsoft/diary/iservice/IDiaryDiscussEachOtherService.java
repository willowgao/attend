package com.wgsoft.diary.iservice;

import java.util.List;
import java.util.Map;

import com.wgsoft.diary.model.DiaryComments;
import com.wgsoft.diary.model.DiaryDaily;

/**
 * @title： IDiaryDiscussEachOtherService.java
 * @desc：日志互评
 * @author： Willowgao
 * @date： 2015-11-12 上午11:59:21
 * @version： V1.0<br>
 * @versioninfo： 慕安软件<br>
 * @modify： 更改时间、更改人、更改原因、更改内容<br>
 */
public interface IDiaryDiscussEachOtherService {

	/**
	 * @desc: 查询所有日志
	 * @param queryMap
	 * @return
	 * @return List<DiaryDaily>
	 * @date： 2015-11-11 下午08:33:30
	 */
	List<DiaryDaily> getDiarysForDiscuss(Map<String, Object> queryMap);

	/**
	 * @desc: 增加评论
	 * @param comments
	 * @return
	 * @return String commentid
	 * @date： 2015-11-12 下午12:03:17
	 */
	String addDiscuss(DiaryComments comments);

	/**
	 * @desc: 增加评论
	 * @param comments
	 * @return
	 * @return String commentid
	 * @date： 2015-11-12 下午12:03:17
	 */
	int delComments(String id);

	/**
	 * @desc:更新日志查阅信息
	 * @param id
	 * @return
	 * @return int
	 * @date： 2015-11-13 上午09:07:44
	 */
	int addViewCount(String id);

	/**
	 * @desc: 查询日志所有的评论信息
	 * @param id
	 * @return
	 * @return List<DiaryComments>
	 * @date： 2015-11-12 下午05:02:39
	 */
	List<DiaryComments> getDiaryCommentsById(String id);

}
