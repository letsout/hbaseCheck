package utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author H
 * @desc 转换时间格式
 * @date 2018/3/22
 */
public class DateUtils {
    /**
     *
     * @param type 参数类型 1-年份，2-月份，3-星期，5-日期，11-小时，12-分钟，13-秒，14 -毫秒
     * @param time 正数代表之后，负数代表之
     * @return
     */
    public static String getTime(int type,int time){
        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date day = new Date();
        Calendar calendar= Calendar.getInstance();
        calendar.setTime(day);
        calendar.add(type,time);
        day=calendar.getTime();
        String time1 = df.format(day).replaceAll("[^\\d]", "");
        return time1;
    }
}
