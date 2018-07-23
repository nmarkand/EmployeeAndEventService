package com.rest.springboot.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.regex.Pattern;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public final class ServiceUtil 
{
	private static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\."+
									            "[a-zA-Z0-9_+&*-]+)*@" +
									            "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
									            "A-Z]{2,7}$";
	
	private static final DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	
	public static String getDateToString(final Date date) 
	{       	
		return dateFormat.format(date);
	}
	
	public static Date getStringToDate(final String date)  
	{
		try 
		{
			return dateFormat.parse(date);
		} 
		catch (java.text.ParseException e) 
		{
			 return null;
		}
	}
	
	public static void isNotNull(Object obj) 
	{
        if (obj == null) throw new IllegalArgumentException("illegal null");
    }
    
	public static void isNotNullAndNotEmpty(String s) 
	{
		isNotNull(s);
        if (s.isEmpty()) throw new IllegalArgumentException("illegal empty string");
    }
    
	public static String getUuid()
	{
		UUID uuid = UUID.randomUUID();
	    
		return uuid.toString();
	}
	
	
	public static boolean isValidEmailAddress(final String email) 
	{                 
		Pattern pat = Pattern.compile(EMAIL_REGEX);
		
		if (email == null)
			return false;
		
		return pat.matcher(email).matches();
	}
	
	public static String[] stringSplitor(final String str)
	{		
		String[] tokens = str.split("/");

		return tokens;		
	}
	
	@SuppressWarnings("unchecked")
	public static String getJsonObjectAsString(final String employeeUuid, 
				final String serviceMessage, final String date)
	{
		JSONObject jsonObj = new JSONObject();	
		
		jsonObj.put("employeeuuid", employeeUuid);
		jsonObj.put("eventtype", serviceMessage);
		jsonObj.put("eventdate", date);

		return jsonObj.toJSONString();
	}
	
	public static JSONObject parseJsonObject(final String jsonString)
	{
		JSONObject jsonObject = null;
		
		JSONParser parser = new JSONParser();
		
		try 
		{
			jsonObject = (JSONObject) parser.parse(jsonString);
		} 
		catch (ParseException e) 
		{
			e.printStackTrace();
		}
		 
		return jsonObject;
		
	}
	
	public static String getJsonObjectValue(final JSONObject jsonObject, final String key)
	{
		return jsonObject.get(key).toString();
	}
}
