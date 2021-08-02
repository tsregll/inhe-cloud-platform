package com.inhe.config;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.springframework.core.convert.converter.Converter;

public class DateConverter implements Converter<String, Date>{
	
	private final String dateFormat;
	
	public DateConverter(String dateFormat){
		this.dateFormat = dateFormat;
	}
	
	@Override
	public Date convert(String arg0) {
		Calendar calendar = null;
		try {
			SimpleDateFormat myFmt=new SimpleDateFormat(dateFormat);
			Date date = myFmt.parse(arg0);
			calendar = Calendar.getInstance();
			calendar.setTime(date);
			TimeZone timeZone = calendar.getTimeZone();
			calendar.add(Calendar.SECOND, timeZone.getRawOffset() / 1000);
		} catch (ParseException e) {
			//e.printStackTrace();
		}
		if(calendar == null){
			return null;
		}
		else{
			return calendar.getTime();
		}	
	}
}
