package lk;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Time
{
    public static String GetTime()
    {
        Calendar   calDate   =   new   GregorianCalendar();
        return "[" + ModifyFmt(Integer.toString(calDate.get(Calendar.HOUR_OF_DAY))) + ":" +
        ModifyFmt(Integer.toString(calDate.get(Calendar.MINUTE))) + ":" +
        ModifyFmt(Integer.toString(calDate.get(Calendar.SECOND))) + "]";
    }
    
    private static String ModifyFmt(String strStr)
    {
        if (strStr.length() == 1)
        {
            return "0" + strStr;
        }
        
        return strStr;
    }
}
