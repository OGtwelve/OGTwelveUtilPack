package cn.com.ogtwelve.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class DateUtil{
	private static DateUtil instance;

	public static DateUtil getInstance() {
		if (null == instance) {
			synchronized (DateUtil.class) {
				if (null == instance) {
					instance = new DateUtil();
				}
			}
		}
		return instance;
	}
	/**
	 * 获取UTC时间
	 * @return
	 */
	
	public Date getUTCDate()
	{
		 Calendar calendar=Calendar.getInstance();
	     int offset= calendar.get(Calendar.ZONE_OFFSET);
	     int  dstoff=calendar.get(Calendar.DST_OFFSET);
	     calendar.add(Calendar.MILLISECOND, -(offset+dstoff));
	     return calendar.getTime();
	}
	/**
	 * 获取sqlite数据库的date字符串
	 * 
	 * @param dt
	 * @return
	 */
	public String getFDIDateStr(Date dt) {
		String str = "";
		SimpleDateFormat formater = new SimpleDateFormat("yyyyMMddHHmmss");
		str = formater.format(dt);

		return str;
	}
	/**
	 * 获取sqlite数据库的date字符串
	 * @param dt
	 * @return
	 */
	public String GetSqliteDateStr(Date dt)
	{
		String str="";
		SimpleDateFormat  formater=new  SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		str=formater.format(dt);

		return str;	
	}
	/**
	 * 日期字符串按默认格式转换为日期。
	 * @param dateStr
	 * @return
	 */
	public Date parse(String dateStr) {
		
		try {
			SimpleDateFormat formater = new SimpleDateFormat("yyyyMMddHHmmss");
			 if(dateStr.length()==8) {
				 formater=new SimpleDateFormat("yyyyMMdd");
			 }
			return formater.parse(dateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

		
	}

	 /** long类型转换成日期
     * @param number 毫秒数
    * @return String yyyy-MM-dd HH:mm:ss
     */
	public static Date longToDate(long number) throws ParseException {
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		//long转Date
		Date date = sd.parse(sd.format(new Date(number)));
		return date;
	}


	/**
	 *以制定格式返回日期字符串
	 * @param dt
	 * @param formate
	 * @return
	 */
	public String GetDateStr(Date dt,String formate)
	{
		SimpleDateFormat  formater=new  SimpleDateFormat(formate);
		return formater.format(dt);
	}
	/**
	 *以制定格式返回日期字符串
	 * @param dt
	 * @param formate
	 * @return
	 */
	public String GetDateStr(Date dt)
	{

		SimpleDateFormat  formater=new  SimpleDateFormat("yyyyMMddHHmmss");
		return formater.format(dt);
	}

	/**
	 * 日期加减
	 * @param date
	 * @param days
	 * @return
	 */
	public String getPlusDays(String date,int days){
		DateTimeFormatter sdf=DateTimeFormatter.ofPattern("yyyyMMdd");
		LocalDate localDate=LocalDate.parse(date,sdf);
		localDate=localDate.plusDays(days);
		String strDate=localDate.format(sdf);
		return strDate;
	}

}