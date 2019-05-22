package com.shengvideo.common.utils;

import org.apache.commons.lang.StringUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CronUtil {
    public static String  time2Cron(Date time,String week){
        DateFormat format = new SimpleDateFormat("ss mm HH");
        String cron = format.format(time);
        if (StringUtils.isNotBlank(week)) {
            cron += " ? * " + toUKWeek(week);
        }else {
            cron += " * * ?";
        }
        return cron;
    }

    public static String  date2Cron(Date data){
        DateFormat format = new SimpleDateFormat("ss mm HH dd MM ?");
        String cron = format.format(data);
        return cron;
    }

    public static String toUKWeek(String week){
        String[] weekList = week.split(",");
        String ukWeek = "";

        for (int i = 0;i < weekList.length;i++){
            if (weekList[i].trim().isEmpty()){
                continue;
            }
            Integer w = Integer.parseInt(weekList[i].trim());
//            if (w == 0){
//                w = 7;
//            }
            ukWeek += (w + 1 + "");
            if (i != weekList.length - 1) {
               ukWeek += ",";
            }
        }
        return ukWeek;
    }

}
