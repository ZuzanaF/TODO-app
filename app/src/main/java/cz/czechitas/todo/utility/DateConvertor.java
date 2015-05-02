package cz.czechitas.todo.utility;

import java.text.SimpleDateFormat;
import java.util.Date;


// date format: http://docs.oracle.com/javase/1.4.2/docs/api/java/text/SimpleDateFormat.html
public class DateConvertor
{
	public static String dateToString(Date date, String format)
	{
		String str = null;
		if(date!=null)
		{
			SimpleDateFormat dateFormat = new SimpleDateFormat(format);
			str = dateFormat.format(date);
		}
		return str;
	}
}
