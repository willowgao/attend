package com.wgsoft.diary.dao;

import java.util.List;

import com.wgsoft.common.dao.BaseDao;
import com.wgsoft.diary.idao.IDiaryDiscussEachOtherDao;
import com.wgsoft.diary.model.DiaryComments;

/**
 * @title： DiaryDiscussEachOtherDao.java
 * @desc： 日志互评
 * @author： Willowgao
 * @date： 2015-11-12 下午12:43:41
 * @version： V1.0<br>
 * @versioninfo： 远光软件股份有限公司<br>
 * @modify： 更改时间、更改人、更改原因、更改内容<br>
 */
@SuppressWarnings("unchecked")
public class DiaryDiscussEachOtherDao extends BaseDao implements IDiaryDiscussEachOtherDao {

	/**
	 * @see com.wgsoft.diary.idao.IDiaryDiscussEachOtherDao#getDiaryCommentsById(String)
	 */
	public List<DiaryComments> getDiaryCommentsById(String id) {
		StringBuffer sql = new StringBuffer(
				"SELECT a.*,b.username FROM DIARY_COMMENTS a,userinfo b where a.userid = b.userid");
		sql.append(" and DIARYID = '").append(id).append("'");
		sql.append(" order by a.commdate desc");
		return getSqlList_(sql.toString(), DiaryComments.class);
	}

	/**
	 * @see com.wgsoft.diary.idao.IDiaryDiscussEachOtherDao#delComments(String)
	 */
	public int delComments(String id) {
		StringBuffer sql = new StringBuffer("delete from Diary_Comments ");
		sql.append(" where commentid ='").append(id).append("'");
		return getSqlUpdate(sql.toString());
	}

	/**
	 * @see com.wgsoft.diary.idao.IDiaryDiscussEachOtherDao#addViewCount(String)
	 */
	public int addViewCount(String id) {
		StringBuffer sql = new StringBuffer("update diary_daily set viewcount=nvl(viewcount,0)+1");
		sql.append(" where diaryid ='").append(id).append("'");
		return getSqlUpdate(sql.toString());
	}

}
