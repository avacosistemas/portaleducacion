package ar.com.avaco.educacion.ws.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	
	private final static String FORMAT_PATTERN = "yyyyMMdd"; //TODO Parametrizar?
	
	public static Date toDate(String dia) throws ParseException {
		SimpleDateFormat dateFormat=(SimpleDateFormat) DateFormat.getInstance();
		dateFormat.applyPattern(FORMAT_PATTERN);
		return dateFormat.parse(dia);
	}
	
	public static String toString(Date fecha) {
		SimpleDateFormat dateFormat=(SimpleDateFormat) DateFormat.getInstance();
		dateFormat.applyPattern(FORMAT_PATTERN);
		return dateFormat.format(fecha);
	}

}
