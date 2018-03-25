package br.ucb.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataUtil {
	
	public  static Date convertStringInDate(String data, String formato){
		DateFormat formatter = new SimpleDateFormat(formato);
		Date date = null;
		try {
			date = (Date)formatter.parse(data);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	public static String convertDateInString(Date date, String formato){
		DateFormat out = new SimpleDateFormat(formato);
        return out.format(date);
	}

}
