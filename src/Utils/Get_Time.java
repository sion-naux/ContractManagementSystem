package Utils;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class Get_Time {

    public Date getCurrentTime(){
        //获取当前时间
        long l = System.currentTimeMillis();
        Date time = new Date(l);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.format(time);
        return time;
    }

}
