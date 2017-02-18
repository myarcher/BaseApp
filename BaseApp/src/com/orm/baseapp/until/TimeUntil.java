package com.orm.baseapp.until;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUntil {
	public static String timeStampToDate(long timeStamp){  
        Date    date = new Date(timeStamp);  
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
        String dateStr = simpleDateFormat.format(date);  
        return dateStr;  
    }  
  
    public static int getYearByTimeStamp(long timeStamp){  
        String date = timeStampToDate(timeStamp);  
        String year = date.substring(0, 4);  
        return Integer.parseInt(year);  
    }  
  
    public static int getMonthByTimeStamp(long timeStamp){  
        String date = timeStampToDate(timeStamp);  
        String month = date.substring(5, 7);  
        return Integer.parseInt(month);  
    }  
  
    public static int getDayByTimeStamp(long timeStamp){  
        String date = timeStampToDate(timeStamp);  
        String day = date.substring(8, 10);  
        return Integer.parseInt(day);  
    }  
  
    public static int getHourByTimeStamp(long timeStamp){  
        String date = timeStampToDate(timeStamp);  
        String hour = date.substring(11, 13);  
        return Integer.parseInt(hour);  
    }  
  
    public static int getMinuteByTimeStamp(long timeStamp){  
        String date = timeStampToDate(timeStamp);  
        String minute = date.substring(14, 16);  
        return Integer.parseInt(minute);  
    }  
  
    public static int getSecondByTimeStamp(long timeStamp){  
        String date = timeStampToDate(timeStamp);  
        String second = date.substring(17, 19);  
        return Integer.parseInt(second);  
    }  
    public static String getDay(long timeStamp){
		int date= new Date(timeStamp).getDay();
		String xq="";
		switch (date) {
		case 1:
			xq="星期�?";
			break;
            case 2:
            	xq="星期�?";
			break;
       case 3:
    	   xq="星期�?";
	    break;
       case 4:
    	   xq="星期�?";
	break;
      case 5:
    	  xq="星期�?";
	break;
    case 6:
    	xq="星期�?";
	break;
     case 0:
    	 xq="星期�?";
	break;

	
		}
		return xq;
	}
}
