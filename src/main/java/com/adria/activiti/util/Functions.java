package com.adria.activiti.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Functions 
{
	public static final Date StringToDate(String dateString)
	{
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date = null ;
		try {
			date = formatter.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	public static final  String DateToString(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String stringDate = null;
		stringDate = formatter.format(date);
		return stringDate;
	}
}
