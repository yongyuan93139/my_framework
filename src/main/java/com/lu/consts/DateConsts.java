package com.lu.consts;

public interface DateConsts
{
	final String YEAR_MON_DAY = "yyyy-MM-dd";
	final String YEAR_MON_DAY_H_M_S = "yyyy-MM-dd HH:mm:ss";

	final static String[] DATE_FORMAT_PATTERNS = new String[] {
			"yyyy-MM",
			"yyyyMM",
			"yyyy/MM",
			"yyyyMMdd",
			"yyyy-MM-dd",
			"yyyy/MM/dd",
			"yyyyMMddHHmmss",
			"yyyy-MM-dd HH:mm:ss",
			"yyyy/MM/dd HH:mm:ss",
			"yyyyMMddHHmm",
			"yyyy-MM-dd HH:mm",
			"yyyy/MM/dd HH:mm",
			"yyyy-MM-dd HH:mm:ss.SSS" };
}
