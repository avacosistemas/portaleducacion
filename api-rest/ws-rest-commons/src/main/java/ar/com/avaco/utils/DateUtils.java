package ar.com.avaco.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class DateUtils {
	
	private final static String FORMAT_PATTERN = "yyyyMMdd"; //TODO Parametrizar?
	
	public static Date toDate(String dia, String pattern) throws ParseException {
		SimpleDateFormat dateFormat=(SimpleDateFormat) DateFormat.getInstance();
		dateFormat.applyPattern(pattern);
		return dateFormat.parse(dia);
	}
	
	public static Date toDate(String dia) throws ParseException {
		return toDate(dia, FORMAT_PATTERN);
	}
	
	public static String toString(Date fecha) {
		SimpleDateFormat dateFormat=(SimpleDateFormat) DateFormat.getInstance();
		dateFormat.applyPattern(FORMAT_PATTERN);
		return dateFormat.format(fecha);
	}
	
	public static String toString(LocalDate fecha) {
		SimpleDateFormat dateFormat=(SimpleDateFormat) DateFormat.getInstance();
		dateFormat.applyPattern(FORMAT_PATTERN);
		return dateFormat.format(fecha);
	}

}
