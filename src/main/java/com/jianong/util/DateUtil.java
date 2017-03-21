package com.jianong.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.google.common.collect.Lists;
import com.jianong.base.GovernmentConst;

/**
 * 说明：日期处理
 */
public class DateUtil {

	private final static String DATE = "yyyy-MM-dd";
	private final static String DATE_TIME = "yyyy-MM-dd HH:mm:ss";

	private final static SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");
	private final static SimpleDateFormat sdfDay = new SimpleDateFormat("yyyy-MM-dd");
	private final static SimpleDateFormat sdfDays = new SimpleDateFormat("yyyyMMdd");
	private final static SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * 返回当天的小时
	 * 
	 * @Title: getDateTimeList
	 * @Description:
	 * @return
	 * @return: List<String>
	 */
	public final static List<String> getDateTimeList() {
		List<String> datas = Lists.newArrayList();
		LocalTime dateTime = LocalTime.now();
		int hour = dateTime.getHour();
		for (int i = 0; i <= hour; i++) {
			if (i < 10) {
				datas.add("0" + i);
			} else {
				datas.add(i + "");
			}
		}
		return datas;
	}

	private final static ThreadLocal<DateFormat> local = new ThreadLocal<DateFormat>() {
		@Override
		protected DateFormat initialValue() {
			return new SimpleDateFormat(DATE);
		}
	};

	private final static ThreadLocal<DateFormat> dateTimeLocal = new ThreadLocal<DateFormat>() {
		@Override
		protected DateFormat initialValue() {
			return new SimpleDateFormat(DATE_TIME);
		}
	};

	/**
	 * 把时间戳转换为时间
	 * 
	 * @Title: getDateTimeFromTimeStrap
	 * @Description:
	 * @param time
	 * @return
	 * @return: String yyyy-mm-dd
	 */
	public static String getDateTimeFromTimeStrap(long time) {
		Date date = new Date(time * 1000);
		return dateTimeLocal.get().format(date);
	}

	public static String getDateTimeFromTimeStrap(long time, String format) {
		SimpleDateFormat sf = new SimpleDateFormat(format);
		Date date = new Date(time);
		return sf.format(date);
	}

	public static long getTimeStrapFromDate(String date) throws Exception {
		return getTimeStrapFromDate(date, GovernmentConst.BEGIN);
	}

	public static long getTimeStrapFromDate(String date, int type) throws Exception {
		if (type == GovernmentConst.BEGIN) {
			if (StringUtils.indexOf(date, ":") == -1) {
				date += " 00:00:00";
			}
		}
		if (type == GovernmentConst.END) {
			if (StringUtils.indexOf(date, ":") == -1) {
				date += " 23:59:59";
			}
		}
		Date da = dateTimeLocal.get().parse(date);
		return da.getTime() / 1000L;
	}

	public static long getTimeStrapFromDate(String date, String format) throws Exception {
		SimpleDateFormat sf = new SimpleDateFormat(format);
		Date da = sf.parse(date);
		return da.getTime();
	}

	/**
	 * 获取YYYY格式
	 * 
	 * @return
	 */
	public static String getYear() {
		return sdfYear.format(new Date());
	}

	/**
	 * 获取YYYY-MM-DD格式
	 * 
	 * @return
	 */
	public static String getDay() {
		return sdfDay.format(new Date());
	}

	/**
	 * 获取YYYYMMDD格式
	 * 
	 * @return
	 */
	public static String getDays() {
		return sdfDays.format(new Date());
	}

	/**
	 * 获取YYYY-MM-DD HH:mm:ss格式
	 * 
	 * @return
	 */
	public static String getTime() {
		return sdfTime.format(new Date());
	}

	/**
	 * @Title: compareDate
	 * @Description: TODO(日期比较，如果s>=e 返回true 否则返回false)
	 * @param s
	 * @param e
	 * @return boolean
	 * @throws @author
	 *             fh
	 */
	public static boolean compareDate(String s, String e) {
		if (fomatDate(s) == null || fomatDate(e) == null) {
			return false;
		}
		return fomatDate(s).getTime() >= fomatDate(e).getTime();
	}

	/**
	 * 格式化日期
	 * 
	 * @return
	 */
	public static Date fomatDate(String date) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return fmt.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String fomatDateTime(Date date) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return fmt.format(date);
	}

	/**
	 * 校验日期是否合法
	 * 
	 * @return
	 */
	public static boolean isValidDate(String s) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			fmt.parse(s);
			return true;
		} catch (Exception e) {
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			return false;
		}
	}

	/**
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static int getDiffYear(String startTime, String endTime) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			// long aa=0;
			int years = (int) (((fmt.parse(endTime).getTime() - fmt.parse(startTime).getTime()) / (1000 * 60 * 60 * 24))
					/ 365);
			return years;
		} catch (Exception e) {
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			return 0;
		}
	}

	/**
	 * <li>功能描述：时间相减得到天数
	 * 
	 * @param beginDateStr
	 * @param endDateStr
	 * @return long
	 * @author Administrator
	 */
	public static long getDaySub(String beginDateStr, String endDateStr) {
		long day = 0;
		java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
		java.util.Date beginDate = null;
		java.util.Date endDate = null;

		try {
			beginDate = format.parse(beginDateStr);
			endDate = format.parse(endDateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		day = (endDate.getTime() - beginDate.getTime()) / (24 * 60 * 60 * 1000);
		// System.out.println("相隔的天数="+day);

		return day;
	}

	/**
	 * 得到n天之后的日期
	 * 
	 * @param days
	 * @return
	 */
	public static String getAfterDayDate(String days) {
		int daysInt = Integer.parseInt(days);

		Calendar canlendar = Calendar.getInstance(); // java.util包
		canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
		Date date = canlendar.getTime();

		SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = sdfd.format(date);

		return dateStr;
	}

	/**
	 * 得到n天之前的日期
	 * 
	 * @param days
	 *            yyyy-mm-dd
	 * @return
	 */
	public static String getBeforeDayDate(String days) {
		int daysInt = Integer.parseInt(days);

		Calendar canlendar = Calendar.getInstance(); // java.util包
		canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
		Date date = canlendar.getTime();

		SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = sdfd.format(date);

		return dateStr;
	}

	/**
	 * 得到n天之后是周几
	 * 
	 * @param days
	 * @return
	 */
	public static String getAfterDayWeek(String days) {
		int daysInt = Integer.parseInt(days);
		Calendar canlendar = Calendar.getInstance(); // java.util包
		canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
		Date date = canlendar.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("E");
		String dateStr = sdf.format(date);
		return dateStr;
	}

	public static void main(String[] args) throws Exception {
		System.out.println(getDateTimeList());
	}

}
