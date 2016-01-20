package com.wgsoft.common.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
 

public class CnDate extends Timestamp {
	public CnDate(long time) {
		super(time);
	}
	public CnDate(Timestamp timestamp) {
		
		super(timestamp.getTime());
	}
	/**
	 * 比较两个日期是否为同一天
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean compare(Date date1,Date date2){
		 SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
		return simple.format(date1).equals(simple.format(date2));
	}
	/**
	 *将字符串转换为Util.DATE
	 * @param date1
	 * @param date2
	 * @return
	 * @throws ParseException 
	 */
	public static Date stringToDate(String str) throws ParseException{
		 SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
		return  simple.parse(str);
	}
	/**
	 * 得到从当前年的前五个年份与后五个年份。
	 * @return List
	 */
	public static List<Integer> getYearList(){
		List<Integer> list = new  ArrayList<Integer>();
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		list.add(new Integer(year));
		for(int i = 1 ;i<=5;i++){
			list.add(new Integer(year-i));
			list.add(new Integer(year+i));
		}
		Collections.sort(list);
		return list;
	}
	/**
	 * 得到从当前年的前五个年份与后五个年份。
	 * @return Map
	 */
	public static Map<String,Integer> getYearMap(){
		TreeMap<String,Integer> map = new  TreeMap<String,Integer>();
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		map.put(year+"", new Integer(year));
		for(int i = 1 ;i<=5;i++){
			map.put(year-i+"", new Integer(year-i));
			map.put(year+i+"", new Integer(year+i));
		}
		return map;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = -8328167242991198888L;

	public String toString() {	 
		String str = "";
		int pos = super.toString().lastIndexOf(".");
		str = super.toString();
		if(str.indexOf("00:00:00")!=-1){
			str = str.substring(0,pos).replaceAll("00:00:00","");
			
		}
		return str;
	}
	public static void main(String [] args){
		System.out.println(CnDate.getYearMap());
	}
	
}




