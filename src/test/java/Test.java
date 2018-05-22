import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author H
 * @desc 测试基础专用
 * @date 2018/3/22
 */
public class Test {
    public static void main(String[] args) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date day = new Date();
        Calendar calendar= Calendar.getInstance();
        calendar.setTime(day);
        calendar.add(11,-1);
        day=calendar.getTime();
        String time1 = df.format(day).replaceAll("[^\\d]", "");
    }
}
