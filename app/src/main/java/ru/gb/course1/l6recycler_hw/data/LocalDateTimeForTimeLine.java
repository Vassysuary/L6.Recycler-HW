package ru.gb.course1.l6recycler_hw.data;

import java.time.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class LocalDateTimeForTimeLine {
    private final GregorianCalendar calendar = new GregorianCalendar();
    //    private String dateTime = calendar.getTime().toString();
    private String dateTime;
    public String LocalDateTimeForTimeLine() {

// делаем для дня месяца добавление нолика спереди при необходимости
        int day = Integer.parseInt(String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)));
        if ( day < 10) dateTime = "0" + String.valueOf(day);
        else dateTime = String.valueOf(day);
        dateTime += "/";
// колдуем с месяцем, т.к. выдаёт номер месяца с нулевого; и дополним нулём, если надо.
        int realMonth = Integer.parseInt(String.valueOf(calendar.get(Calendar.MONTH))) + 1;
        if ( realMonth < 10) dateTime += "0" + String.valueOf(realMonth);
        else dateTime += String.valueOf(realMonth);
        dateTime += "/" + calendar.get(Calendar.YEAR) + " ";
// делаем для часов добавление нолика спереди при необходимости
        int hour = Integer.parseInt(String.valueOf(calendar.get(Calendar.HOUR)));
        if ( hour < 10) dateTime += "0" + String.valueOf(hour);
        else dateTime += String.valueOf(hour);
        dateTime += ":";
// делаем для минут добавление нолика спереди при необходимости
        int minute = Integer.parseInt(String.valueOf(calendar.get(Calendar.MINUTE)));
        if ( minute < 10) dateTime += "0" + String.valueOf(minute);
        else dateTime += String.valueOf(minute);
        return dateTime;
    }
}
