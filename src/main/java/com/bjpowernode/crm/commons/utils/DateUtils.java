package com.bjpowernode.crm.commons.utils;

import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    public static String formateDateTime(Date date){
        SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr =sdf.format(date);
        return dateStr;
    }
}
