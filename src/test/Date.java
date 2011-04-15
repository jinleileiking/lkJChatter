package test;
import   java.util.*;

public class Date
{

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        // TODO Auto-generated method stub
   
        
        Calendar   todaysDate   =   new   GregorianCalendar();   
        System.out.println(todaysDate.get(Calendar.YEAR)); 
        System.out.println(todaysDate.get(Calendar.MONTH)   +   1); 
        System.out.println(todaysDate.get(Calendar.DAY_OF_MONTH)); 
        System.out.println(todaysDate.get(Calendar.HOUR_OF_DAY)); 
        System.out.println(todaysDate.get(Calendar.HOUR)); 
        System.out.println(todaysDate.get(Calendar.MINUTE)); 
        System.out.println(todaysDate.get(Calendar.SECOND)); 
//        month   =   todaysDate.get(Calendar.MONTH)   +   1;   
//        day   =   todaysDate.get(Calendar.DAY_OF_MONTH);   
//        hourOfDay   =   todaysDate.get(Calendar.HOUR_OF_DAY);     //   24小时制   
//        hour   =   todaysDate.get(Calendar.HOUR);                   //   12小时制   
//        minute   =   todaysDate.get(Calendar.MINUTE);   
//        second   =   todaysDate.get(Calendar.SECOND);   
    }

}
