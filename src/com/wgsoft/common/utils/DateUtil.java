package com.wgsoft.common.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 
 * @title： DateUtil.java
 * @desc： 时间工具
 * @author： Willowgao
 * @date： 2015-9-8 下午12:55:38
 * @version： V1.0<br>
 * @versioninfo： 远光软件股份有限公司<br>
 * @modify： 更改时间、更改人、更改原因、更改内容<br>
 */
public class DateUtil {

	public static final String YNYMDRHMS = "yyyy年MM月dd日 HH:mm:ss"; // yyyy年MM月dd日
	// hh:mm:ss
	public static final String YNYMDR = "yyyy年MM月dd日"; // yyyy年MM月dd日
	public static final String YMDHMS = "yyyy-MM-dd HH:mm:ss"; // 年月日时分秒
	public static final String YMDHM = "yyyy-MM-dd HH:mm"; // 年月日时分秒
	public static final String YMD = "yyyy-MM-dd"; // 年月日

	public static final String HMS = "HH:mm:ss"; // 年月日时分秒
	public static final String MDYHMS = "MM/dd/yyyy HH:mm:ss.SSS"; // 年月日时分秒
	public static final String YMD2 = "yyyyMMdd";
	public static final String FORMAT_MONTH = "yyyy-MM";
	public static final String FORMAT_YEAR = "yyyy";
	public static final String[] WEEK_NAMES = { "日", "一", "二", "三", "四", "五", "六" };

	/**
	 * 字符串转换为Timestamp
	 * 
	 * @param date
	 * @return
	 */
	public static Timestamp str2Timestamp(String date) {
		Date dt = string2Date(date, YMDHMS);
		return new Timestamp(dt.getTime());
	}

	/**
	 * Date型的数据转换成String
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String date2String(Date date, String format) {
		if (date == null) {
			return "";
		}
		SimpleDateFormat sf = new SimpleDateFormat(format);
		return sf.format(date);
	}

	/**
	 * 将字符串转换为String
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static Date string2Date(String date, String format) {
		SimpleDateFormat sf = new SimpleDateFormat(format);
		Date rel = null;
		try {
			rel = sf.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rel;
	}

	/**
	 * 功能：根据指定日期格式输出当前日期,默认yyyy-MM-dd
	 * 
	 * @param formatstr
	 * @return
	 */
	public static String getNowDateByFormat(String formatstr) {
		if (formatstr == null || "".equals(formatstr))
			formatstr = YMD;
		SimpleDateFormat format = new SimpleDateFormat(formatstr);
		return format.format(new Date());
	}

	/**
	 * 将日期字符串转换为指定的日期字符串文本格式。 <br/>
	 * 
	 * @param date
	 *            日期字符串
	 * @param fromFormat
	 *            字符串原本的日期格式
	 * @param toFormat
	 *            将要转变成的日期格式
	 * @return 指定格式的日期字符串
	 * @author willowgao
	 */
	public static String string2Format(String date, String fromFormat, String toFormat) {
		Date d = string2Date(date, fromFormat);
		return date2String(d, toFormat);
	}

	/**
	 * 将Date对象按照所给的日期格式，进行格式内的截取。<br/>
	 * 如：<code>date</code>2014-01-29 12:00:04,<code>format</code> yyyy-MM-dd,
	 * 转换后输出：2014-01-29 00:00:00 000 ，即非yyyy-MM-dd内的为初始值状态。
	 * 
	 * @param date
	 *            日期对象
	 * @param format
	 *            格式
	 * @return 截取后的日期
	 * @author willowgao
	 */
	public static Date date2Format(Date date, String format) {
		String dateStr = date2String(date, format);
		return string2Date(dateStr, format);
	}

	/**
	 * 获取日历对象。 <br/>
	 * 
	 * @param date
	 *            日历的时间
	 * @return 日历对象
	 * @author willowgao
	 */
	public static Calendar newCalendar(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		return calendar;
	}

	/**
	 * 根据间隔的秒来获取开始至结束时间段的所有时间
	 * 
	 * @param start
	 *            开始时间 yyyy-MM-dd HH:mm:ss
	 * @param end
	 *            结束时间 yyyy-MM-dd HH:mm:ss
	 * @param secondSetp
	 *            间隔的秒
	 * @return 假设开始时间为："2014-06-01 02:50:00"　结束时间为：　"2014-06-01 02:50:30" 则返回格式：
	 *         ["2014-06-01 02:50:00","2014-06-01 02:50:10",
	 *         "2014-06-01 02:50:20","2014-06-01 02:50:30"]
	 * @author tanluole
	 */
	public static List<String> getPeriods(String start, String end, int secondSetp) {
		List<String> allTimes = new ArrayList<String>(); // 所有的时间段
		Date sd = string2Date(start, YMDHMS);
		Date ed = string2Date(end, YMDHMS);
		Date temp = (Date) sd.clone();
		String starts = start.substring((start.length() - 1), start.length());
		if ("0".equals(starts)) { // 如果开始时间尾数为0
			allTimes.add(start);
		}
		while (true) {

			Calendar cald = newCalendar(temp);
			cald.add(Calendar.SECOND, secondSetp);
			temp = cald.getTime();
			if (temp.after(ed)) {
				break;
			}
			allTimes.add(date2String(temp, YMDHMS));
		}

		return allTimes;
	}

	/**
	 * 计算时间间隔总数
	 * 
	 * @param start
	 *            开始时间
	 * @param end
	 *            结束时间
	 * @param secondSetp
	 *            间隔（秒）
	 * @return
	 */
	public static int getCount(String start, String end, int secondSetp) {
		String starts = start.substring((start.length() - 1), start.length());
		String ends = end.substring((end.length() - 1), end.length());
		if (!"0".equals(starts)) { // 如果开始日期尾数不为0，则应往后推，如"2014-09-19 00:00:02"应变成"2014-09-19 00:00:10"
			start = start.substring(0, (start.length() - 1)) + "0";
			Calendar cald = DateUtil.newCalendar(DateUtil.string2Date(start, DateUtil.YMDHMS));
			cald.add(Calendar.SECOND, secondSetp);
			start = DateUtil.date2String(cald.getTime(), DateUtil.YMDHMS);
		}
		if (!"0".equals(ends)) { // 如果结尾不为0，则往前推，如"2014-09-19 00:01:01"应变成"2014-09-19 00:01:00"
			end = end.substring(0, (end.length() - 1)) + "0";
		}

		Date sd = DateUtil.string2Date(start, DateUtil.YMDHMS);
		Date ed = DateUtil.string2Date(end, DateUtil.YMDHMS);

		int shouldBe = (int) ((ed.getTime() - sd.getTime()) / 10000) + 1; // 应该有多少条数据
		return shouldBe;
	}

	/**
	 * 功能：解析指定日期字符串为日期类型
	 * 
	 * @param date
	 *            yyyy-MM
	 * @return
	 */
	public static Date parseTheDateOfMonth(String date) {
		return string2Date(date, FORMAT_MONTH);
	}
	

	/**
	 * 功能：获取指定日期(月份)所在月份的最后一天的日期
	 * 
	 * @param date
	 *            (yyyy-MM)
	 * @return yyyy-MM-dd
	 */
	public static String getFirstDateOfMonth(String date) {
		String last_date_str = null;
		Date last_date = null;
		SimpleDateFormat format = null;
		Calendar cal = Calendar.getInstance();
		cal.setTime(parseTheDateOfMonth(date));
		cal.set(Calendar.DATE, cal.getActualMinimum(Calendar.DATE));
		last_date = cal.getTime();
		format = new SimpleDateFormat(YMD);
		last_date_str = format.format(last_date);
		return last_date_str;
	}
	

	/**
	 * 功能：获取指定日期(月份)所在月份的最后一天的日期
	 * 
	 * @param date
	 *            (yyyy-MM)
	 * @return yyyy-MM-dd
	 */
	public static String getLastDateOfMonth(String date) {
		String last_date_str = null;
		Date last_date = null;
		SimpleDateFormat format = null;
		Calendar cal = Calendar.getInstance();
		cal.setTime(parseTheDateOfMonth(date));
		cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
		last_date = cal.getTime();
		format = new SimpleDateFormat(YMD);
		last_date_str = format.format(last_date);
		return last_date_str;
	}

	/**
	 * @desc 返回指定日期对应的是周几
	 * @param date
	 * @return
	 */
	public static String getDayOfWeekByTime(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return WEEK_NAMES[cal.get(Calendar.DAY_OF_WEEK) - 1];
	}

	/**
	 * @desc 返回指定日期对应的是周几
	 * @param datestr
	 *            yyyy-MM-dd
	 * @return
	 */
	public static String getDayOfWeekByTime(String datestr) {
		return getDayOfWeekByTime(string2Date(datestr, YMD));
	}

	/**
	 * @desc:取周一
	 * @param date
	 * @return
	 * @return String
	 * @date： 2015-11-9 下午08:21:05
	 */
	public static String getMonday(Date date) {
		String re_date = getDayOfWeekByTime(date);
		String strDate = date2String(date, YMD);
		if (re_date == "二") {
			re_date = addIntervalOfDate(strDate, 0, 0, -1, YMD);
		} else if (re_date == "三") {
			re_date = addIntervalOfDate(strDate, 0, 0, -2, YMD);
		} else if (re_date == "四") {
			re_date = addIntervalOfDate(strDate, 0, 0, -3, YMD);
		} else if (re_date == "五") {
			re_date = addIntervalOfDate(strDate, 0, 0, -4, YMD);
		} else if (re_date == "六") {
			re_date = addIntervalOfDate(strDate, 0, 0, -5, YMD);
		} else if (re_date == "日") {
			re_date = addIntervalOfDate(strDate, 0, 0, -6, YMD);
		} else {
			re_date = date2String(date, YMD);
		}
		return re_date;
	}

	/**
	 * @desc:取周五
	 * @param date
	 * @return
	 * @return String
	 * @date： 2015-11-9 下午08:20:56
	 */
	public static String getFriday(Date date) {
		String re_date = getDayOfWeekByTime(date);
		String strDate = date2String(date, YMD);
		if (re_date == "二") {
			re_date = addIntervalOfDate(strDate, 0, 0, 3, YMD);
		} else if (re_date == "三") {
			re_date = addIntervalOfDate(strDate, 0, 0, 2, YMD);
		} else if (re_date == "四") {
			re_date = addIntervalOfDate(strDate, 0, 0, 1, YMD);
		} else if (re_date == "一") {
			re_date = addIntervalOfDate(strDate, 0, 0, 4, YMD);
		} else if (re_date == "六") {
			re_date = addIntervalOfDate(strDate, 0, 0, -1, YMD);
		} else if (re_date == "日") {
			re_date = addIntervalOfDate(strDate, 0, 0, -2, YMD);
		}
		return re_date;
	}

	/**
	 * 指定日期上增加相应的年，月，日。负数表示减
	 * 
	 * @param strdate
	 *            传入的日期字符串
	 * @param year
	 *            增（减）年数
	 * @param month增
	 *            （减）月数
	 * @param day
	 *            增（减）日数
	 * @param format
	 *            日期格式
	 * @return 日期字符串
	 */
	public static String addIntervalOfDate(String strdate, int year, int month, int day, String format) {

		if (null == format || "".equals(format)) {
			// 月
			if (strdate.length() == 7) {
				format = DateUtil.FORMAT_MONTH;
			} else if (strdate.length() == 10) {// 日
				format = DateUtil.YMD;
			} else if (strdate.length() == 4) {// 年
				format = DateUtil.FORMAT_YEAR;
			}
		}
		Date d = DateUtil.string2Date(strdate, format);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(d);
		calendar.add(Calendar.YEAR, year);
		calendar.add(Calendar.MONTH, month);
		calendar.add(Calendar.DATE, day);
		return DateUtil.date2String(calendar.getTime(), format);

	}

	/**
	 * 功能：返回上一月的日期,解析指定日期字符串为日期类型。
	 * 
	 * @param date
	 * @param formatStr
	 * @return
	 */
	public static String getLastMonth(String date) {
		return getUpperMonth(date, null);
	}

	/**
	 * 功能：返回上一月的日期,解析指定日期字符串为日期类型。
	 * 
	 * @param date
	 * @param formatStr
	 * @return
	 */
	public static String getUpperMonth(String date, String formatStr) {

		if (formatStr == null || formatStr.length() == 0) {
			formatStr = "yyyy-MM";
		}
		SimpleDateFormat format = null;
		Date ret = null;
		try {
			format = new SimpleDateFormat(formatStr);

			ret = format.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		ret.setMonth(ret.getMonth() - 1);
		return format.format(ret);
	}

	/**
	 * 功能：返回上一年的日期，解析指定日期字符串为日期类型,。
	 * 
	 * @param date
	 * @param formatStr
	 * @return
	 */
	public static String getUpperYear(String date) {
		return getLastYear(date, null);
	}

	/**
	 * 功能：返回上一年的日期，解析指定日期字符串为日期类型,。
	 * 
	 * @param date
	 * @param formatStr
	 * @return
	 */
	public static String getLastYear(String date, String formatStr) {
		// if(StringUtils.formatStr

		if (formatStr == null || formatStr.length() == 0) {
			formatStr = "yyyy";
		}
		SimpleDateFormat format = null;
		Date ret = null;
		try {
			format = new SimpleDateFormat(formatStr);

			ret = format.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		ret.setYear(ret.getYear() - 1);
		return format.format(ret);
	}
	
	
	public static String getQuarterFirstMonth(Date date){
		String reDay = null;
		int year =  date.getYear()+1900;
		int month = date.getMonth();
		if(month>=1 &&month<=3){
			reDay = year+"-01-01";
		}else if(month>=4 &&month<=6){
			reDay = year+"-04-01";
		}else if(month>=7 &&month<=9){
			reDay = year+"-07-01";
		}else if(month>=10 &&month<=12){
			reDay = year+"-10-01";
		}
		return reDay;
	}

	public static  String getQuarterLastDay(Date date){
		String reDay = null;
		int month = date.getMonth();
		int year =  date.getYear()+1900;
		if(month>=1 &&month<=3){
			reDay = year+"-03-31";
		}else if(month>=4 &&month<=6){
			reDay = year+"-06-30";
		}else if(month>=7 &&month<=9){
			reDay = year+"-09-30";
		}else if(month>=10 &&month<=12){
			reDay = year+"-12-31";
		}
		return reDay;
	}
	
	public static void main(String[] args) {
		System.out.println(date2String(new Date(), HMS));
	}

}
