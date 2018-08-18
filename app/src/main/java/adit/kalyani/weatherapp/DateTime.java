package adit.kalyani.weatherapp;

import java.sql.Date;

public class DateTime {
    public int year;
    public  int month;
    public int day;

    public  int hour;
    public  int minute;
    public  int seconds;
    public  String getMonth(){
        switch (month){
            case 1: return  "Jan";
            case 2: return  "Feb";
            case 3: return  "Mar";
            case 4: return  "Apr";
            case 5: return  "May";
            case 6: return  "Jun";
            case 7: return  "Jul";
            case 8: return  "Aug";
            case 9: return  "Sep";
            case 10: return  "Oct";
            case 11: return  "Nov";
            case 12: return  "Dec";
            default:return "Invalid Month";
        }
    }
    public DateTime(int year, int month, int day, int hour, int minute, int seconds) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
        this.seconds = seconds;
    }
    public static boolean CompareDay(DateTime d1,DateTime d2){
        return  d1.day==d2.day;
    }
    public  static  DateTime parse(String date){
        String dt = date.substring(0,date.indexOf(" "));
        String tm = date.substring(date.indexOf(" ")+1,date.length());

        String[] d =  dt.split("-");
        String[] t = tm.split(":");


        int year = Integer.valueOf(d[0]);
        int month = Integer.valueOf(d[1]);
        int day = Integer.valueOf(d[2]);

        int hour = Integer.valueOf(t[0]);
        int minute = Integer.valueOf(t[1]);
        int seconds = Integer.valueOf(t[2]);

        return new DateTime(year, month, day, hour, minute, seconds);

    }
}
