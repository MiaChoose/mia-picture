package com.lumenglover.miapicturebackend.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CalendarTool {
    public static String getTodayNum() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(new Date());
    }
}
