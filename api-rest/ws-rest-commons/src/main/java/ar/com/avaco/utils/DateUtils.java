package ar.com.avaco.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	
	public final static String FORMAT_PATTERN = "yyyyMMdd"; //TODO Parametrizar?
	public final static String FULL_24_HS = "dd/MM/yyyy HH:mm:ss";
	
	public static Date toDate(String dia, String pattern) throws ParseException {
		SimpleDateFormat dateFormat=(SimpleDateFormat) DateFormat.getInstance();
		dateFormat.applyPattern(pattern);
		return dateFormat.parse(dia);
	}
	
	public static Date toDate(String dia) throws ParseException {
		return toDate(dia, FORMAT_PATTERN);
	}
	
	public static String toString(Date fecha) {
		return toString(fecha, "dd/MM/yyyy");
	}
	
	public static String toString(Date fecha, String pattern) {
		SimpleDateFormat dateFormat=(SimpleDateFormat) DateFormat.getInstance();
		dateFormat.applyPattern(pattern);
		return dateFormat.format(fecha);
	}
	
	public static String toString(LocalDate fecha) {
		SimpleDateFormat dateFormat=(SimpleDateFormat) DateFormat.getInstance();
		dateFormat.applyPattern(FORMAT_PATTERN);
		return dateFormat.format(fecha);
	}

    public static Date getFechaYHoraActual() {  
    	Calendar cal = Calendar.getInstance();
    	return cal.getTime();
    }

}
