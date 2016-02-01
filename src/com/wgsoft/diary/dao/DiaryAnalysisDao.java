package com.wgsoft.diary.dao;

import java.util.List;

import com.wgsoft.common.dao.BaseDao;
import com.wgsoft.diary.idao.IDiaryAnalysisDao;
import com.wgsoft.diary.model.EchartsOfBar;
/**
 * 
 * @title： DiaryAnalysisDao.java
 * @desc：   日志统计分析
 * @author： Willowgao
 * @date： 2015-11-16 上午10:45:59
 * @version： V1.0<br>
 * @versioninfo： 慕安软件<br>
 * @modify： 更改时间、更改人、更改原因、更改内容<br>
 */
public class DiaryAnalysisDao extends BaseDao implements IDiaryAnalysisDao {

	/**
	 * @see com.wgsoft.diary.idao.IDiaryAnalysisDao#getQueryMaxDiss()
	 */
	public List<EchartsOfBar> getQueryMaxDiss() {
		StringBuffer sql = new StringBuffer("SELECT * FROM (SELECT '日志评论统计分析' title,'评论最多日志' childtitle, '评论数' dataname,");
		sql.append(" '条' dataunit, COUNT(1) xdata, b.diaryid ID, c.username ||TO_CHAR(B.DIARYDATE, 'yyyy-mm-dd') || ' 填写 ' ");
		sql.append("||FN_GETCODEDESC('DIARYTYPE', B.DIARYTYPE) xcomments FROM DIARY_COMMENTS A, DIARY_DAILY B, USERINFO C");
		sql.append(" WHERE A.DIARYID = B.DIARYID  AND B.USERID = C.USERID GROUP BY B.DIARYID, B.USERID, C.USERNAME, B.DIARYTYPE, B.DIARYDATE");
		sql.append(" ORDER BY xdata DESC) WHERE ROWNUM <=10");
		return getSqlList_(sql.toString(), EchartsOfBar.class);
	}

}
